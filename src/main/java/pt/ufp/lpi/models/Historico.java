package pt.ufp.lpi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import pt.ufp.lpi.models.enumerado.TipoVendaArrendamento;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Historico
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM",shape = JsonFormat.Shape.STRING)
    private LocalDateTime data;
    private float precoAntigo;
    private TipoVendaArrendamento tipo;
    @ManyToOne
    private Concelho concelho;
}
