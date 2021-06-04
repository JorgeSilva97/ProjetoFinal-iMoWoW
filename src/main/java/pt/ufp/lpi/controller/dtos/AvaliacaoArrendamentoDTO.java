package pt.ufp.lpi.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.lpi.models.enumerado.Avaliacao;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoArrendamentoDTO
{
    private Long arrendamentoId;
    private Float valorAvaliacao;
    private Avaliacao avaliacao;
}
