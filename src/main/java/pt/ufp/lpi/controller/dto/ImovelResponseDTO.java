package pt.ufp.lpi.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import pt.ufp.lpi.models.Concelho;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelResponseDTO
{
    private Long idUser;
    private Concelho concelho;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataAnuncio;
    private int anoConstrução;
    private float metrosQuadrados;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;
    private boolean elevador;
}
