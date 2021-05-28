package pt.ufp.lpi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.lpi.controller.dtos.ImovelDTO;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/imovel")
public class ImovelController
{
    private final UtilizadorService utilizadorService;
    private final DTOToModelConversor conversor = DTOToModelConversor.getInstance();

    public ImovelController(UtilizadorService utilizadorService) { this.utilizadorService = utilizadorService; }

    @GetMapping()
    public ResponseEntity<Iterable<ImovelDTO>> getAllImoveis()
    {
        List<ImovelDTO> responseDTOS = new ArrayList<>();
        utilizadorService.findAllImoveis().forEach(imovel -> responseDTOS.add(conversor.converterImovelParaDTO(imovel)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping()
    public ResponseEntity<ImovelDTO> getImovelById(@PathVariable Long id)
    {
        Optional<Imovel> optionalImovel = utilizadorService.findImoveloById(id);
        return optionalImovel.map(imovel -> {
            ImovelDTO imovelDTO = conversor.converterImovelParaDTO(imovel);
            return ResponseEntity.ok(imovelDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping(value = "",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImovelDTO> createImovel(@RequestBody ImovelDTO imovelCreateDTO)
    {
        Optional<Imovel> optionalImovel=utilizadorService.criaImovel(conversor.converterDTOParaImovel(imovelCreateDTO));
        if(optionalImovel.isPresent())
            return ResponseEntity.ok(conversor.converterImovelParaDTO(optionalImovel.get()));
        return ResponseEntity.badRequest().build();
    }
}
