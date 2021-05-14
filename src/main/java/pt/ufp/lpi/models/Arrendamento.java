package pt.ufp.lpi.models;

import lombok.*;

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

    public float precoMetroQuadrado()
    {
        return this.precoArrendamento/this.imovel.getMetrosQuadrados();
    }

    //não testado
    public float calculaArrendamento()
    {
        return 0;
    }

}
