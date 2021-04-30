package pt.ufp.lpi.lectures.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import pt.ufp.lpi.lectures.models.enumerado.EstadoImovel;
import pt.ufp.lpi.lectures.models.enumerado.Topologia;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
//@Builder
public class Imovel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private Utilizador utilizador;
    @OneToOne
    private Concelho concelho;
    private Topologia topologia;
    private EstadoImovel estado;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private Date dataAnuncio;
    @JsonFormat(pattern = "yyyy",shape = JsonFormat.Shape.STRING)
    private Date anoConstrução;
    private float metrosQuadrados;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;
    private boolean elevador;
}
