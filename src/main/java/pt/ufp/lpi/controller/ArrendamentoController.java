package pt.ufp.lpi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.controller.dtos.ArrendamentoDTO;
import pt.ufp.lpi.controller.dtos.AvaliacaoArrendamentoDTO;
import pt.ufp.lpi.controller.dtos.ImovelDTO;
import pt.ufp.lpi.controller.dtos.VendaDTO;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.Arrendamento;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.enumerado.Avaliacao;
import pt.ufp.lpi.services.AplicacaoService;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.Optional;

@Controller
@RequestMapping("/arrendamento")
@CrossOrigin("*")
public class ArrendamentoController
{

    private final UtilizadorService utilizadorService;
    private final AplicacaoService aplicacaoService;
    private final DTOToModelConversor conversor = DTOToModelConversor.getInstance();

    public ArrendamentoController(UtilizadorService utilizadorService, AplicacaoService aplicacaoService) {
        this.utilizadorService = utilizadorService;
        this.aplicacaoService = aplicacaoService;
    }

    @PostMapping(value = "",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvaliacaoArrendamentoDTO> avalicaoArrendamento(@RequestBody ArrendamentoDTO arrendamentoDTO)
    {
        ImovelDTO imovelDTO = ImovelDTO.builder()
                .concelhoId(arrendamentoDTO.getConcelhoId())
                .anoConstrução(arrendamentoDTO.getAnoConstrução())
                .estadoImovel(arrendamentoDTO.getEstadoImovel())
                .elevador(arrendamentoDTO.isElevador())
                .jardim(arrendamentoDTO.isJardim())
                .garagem(arrendamentoDTO.isGaragem())
                .piscina(arrendamentoDTO.isPiscina())
                .metrosQuadrados(arrendamentoDTO.getMetrosQuadrados())
                .topologia(arrendamentoDTO.getTopologia())
                .userId(arrendamentoDTO.getUserId())
                .build();
        Optional<Imovel> optionalImovel = utilizadorService.criaImovel(conversor.converterDTOParaImovel(imovelDTO));
        if (optionalImovel.isPresent())
        {
            arrendamentoDTO.setImovelId(optionalImovel.get().getId());
            Optional<Arrendamento> optionalArrendamento = utilizadorService.criaArrendamento(conversor.converterDTOParaArrendamento(arrendamentoDTO));
            if (optionalArrendamento.isPresent())
            {
                Arrendamento arrendamento = optionalArrendamento.get();
                Imovel imovel = optionalImovel.get();
                arrendamento.setImovel(imovel);
                Float valor = aplicacaoService.getValorFuturoDeArrendamento(arrendamento.getId());
                Optional<Avaliacao> avalicaoOptional = aplicacaoService.getAvalicaoNegocioArrendamento(arrendamento.getId());
                if (avalicaoOptional.isPresent() && valor != 0)
                    return ResponseEntity.ok(conversor.converterArrendamentoParaAvaliacaoArrendamentoDTO(arrendamento));
            }
        }
        return ResponseEntity.badRequest().build();
    }





  /*  @PostMapping(value = "",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrendamentoDTO> createArrendamento(@RequestBody ArrendamentoDTO arrendamentoDTO)
    {
        Optional<Arrendamento> optionalArrendamento = utilizadorService.
                criaArrendamento(conversor.converterDTOParaArrendamento(arrendamentoDTO));
        if (optionalArrendamento.isPresent())
            return ResponseEntity.ok(conversor.converterArrendamentoParaDTO(optionalArrendamento.get()));
        return ResponseEntity.badRequest().build();
    }*/

    @GetMapping("/valor/{id}")
    public ResponseEntity<Float> getValorArrendamento(@PathVariable("id") Long arrendamentoId)
    {
        Float valor = aplicacaoService.getValorFuturoDeArrendamento(arrendamentoId);
        if(valor!=0)
            return ResponseEntity.ok(valor);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/avaliacao/{id}")
    public ResponseEntity<String> getAvaliacaoArrendamento(@PathVariable("id") Long arrendamentoId)
    {
        Optional<Avaliacao> avalicaoOptional = aplicacaoService.getAvalicaoNegocioArrendamento(arrendamentoId);
        if (avalicaoOptional.isPresent())
            return ResponseEntity.ok(avalicaoOptional.get().name());
        return ResponseEntity.notFound().build();
    }
}
