package pt.ufp.lpi.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ConcelhoTest {

    @Test
    void adicionaHistoricoVenda()
    {
        Concelho concelho = Concelho.builder()
                .nome("gaia")
                .build();
        HistoricoVenda historicoVenda = HistoricoVenda.builder()
                .precoAntigo(200)
                .data(LocalDateTime.now())
                .build();
        assertEquals(0, concelho.getHistoricoVendas().size());
        concelho.adicionaHistoricoVenda(historicoVenda);
        assertEquals(1, concelho.getHistoricoVendas().size());
        concelho.adicionaHistoricoVenda(historicoVenda);
        assertEquals(1, concelho.getHistoricoVendas().size());    }

    @Test
    void adicionaHistoricoArrendamento()
    {
        Concelho concelho = Concelho.builder()
                .nome("gaia")
                .build();
        HistoricoArrendamento historicoArrendamento = HistoricoArrendamento.builder()
                .data(LocalDateTime.now())
                .precoAntigo(400)
                .build();
        assertEquals(0, concelho.getHistoricoArrendamentos().size());
        concelho.adicionaHistoricoArrendamento(historicoArrendamento);
        assertEquals(1, concelho.getHistoricoArrendamentos().size());
        concelho.adicionaHistoricoArrendamento(historicoArrendamento);
        assertEquals(1, concelho.getHistoricoArrendamentos().size());
    }
}