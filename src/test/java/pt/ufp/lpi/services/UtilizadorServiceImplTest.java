package pt.ufp.lpi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Distrito;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Utilizador;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;
import pt.ufp.lpi.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UtilizadorServiceImplTest
{

    @Autowired
    private UtilizadorService utilizadorService;
    @MockBean
    private ConcelhoRepository concelhoRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @MockBean
    private VendaRepository vendaRepository;
    @MockBean
    private ArrendamentoRepository arrendamentoRepository;
    @MockBean
    private TopologiaRepository topologiaRepository;
    @MockBean
    private EstadoRepository estadoRepository;

    @Test
    void findAllConcelhos()
    {
        when(concelhoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(utilizadorService.findAllConcelhos());
    }

    @Test
    void findConcelhoById()
    {
        when(concelhoRepository.findById(1L)).thenReturn(Optional.of(new Concelho()));
        assertTrue(utilizadorService.findConcelhoById(1L).isPresent());
        assertTrue(utilizadorService.findConcelhoById(2L).isEmpty());
    }

    @Test
    void findAllImoveis()
    {
        when(imovelRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(utilizadorService.findAllImoveis());

    }

    @Test
    void findImoveloById()
    {
        when(imovelRepository.findById(1L)).thenReturn(Optional.of(new Imovel()));
        assertTrue(utilizadorService.findImoveloById(1L).isPresent());
        assertTrue(utilizadorService.findImoveloById(2L).isEmpty());
    }

    @Test
    void criaImovel() {
        Concelho gaia = Concelho.builder()
                .nome("porto")
                .build();
        Utilizador jorge = Utilizador.builder()
                .userName("jorge")
                .build();
        Topologia top = Topologia.T1;
        EstadoImovel estadoImovel = EstadoImovel.novo;
        Imovel imovel = Imovel.builder()
                .anoConstrução(200)
                .metrosQuadrados(100)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .elevador(false)
                .utilizador(jorge)
                .concelho(gaia)
                .topologia(top)
                .estado(estadoImovel)
                .build();
        //jorge.getImoveis().add(imovel);
        when(utilizadorService.criaImovel(1L, 1L, 1L, 1L,
                imovel.getAnoConstrução(), imovel.getMetrosQuadrados(), imovel.isPiscina(), imovel.isJardim(),
                imovel.isGaragem(), imovel.isElevador())).thenReturn(Optional.of(new Imovel()));
        assertTrue(utilizadorService.criaImovel(1L, 1L, 1L, 1L,
                imovel.getAnoConstrução(), imovel.getMetrosQuadrados(), imovel.isPiscina(), imovel.isJardim(),
                imovel.isGaragem(), imovel.isElevador()).isPresent());
        assertTrue(utilizadorService.criaImovel(2L, 2L, 2L, 2L,
                imovel.getAnoConstrução(), imovel.getMetrosQuadrados(), imovel.isPiscina(), imovel.isJardim(),
                imovel.isGaragem(), imovel.isElevador()).isEmpty());

    }

    @Test
    void criaVenda()
    {

    }

    @Test
    void criaArrendamento() {
    }

    @Test
    void consultaPrecoMetroQuadrado() {
    }
}