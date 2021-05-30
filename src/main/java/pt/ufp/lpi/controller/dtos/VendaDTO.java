package pt.ufp.lpi.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO
{
    private Long imovelId;
    private float precoTotal;
}
