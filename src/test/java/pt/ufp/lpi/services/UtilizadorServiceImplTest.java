package pt.ufp.lpi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.lpi.models.*;
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
    private UtilizadorRepository utilizadorRepository;
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
    void criaImovel()
    {
        Distrito porto = Distrito.builder()
                .nome("porto")
                .build();
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .distrito(porto)
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
                .topologia(Topologia.T1)
                .estado(EstadoImovel.novo)
                .build();
        //jorge.getImoveis().add(imovel);
        /*when(utilizadorService.criaImovel(1L, 1L, Topologia.T1, EstadoImovel.novo,
                imovel.getAnoConstrução(), imovel.getMetrosQuadrados(), imovel.isPiscina(), imovel.isJardim(),
                imovel.isGaragem(), imovel.isElevador())).thenReturn(Optional.of(new Imovel()));*/

        //linha 54 UtilizadorServiceImpl
        when(utilizadorRepository.findById(1L)).thenReturn(Optional.of(jorge));
        //linha 55 UtilizadorServiceImpl
        when(concelhoRepository.findById(1L)).thenReturn(Optional.of(gaia));
        //linha 73 UtilizadorServiceImpl
        when(imovelRepository.save(imovel)).thenReturn(imovel);

        assertTrue(utilizadorService.criaImovel(1L, 1L, Topologia.T1, EstadoImovel.novo,
                imovel.getAnoConstrução(), imovel.getMetrosQuadrados(), imovel.isPiscina(), imovel.isJardim(),
                imovel.isGaragem(), imovel.isElevador()).isPresent());
        assertTrue(utilizadorService.criaImovel(2L, 2L, Topologia.T1_1, EstadoImovel.renovado,
                imovel.getAnoConstrução(), imovel.getMetrosQuadrados(), imovel.isPiscina(), imovel.isJardim(),
                imovel.isGaragem(), imovel.isElevador()).isEmpty());

    }

    @Test
    void criaVenda()
    {
        Distrito porto = Distrito.builder()
                .nome("porto")
                .build();
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .distrito(porto)
                .build();
        Utilizador jorge = Utilizador.builder()
                .userName("jorge")
                .build();
        Imovel imovel = Imovel.builder()
                .anoConstrução(200)
                .metrosQuadrados(100)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .elevador(false)
                .utilizador(jorge)
                .concelho(gaia)
                .topologia(Topologia.T1)
                .estado(EstadoImovel.novo)
                .build();
        Venda venda = Venda.builder()
                .precoTotal(200000)
                .imovel(imovel)
                .build();
        when(utilizadorService.criaVenda(1L, venda.getPrecoTotal())).thenReturn(Optional.of(new Venda()));
        assertTrue(utilizadorService.criaVenda(1L, venda.getPrecoTotal()).isPresent());
        assertTrue(utilizadorService.criaVenda(2L, venda.getPrecoTotal()).isEmpty());
    }

    @Test
    void criaArrendamento()
    {
        Distrito porto = Distrito.builder()
                .nome("porto")
                .build();
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .distrito(porto)
                .build();
        Utilizador jorge = Utilizador.builder()
                .userName("jorge")
                .build();
        Imovel imovel = Imovel.builder()
                .anoConstrução(200)
                .metrosQuadrados(100)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .elevador(false)
                .utilizador(jorge)
                .concelho(gaia)
                .topologia(Topologia.T1)
                .estado(EstadoImovel.novo)
                .build();
        Arrendamento arrendamento = Arrendamento.builder()
                .precoArrendamento(600)
                .imovel(imovel)
                .build();
        when(utilizadorService.criaArrendamento(1L, arrendamento.getPrecoArrendamento()))
                .thenReturn(Optional.of(new Arrendamento()));
        assertTrue(utilizadorService.criaArrendamento(1L, arrendamento.getPrecoArrendamento()).isPresent());
        assertTrue(utilizadorService.criaArrendamento(2L, arrendamento.getPrecoArrendamento()).isEmpty());
    }

    @Test
    void consultaPrecoMetroQuadrado()
    {
        Distrito porto = Distrito.builder()
                .id(1L)
                .nome("porto")
                .build();
        Concelho gaia = Concelho.builder()
                .id(1L)
                .nome("gaia")
                .distrito(porto)
                .precoMedio(200)
                .build();
        when(utilizadorService.consultaPrecoMetroQuadrado(1L)).thenReturn(Optional.of(gaia.getPrecoMedio()));
        assertEquals(200, utilizadorService.consultaPrecoMetroQuadrado(1L));
        assertNotEquals(400, utilizadorService.consultaPrecoMetroQuadrado(1L));


    }
}