package pt.ufp.lpi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @OneToOne
    private Concelho concelho;
    private Topologia topologia;
    private EstadoImovel estado;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataAnuncio;
    @JsonFormat(pattern = "yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDateTime anoConstrução;
    private float metrosQuadrados;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;
    private boolean elevador;

}
