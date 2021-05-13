package pt.ufp.lpi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.repositories.ArrendamentoRepository;
import pt.ufp.lpi.repositories.ConcelhoRepository;
import pt.ufp.lpi.repositories.ImovelRepository;
import pt.ufp.lpi.repositories.VendaRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UtilizadorServiceImpl.class)
class UtilizadorServiceImplTest
{

    @Autowired
    private UtilizadorService utilizadorService;
    @MockBean
    private ConcelhoRepository concelhoRepository;
    @MockBean
    private ImovelRepository imovelRepository;
    @MockBean
    private VendaRepository vendaRepository;
    @MockBean
    private ArrendamentoRepository arrendamentoRepository;


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
    void findAllImoveis() {
    }

    @Test
    void findImoveloById() {
    }

    @Test
    void criaImovel() {
    }

    @Test
    void criaVenda() {
    }

    @Test
    void criaArrendamento() {
    }

    @Test
    void consultaPrecoMetroQuadrado() {
    }
}