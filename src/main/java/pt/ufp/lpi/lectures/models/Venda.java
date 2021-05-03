package pt.ufp.lpi.lectures.models;

import lombok.Getter;
import lombok.Setter;
import pt.ufp.lpi.lectures.models.enumerado.EstadoImovel;

import javax.persistence.*;
import java.util.Date;

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

    public float precoMetroQuadrado()
    {
        return this.getPrecoTotal()/this.imovel.getMetrosQuadrados();
    }

    public float calcularVenda()
    {
        float valorConcelhoMetroQuadrado = this.getImovel().getConcelho().getPrecoMedio();
        float metrosQuadrados = this.getImovel().getMetrosQuadrados();
        float valor = valorConcelhoMetroQuadrado * metrosQuadrados;
        if (this.getImovel().isJardim())
            valor = (float)(valor * 1.13);
        if (this.getImovel().isPiscina())
            valor = (float)(valor * 1.07);
        if (this.getImovel().isGaragem())
            valor = (float)(valor * 1.05);
        if (this.getImovel().isElevador())
            valor = (float)(valor * 1.05);
        if (this.getImovel().getEstado() == EstadoImovel.renovado)
            valor = (float)(valor * 1.10);  //mais 10%
        else if (this.getImovel().getEstado() == EstadoImovel.porRecuperar)
            valor = (float)(valor * 0.40); //menos 60%
        else if (this.getImovel().getEstado() == EstadoImovel.usado)
            //if (this.getImovel().getAnoConstrução() < 2010)
             //   valor = (float)(valor * 1.05); //menos
        //se tiver piscina 7%
        //se tiver jardim 13%
        //se tiver garagem 5%
        //se tiver elevador 5%
        return valor;
    }

}
