package pt.ufp.lpi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrendamentoTest {

    @Test
    void precoMetroQuadrado()
    {
        Imovel casaDaRocha = new Imovel();
        casaDaRocha.setMetrosQuadrados(200);
        Arrendamento arrendamento = new Arrendamento();
        arrendamento.setImovel(casaDaRocha);
        arrendamento.setPrecoArrendamento(800);

        assertNotEquals(9, arrendamento.precoMetroQuadrado());
        assertEquals(4, arrendamento.precoMetroQuadrado());
        assertNotEquals(3, arrendamento.precoMetroQuadrado());
    }
}