package pt.ufp.lpi.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.lpi.models.Distrito;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcelhoDTO
{
    private String nome;
    private float precoMedioVenda;
    private float precoMedioArrendamento;
    private Distrito distrito;
    private List<HistoricoVendaDTO> historicoVendas = new ArrayList<>();
    private List<HistoricoArrendamentoDTO> historicoArrendamentos = new ArrayList<>();

}
