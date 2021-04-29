package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Arrendamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float precoArrendamento;
    @OneToOne
    private Imovel imovel;

    public float precoMetroQuadrado()
    {
        return this.imovel.getPrecoTotal()/this.imovel.getMetrosQuadrados();
    }

}
