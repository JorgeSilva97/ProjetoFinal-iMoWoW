package pt.ufp.lpi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @ManyToOne
    private Utilizador utilizador;
    @OneToOne
    private Concelho concelho;
    private Topologia topologia;
    private EstadoImovel estado;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataAnuncio;
    private int anoConstrução;
    private float metrosQuadrados;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;
    private boolean elevador;


}
