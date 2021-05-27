package pt.ufp.lpi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.controller.dto.*;
import pt.ufp.lpi.controller.dto.conversores.ConverterConcelhoParaDTO;
import pt.ufp.lpi.controller.dto.ImovelDTO;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/utilizador")
public class ConcelhoController
{
    private final UtilizadorService utilizadorService;
    private final ConverterConcelhoParaDTO converterConcelhoParaDTO = new ConverterConcelhoParaDTO();
    private final DTOToModelConversor conversor=DTOToModelConversor.getInstance();

    public ConcelhoController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }


    @GetMapping()
    public ResponseEntity<Iterable<ConcelhoResponseDTO>> getAllConcelhos()
    {
        List<ConcelhoResponseDTO> responseDTOS = new ArrayList<>();
        utilizadorService.findAllConcelhos().forEach(concelho -> responseDTOS.add(converterConcelhoParaDTO.converter(concelho)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcelhoResponseDTO> getConcelhoById(@PathVariable Long id)
    {
        Optional<Concelho> optionalConcelho = utilizadorService.findConcelhoById(id);
        return optionalConcelho.map(concelho -> {
            ConcelhoResponseDTO concelhoResponseDTO = converterConcelhoParaDTO.converter(concelho);
            return ResponseEntity.ok(concelhoResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImovelDTO> createImovel(@RequestBody ImovelDTO imovelCreateDTO){
        Optional<Imovel> optionalImovel=utilizadorService.criaImovel(conversor.converterDTOToImovel(imovelCreateDTO));
        if(optionalImovel.isPresent()){
            return ResponseEntity.ok(conversor.converterImovelToDTO(optionalImovel.get()));
        }
        return ResponseEntity.badRequest().build();

    }

    /*@GetMapping()
    public ResponseEntity<Iterable<ImovelResponseDTO>> getAllImoveis()
    {
        List<ImovelResponseDTO> responseDTOS = new ArrayList<>();
        utilizadorService.findAllImoveis().forEach(imovel -> responseDTOS.add());
    }*/




}
