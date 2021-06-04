package pt.ufp.lpi.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrendamentoDTO
{
    private Long id;
    private Long imovelId;
    private Long userId;
    private Long concelhoId;
    private Topologia topologia;
    private EstadoImovel estadoImovel;
    private int anoConstrução;
    private float metrosQuadrados;
    private boolean piscina;
    private boolean jardim;
    private boolean garagem;
    private boolean elevador;
    private float precoArrendamento;
}
