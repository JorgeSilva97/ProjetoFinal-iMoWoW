package pt.ufp.lpi.lectures.models;

import org.junit.jupiter.api.Test;

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
}