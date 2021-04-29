package pt.ufp.info.esof.lectures.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void precoMetroQuadrado()
    {
        Imovel casaDaRocha = new Imovel();
        casaDaRocha.setPrecoTotal(230000);
        casaDaRocha.setMetrosQuadrados(230);
        Venda venda = new Venda();
        venda.setImovel(casaDaRocha);

        assertNotEquals(900, venda.precoMetroQuadrado());
        assertEquals(1000, venda.precoMetroQuadrado());
        assertNotEquals(1300, venda.precoMetroQuadrado());
    }
}