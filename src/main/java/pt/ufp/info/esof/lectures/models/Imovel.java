package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
public class Imovel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Utilizador utilizador;
    private float precoTotal;
    private float metrosQuadrados;
    private Date dataAnuncio;
    private Date anoConstrucao;
    private Topologia topologia;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;

}
