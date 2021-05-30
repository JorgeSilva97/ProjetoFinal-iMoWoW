package pt.ufp.lpi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.controller.dtos.ImovelDTO;
import pt.ufp.lpi.controller.dtos.ValorVendaDTO;
import pt.ufp.lpi.controller.dtos.VendaDTO;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.Venda;
import pt.ufp.lpi.services.AplicacaoService;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.Optional;

@Controller
@RequestMapping("/venda")
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
    public ResponseEntity<VendaDTO> createVenda(@RequestBody VendaDTO vendaCreateDTO)
    {
        Optional<Venda> optionalVenda = utilizadorService.criaVenda(conversor.converterDTOParaVenda(vendaCreateDTO));
        if (optionalVenda.isPresent())
            return ResponseEntity.ok(conversor.converterVendaParaDTO(optionalVenda.get()));
        return ResponseEntity.badRequest().build();
    }
/*
    @GetMapping("/valor/{id}")
    public ResponseEntity<VendaDTO> getValorVenda(@PathVariable Long vendaId)
    {
        Optional<Float> valor = Optional.of(aplicacaoService.getValorFuturoDaVenda(vendaId));
        return valor.map(venda -> {
          //  ValorVendaDTO valorVendaDTO = conversor.converterValorVendaParaDTO(venda)
        });
    }*/
}
