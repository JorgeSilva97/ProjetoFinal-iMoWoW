package pt.ufp.lpi.models;

import lombok.*;
import pt.ufp.lpi.models.enumerado.Avalicao;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arrendamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float precoArrendamento;
    @OneToOne
    private Imovel imovel;

    //-------------------------------------------
    private final float PercentagemT1 = 1.045F; //mais 4.5%
    private final float PercentagemT1Mais1 = 1.05F; //mais 5%
    private final float PercentagemT2 = 1.09F; //mais 9%
    private final float PercentagemT2Mais1 = 1.10F; //mais 10%
    private final float PercentagemT3 = 1.15F; //mais 15%
    private final float PercentagemT3Mais1 = 1.17F; //mais 17%
    private final float PercentagemT4 = 1.22F; //mais 22%
    //------------------------------------------------------
    private final float PercentagemEstadoRenovado = 1.06F; //mais 6%
    private final float PercentagemEstadoNovo = 1.12F; //mais 12%
    private final float PercentagemEstadoPorRecuperar = 0.60F; //menos 40%
    private final float PercentagemEstadoUsado = 0.96F; //menos 4%
    //------------------------------------------------------

    public float precoMetroQuadrado()
    {
        return this.getPrecoArrendamento()/this.imovel.getMetrosQuadrados();
    }

    //não testado
    public float calculaArrendamento()
    {
        float valorConcelhoMetroQuadrado = this.getImovel().getConcelho().getPrecoMedioArrendamento();
        float metrosQuadrados = this.getImovel().getMetrosQuadrados();
        float valor = valorConcelhoMetroQuadrado * metrosQuadrados;
        if (this.getImovel().getTopologia() == Topologia.T1)
            valor = valor * PercentagemT1;
        else if (this.getImovel().getTopologia() == Topologia.T1_1)
            valor = valor * PercentagemT1Mais1;
        else if (this.getImovel().getTopologia() == Topologia.T2)
            valor = valor * PercentagemT2;
        else if (this.getImovel().getTopologia() == Topologia.T2_1)
            valor = valor * PercentagemT2Mais1;
        else if (this.getImovel().getTopologia() == Topologia.T3)
            valor = valor * PercentagemT3;
        else if (this.getImovel().getTopologia() == Topologia.T3_1)
            valor = valor * PercentagemT3Mais1;
        else
            valor = valor * PercentagemT4;

        if (this.getImovel().getEstado() == EstadoImovel.renovado)
            valor = valor * PercentagemEstadoRenovado;
        else if (this.getImovel().getEstado() == EstadoImovel.novo)
            valor = valor * PercentagemEstadoNovo;
        else if (this.getImovel().getEstado() == EstadoImovel.porRecuperar)
            valor = valor * PercentagemEstadoPorRecuperar;
        else if (this.getImovel().getEstado() == EstadoImovel.usado)
            valor = valor * PercentagemEstadoUsado;

        return valor;
    }


    /**
     * funcao que faz a avaliacao do negocio de venda
     * @return 1 - mau negocio
     *         2 - não muito bom negocio
     *         3 - negocio razoavel
     *         4 - bom negocio
     *         5 - excelente negocio
     */
    public Avalicao avaliacaoNegocioArrendamento()
    {
        float avaliacao = this.calculaArrendamento();
        float precoArrendamento = this.getPrecoArrendamento();
        float avaliacaoMaisDezPorcento = (float)(avaliacao * 1.1);
        float avaliacaoMaisCincoPorcento = (float)(avaliacao * 1.05);
        float avaliacaoMenosCincoPorcento = (float)(avaliacao * 0.95);
        float avaliacaoMenosDezPorcento = (float)(avaliacao * 0.9);
        if (avaliacao < precoArrendamento)
        {
            if (avaliacaoMaisDezPorcento<precoArrendamento)
                return Avalicao.MuitoMau;
            else if ((avaliacaoMaisDezPorcento>precoArrendamento) &&
                    (precoArrendamento>avaliacaoMaisCincoPorcento))
                return Avalicao.Mau;
            else
                return Avalicao.Suficiente;
        }
        else
        {
            if (precoArrendamento>=avaliacaoMenosCincoPorcento)
                return Avalicao.Suficiente;
            else if ((avaliacaoMenosCincoPorcento>precoArrendamento) &&
                    (precoArrendamento>avaliacaoMenosDezPorcento))
                return Avalicao.Bom;
            else
                return Avalicao.MuitoBom;
        }
    }

}
