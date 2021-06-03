package pt.ufp.lpi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.controller.dtos.AvaliacaoVendaDTO;
import pt.ufp.lpi.controller.dtos.ImovelDTO;
import pt.ufp.lpi.controller.dtos.VendaDTO;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Venda;
import pt.ufp.lpi.models.enumerado.Avaliacao;
import pt.ufp.lpi.services.AplicacaoService;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.Optional;

@Controller
@RequestMapping("/venda")
@CrossOrigin("*")
public class VendaController
{
    private final UtilizadorService utilizadorService;
    private final AplicacaoService aplicacaoService;
    private final DTOToModelConversor conversor = DTOToModelConversor.getInstance();

    public VendaController(UtilizadorService utilizadorService, AplicacaoService aplicacaoService) {
        this.utilizadorService = utilizadorService;
        this.aplicacaoService = aplicacaoService;
    }

    @PostMapping(value = "",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvaliacaoVendaDTO> avaliacaoVenda(@RequestBody VendaDTO vendaDTO, @RequestBody ImovelDTO imovelDTO)
    {
        Optional<Imovel> optionalImovel = utilizadorService.criaImovel(conversor.converterDTOParaImovel(imovelDTO));
        Optional<Venda> optionalVenda = utilizadorService.criaVenda(conversor.converterDTOParaVenda(vendaDTO));
        if (optionalVenda.isPresent() && optionalImovel.isPresent())
        {
            Venda venda = optionalVenda.get();
            Imovel imovel = optionalImovel.get();
            venda.setImovel(imovel);
            Float valor = aplicacaoService.getValorFuturoDaVenda(venda.getId());
            Optional<Avaliacao> avalicaoOptional = aplicacaoService.getAvalicaoNegocioVenda(venda.getId());
            if (avalicaoOptional.isPresent() && valor!=0)
            {
                Avaliacao avaliacao = avalicaoOptional.get();
                return ResponseEntity.of(conversor.converterVendaParaAvaliacaoVendaDTO(optionalVenda.get()));
            }
        }
        return ResponseEntity.badRequest().build();
        /*
        Optional<Venda> optionalVenda = utilizadorService.criaVenda(conversor.converterDTOParaAvaliacaoVendaDTO(avaliacaoVendaDTO));
        Float valor = aplicacaoService.getValorFuturoDaVenda(avaliacaoVendaDTO.getId());
        Optional<Avaliacao> avalicaoOptional = aplicacaoService.getAvalicaoNegocioVenda(avaliacaoVendaDTO.getId());
        if (optionalVenda.isPresent() && valor!=0 && avalicaoOptional.isPresent())
            return ResponseEntity.of(conversor.converterVendaParaAvaliacaoVendaDTO(optionalVenda.get()));
        return ResponseEntity.badRequest().build();
         */

    }

    @PostMapping(value = "",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VendaDTO> createVenda(@RequestBody VendaDTO vendaDTO)
    {
        Optional<Venda> optionalVenda = utilizadorService.criaVenda(conversor.converterDTOParaVenda(vendaDTO));
        if (optionalVenda.isPresent())
            return ResponseEntity.ok(conversor.converterVendaParaDTO(optionalVenda.get()));
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/valor/{id}")
    public ResponseEntity<Float> getValorVenda(@PathVariable("id") Long vendaId)
    {
        Float valor = aplicacaoService.getValorFuturoDaVenda(vendaId);
        if(valor!=0)
            return ResponseEntity.ok(valor);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/avaliacao/{id}")
    public ResponseEntity<String> getAvaliacaoVenda(@PathVariable("id") Long vendaId)
    {
        Optional<Avaliacao> avalicaoOptional = aplicacaoService.getAvalicaoNegocioVenda(vendaId);
        if (avalicaoOptional.isPresent())
            return ResponseEntity.ok(avalicaoOptional.get().name());
        return ResponseEntity.notFound().build();
    }
}
