package pt.ufp.lpi.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.lpi.models.enumerado.EstadoImovel;

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
    private float Garagem = 1.05F;

    public float precoMetroQuadrado()
    {
        return this.getPrecoTotal()/this.imovel.getMetrosQuadrados();
    }

    public float calcularVenda()
    {
        float valorConcelhoMetroQuadrado = this.getImovel().getConcelho().getPrecoMedio();
        float metrosQuadrados = this.getImovel().getMetrosQuadrados();
        float valor = valorConcelhoMetroQuadrado * metrosQuadrados;
        if (this.getImovel().isGaragem()) //se tiver garagem 5%
            valor = (float)(valor * 1.05);
        if (this.getImovel().isPiscina()) //se tiver piscina 7%
            valor = (float)(valor * 1.07);
        if (this.getImovel().isJardim()) //se tiver jardim 13%
            valor = (float)(valor * 1.13);
        if (this.getImovel().isElevador()) //se tiver elevador 5%
            valor = (float)(valor * 1.05);
        if (this.getImovel().getEstado() == EstadoImovel.renovado) //mais 13%
            valor = (float)(valor * 1.13);
        else if (this.getImovel().getEstado() == EstadoImovel.novo) //mais 25%
            valor = (float)(valor * 1.25);
        else if (this.getImovel().getEstado() == EstadoImovel.porRecuperar) //menos 60%
            valor = (float)(valor * 0.40);
        else if (this.getImovel().getEstado() == EstadoImovel.usado) //menos 10%
            valor = (float)(valor * 0.90);
            //if (this.getImovel().getAnoConstrução() < 2010)
             //   valor = (float)(valor * 1.05); //menos
        //se tiver piscina 7%
        //se tiver jardim 13%
        //se tiver garagem 5%
        //se tiver elevador 5%
        return valor;
    }

}
