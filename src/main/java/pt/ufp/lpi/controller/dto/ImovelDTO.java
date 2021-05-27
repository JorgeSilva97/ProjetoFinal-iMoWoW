package pt.ufp.lpi.controller.dto;

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
public class ImovelDTO {
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


    public static void main(String[] args) {
        System.out.println(EstadoImovel.usado.ordinal());
    }
}
