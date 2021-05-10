package pt.ufp.lpi.models;

import org.junit.jupiter.api.Test;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void precoMetroQuadrado()
    {
        Imovel casaDaRocha = new Imovel();
        casaDaRocha.setMetrosQuadrados(230);
        Venda venda = new Venda();
        venda.setImovel(casaDaRocha);
        venda.setPrecoTotal(230000);

        assertNotEquals(900, venda.precoMetroQuadrado());
        assertEquals(1000, venda.precoMetroQuadrado());
        assertNotEquals(1300, venda.precoMetroQuadrado());
    }

    @Test
    void calcularVenda()
    {
        Concelho gaia = new Concelho();
        gaia.setPrecoMedio(200);

        Imovel casaDaRocha = new Imovel();
        casaDaRocha.setMetrosQuadrados(500);
        casaDaRocha.setPiscina(true);
        casaDaRocha.setGaragem(true);
        casaDaRocha.setJardim(true);
        casaDaRocha.setEstado(EstadoImovel.novo);
        casaDaRocha.setConcelho(gaia);
        Venda venda1 = new Venda();
        venda1.setImovel(casaDaRocha);
        venda1.setPrecoTotal(160000);

        Imovel casaDaPedra = new Imovel();
        casaDaPedra.setMetrosQuadrados(500);
        casaDaPedra.setElevador(true);
        casaDaPedra.setJardim(true);
        casaDaPedra.setEstado(EstadoImovel.porRecuperar);
        casaDaPedra.setConcelho(gaia);
        Venda venda2 = new Venda();
        venda2.setImovel(casaDaPedra);
        venda2.setPrecoTotal(100000);

        assertNotEquals(100000, venda1.calcularVenda());
        assertEquals(158694.375, venda1.calcularVenda());
        assertNotEquals(100000, venda2.calcularVenda());
        assertEquals(47460, venda2.calcularVenda());

        casaDaPedra.setTopologia(Topologia.T1);
        assertNotEquals(47460, venda2.calcularVenda());
        assertEquals(47460, venda2.calcularVenda());

        casaDaRocha.setTopologia(Topologia.T2_1);
        assertNotEquals(158694.375, venda1.calcularVenda());
        assertEquals(158694.375, venda1.calcularVenda());

    }
}