package pt.ufp.lpi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.controller.dtos.*;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/concelho")
@CrossOrigin("*")
public class ConcelhoController
{
    private final UtilizadorService utilizadorService;
    private final DTOToModelConversor conversor = DTOToModelConversor.getInstance();

    public ConcelhoController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }


    @GetMapping()
    public ResponseEntity<Iterable<ConcelhoDTO>> getAllConcelhos()
    {
        List<ConcelhoDTO> responseDTOS = new ArrayList<>();
        utilizadorService.findAllConcelhos().forEach(concelho -> responseDTOS.add(conversor.converterConcelhoParaDTO(concelho)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("historicos/preco/{id}")
    public ResponseEntity<ConcelhoDTO> getInformacaoConcelhos(@PathVariable Long concelhoId)
    {
        Optional<Concelho> optionalConcelho = utilizadorService.findConcelhoById(concelhoId);
        if (optionalConcelho.isPresent())
            return ResponseEntity.ok(conversor.converterConcelhoParaDTO(optionalConcelho.get()));
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConcelhoDTO> getConcelhoById(@PathVariable Long id)
    {
        Optional<Concelho> optionalConcelho = utilizadorService.findConcelhoById(id);
        return optionalConcelho.map(concelho -> {
            ConcelhoDTO concelhoDTO = conversor.converterConcelhoParaDTO(concelho);
            return ResponseEntity.ok(concelhoDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
