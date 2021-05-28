package pt.ufp.lpi.controller.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pt.ufp.lpi.models.Concelho;

import java.time.LocalDateTime;

@Data
public class HistoricoArrendamentoDTO
{
    @JsonFormat(pattern = "yyyy-MM",shape = JsonFormat.Shape.STRING)
    private LocalDateTime data;
    private float precoAntigo;
    private Concelho concelho;
}
