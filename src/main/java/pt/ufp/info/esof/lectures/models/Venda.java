package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

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

    public float precoMetroQuadrado()
    {
        return this.getPrecoTotal()/this.imovel.getMetrosQuadrados();
    }

}
