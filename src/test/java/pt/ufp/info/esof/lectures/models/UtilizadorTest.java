package pt.ufp.info.esof.lectures.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilizadorTest {

    @Test
    void adicionaConcelho()
    {
        Utilizador u = new Utilizador();
        u.setUserName("jfps");
        u.setPassword("123456");
        Concelho gaia = new Concelho();
        gaia.setNome("Gaia");
        Concelho maia = new Concelho();
        maia.setNome("Maia");

        assertEquals(0,u.getConcelhos().size());
        u.adicionaConcelho(gaia);
        assertEquals(1,u.getConcelhos().size());
        u.adicionaConcelho(gaia);
        assertEquals(1,u.getConcelhos().size());
        u.adicionaConcelho(maia);
        assertEquals(2,u.getConcelhos().size());

        assertNotNull(gaia.getUtilizadores());
        assertNotNull(maia.getUtilizadores());
    }

    @Test
    void adicionaImovel()
    {
        Utilizador u = new Utilizador();
        u.setUserName("jfps");
        u.setPassword("123456");
        Imovel casaDasRosas = new Imovel();
        Imovel casaDasCravos = new Imovel();

        assertEquals(0, u.getImoveis().size());
        u.adicionaImovel(casaDasRosas);
        assertEquals(1, u.getImoveis().size());
        u.adicionaImovel(casaDasRosas);
        assertEquals(1, u.getImoveis().size());
        u.adicionaImovel(casaDasCravos);
        assertEquals(2, u.getImoveis().size());

        assertNotNull(casaDasRosas.getUtilizador());
        assertNotNull(casaDasCravos.getUtilizador());
    }


}