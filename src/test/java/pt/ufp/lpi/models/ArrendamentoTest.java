package pt.ufp.lpi.models;

import org.junit.jupiter.api.Test;
import pt.ufp.lpi.models.enumerado.Avalicao;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

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

    @Test
    void calculaArrendamento()
    {
        Concelho gaia = Concelho.builder()
                .precoMedioArrendamento(5)
                .build();
        Imovel casaDaRocha = Imovel.builder()
                .metrosQuadrados(100)
                .topologia(Topologia.T1_1)
                .estado(EstadoImovel.novo)
                .concelho(gaia)
                .build();
        Arrendamento arrendamento = Arrendamento.builder()
                .imovel(casaDaRocha)
                .precoArrendamento(600)
                .build();

        assertNotEquals(525, arrendamento.calculaArrendamento());
        assertEquals(588, arrendamento.calculaArrendamento());
    }

    @Test
    void avaliacaoNegocioArrendamento()
    {
        Concelho gaia = Concelho.builder()
                .precoMedioArrendamento(5)
                .build();

        Imovel casaDaRocha = Imovel.builder()
                .metrosQuadrados(100)
                .topologia(Topologia.T1_1)
                .estado(EstadoImovel.novo)
                .concelho(gaia)
                .build();
        Arrendamento arrendamento1 = Arrendamento.builder()
                .imovel(casaDaRocha)
                .precoArrendamento(600)
                .build();

        Imovel casaDaPedra= Imovel.builder()
                .metrosQuadrados(100)
                .topologia(Topologia.T1_1)
                .estado(EstadoImovel.novo)
                .concelho(gaia)
                .build();
        Arrendamento arrendamento2 = Arrendamento.builder()
                .imovel(casaDaPedra)
                .precoArrendamento(520)
                .build();

        assertEquals(Avalicao.Suficiente, arrendamento1.avaliacaoNegocioArrendamento());
        assertEquals(Avalicao.MuitoBom, arrendamento2.avaliacaoNegocioArrendamento());
    }
}