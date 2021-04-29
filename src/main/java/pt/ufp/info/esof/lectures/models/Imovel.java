package pt.ufp.info.esof.lectures.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private Date dataAnuncio;
    @JsonFormat(pattern = "yyyy",shape = JsonFormat.Shape.STRING)
    private Date anoConstrução;
    private Topologia topologia;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;

}
