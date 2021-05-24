package pt.ufp.lpi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Distrito;
import pt.ufp.lpi.models.HistoricoArrendamento;
import pt.ufp.lpi.models.HistoricoVenda;
import pt.ufp.lpi.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AplicacaoServiceImplTest
{

    @Autowired
    private AplicacaoService aplicacaoService;
    @MockBean
    private ArrendamentoRepository arrendamentoRepository;
    @MockBean
    private VendaRepository vendaRepository;
    @MockBean
    private ConcelhoRepository concelhoRepository;
    @MockBean
    private HistoricoVendaRepository historicoVendaRepository;
    @MockBean
    private HistoricoArrendamentoRepository historicoArrendamentoRepository;

    @Test
    void findAllHistoricosVenda()
    {
        when(historicoVendaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(aplicacaoService.findAllHistoricosVenda());
    }

    @Test
    void findAllHistoricosArrendamento()
    {
        when(historicoArrendamentoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(aplicacaoService.findAllHistoricosArrendamento());
    }

    @Test
    void criaHistoricoVenda()
    {
        Distrito porto = Distrito.builder()
                .nome("porto")
                .build();
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .distrito(porto)
                .precoMedioVenda(200)
                .precoMedioArrendamento(19)
                .build();
        HistoricoVenda historicoVenda = HistoricoVenda.builder()
                .concelho(gaia)
                .precoAntigo(196)
                .data(LocalDateTime.now())
                .build();

        when(concelhoRepository.findById(1L)).thenReturn(Optional.of(gaia));
        when(historicoVendaRepository.save(historicoVenda)).thenReturn(historicoVenda);

        assertTrue(aplicacaoService.criaHistoricoVenda(1L, gaia.getPrecoMedioVenda(),
                historicoVenda.getPrecoAntigo()).isPresent());
        assertTrue(aplicacaoService.criaHistoricoVenda(2L, gaia.getPrecoMedioVenda(),
                historicoVenda.getPrecoAntigo()).isEmpty());
    }

    @Test
    void criaHistoricoArrendamento()
    {
        Distrito porto = Distrito.builder()
                .nome("porto")
                .build();
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .distrito(porto)
                .precoMedioVenda(200)
                .precoMedioArrendamento(19)
                .build();
        HistoricoArrendamento historicoArrendamento = HistoricoArrendamento.builder()
                .precoAntigo(18)
                .concelho(gaia)
                .data(LocalDateTime.now())
                .build();

        when(concelhoRepository.findById(1L)).thenReturn(Optional.of(gaia));
        when(historicoArrendamentoRepository.save(historicoArrendamento)).thenReturn(historicoArrendamento);

        assertTrue(aplicacaoService.criaHistoricoArrendamento(1L, gaia.getPrecoMedioArrendamento(),
                historicoArrendamento.getPrecoAntigo()).isPresent());
        assertTrue(aplicacaoService.criaHistoricoArrendamento(2L, gaia.getPrecoMedioArrendamento(),
                historicoArrendamento.getPrecoAntigo()).isEmpty());
    }

    @Test
    void getValorFuturoDeArrendamento() {
    }

    @Test
    void getValorFuturoDaVenda() {
    }

    @Test
    void getAvalicaoNegocioVenda() {
    }

    @Test
    void getAvalicaoNegocioArrendamento() {
    }
}