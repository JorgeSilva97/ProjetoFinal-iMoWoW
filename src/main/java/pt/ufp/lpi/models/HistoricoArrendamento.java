package pt.ufp.lpi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoArrendamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM",shape = JsonFormat.Shape.STRING)
    private LocalDateTime data;
    private float precoAntigo;
    @ManyToOne
    private Concelho concelho;




}
