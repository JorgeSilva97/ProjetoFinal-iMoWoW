package pt.ufp.info.esof.lectures.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrendamentoTest {

    @Test
    void precoMetroQuadrado()
    {
        Imovel casaDaRocha = new Imovel();
        casaDaRocha.setPrecoTotal(230000);
        casaDaRocha.setMetrosQuadrados(230);
        Arrendamento arrendamento = new Arrendamento();
        arrendamento.setImovel(casaDaRocha);

        assertNotEquals(900, arrendamento.precoMetroQuadrado());
        assertEquals(1000, arrendamento.precoMetroQuadrado());
        assertNotEquals(1300, arrendamento.precoMetroQuadrado());
    }
}