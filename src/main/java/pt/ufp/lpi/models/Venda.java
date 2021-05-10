package pt.ufp.lpi.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Venda
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Imovel imovel;
    private float precoTotal;

    private float PercentagemGaragem = 1.05F; //se tiver garagem 5%
    private float PercentagemPiscina = 1.07F; //se tiver piscina 7%
    private float PercentagemJardim = 1.13F; //se tiver jardim 13%
    private float PercentagemElevador = 1.05F; //se tiver elevador 5%
    private float PercentagemEstadoRenovado = 1.13F; //mais 13%
    private float PercentagemEstadoNovo = 1.25F; //mais 25%
    private float PercentagemEstadoPorRecuperar = 0.40F; //menos 60%
    private float PercentagemEstadoUsado = 0.90F; //menos 10%
    private float PercentagemT1 = 0.09F;
    private float PercentagemT1Mais1 = 0.10F;
    private float PercentagemT2 = 0.18F;
    private float PercentagemT2Mais1 = 0.20F;
    private float PercentagemT3 = 0.27F;
    private float PercentagemT3Mais1 = 0.28F;
    private float PercentagemT4 = 0.36F;



    public float precoMetroQuadrado()
    {
        return this.getPrecoTotal()/this.imovel.getMetrosQuadrados();
    }

    public float calcularVenda()
    {
        float valorConcelhoMetroQuadrado = this.getImovel().getConcelho().getPrecoMedio();
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

        if (this.getImovel().getEstado() == EstadoImovel.renovado) //mais 13%
            valor = valor * PercentagemEstadoRenovado;
        else if (this.getImovel().getEstado() == EstadoImovel.novo) //mais 25%
            valor = valor * PercentagemEstadoNovo;
        else if (this.getImovel().getEstado() == EstadoImovel.porRecuperar) //menos 60%
            valor = valor * PercentagemEstadoPorRecuperar;
        else if (this.getImovel().getEstado() == EstadoImovel.usado) //menos 10%
            valor = valor * PercentagemEstadoUsado;
            //if (this.getImovel().getAnoConstrução() < 2010)
             //   valor = (float)(valor * 1.05); //menos

        if (this.getImovel().isGaragem()) //se tiver garagem 5%
            valor = valor * PercentagemGaragem;
        if (this.getImovel().isPiscina()) //se tiver piscina 7%
            valor = valor * PercentagemPiscina;
        if (this.getImovel().isJardim()) //se tiver jardim 13%
            valor = valor * PercentagemJardim;
        if (this.getImovel().isElevador()) //se tiver elevador 5%
            valor = valor * PercentagemElevador;
        return valor;
    }

}
