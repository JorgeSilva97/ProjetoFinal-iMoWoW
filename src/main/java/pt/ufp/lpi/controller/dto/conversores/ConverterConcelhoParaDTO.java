package pt.ufp.lpi.controller.dto.conversores;

import pt.ufp.lpi.controller.dto.ConcelhoResponseDTO;
import pt.ufp.lpi.controller.dto.HistoricoArrendamentoDTO;
import pt.ufp.lpi.controller.dto.HistoricoVendaDTO;
import pt.ufp.lpi.models.Concelho;

import java.util.stream.Collectors;

public class ConverterConcelhoParaDTO implements Conversor<ConcelhoResponseDTO, Concelho>
{
    @Override
    public ConcelhoResponseDTO converter(Concelho concelho)
    {
        ConcelhoResponseDTO responseDTO = new ConcelhoResponseDTO();
        responseDTO.setNome(concelho.getNome());
        responseDTO.setPrecoMedioVenda(concelho.getPrecoMedioVenda());
        responseDTO.setPrecoMedioArrendamento(concelho.getPrecoMedioArrendamento());
        responseDTO.setDistrito(concelho.getDistrito());
        responseDTO.setHistoricoVendas(concelho.getHistoricoVendas().stream().map(historicoVenda -> {
            HistoricoVendaDTO historicoVendaDTO = new HistoricoVendaDTO();
            historicoVendaDTO.setPrecoAntigo(historicoVenda.getPrecoAntigo());
            historicoVendaDTO.setData(historicoVenda.getData());
            return historicoVendaDTO;
        }).collect(Collectors.toList()));
        responseDTO.setHistoricoArrendamentos(concelho.getHistoricoArrendamentos().stream().map(historicoArrendamento ->
        {
            HistoricoArrendamentoDTO historicoArrendamentoDTO = new HistoricoArrendamentoDTO();
            historicoArrendamentoDTO.setPrecoAntigo(historicoArrendamento.getPrecoAntigo());
            historicoArrendamentoDTO.setData(historicoArrendamento.getData());
            return historicoArrendamentoDTO;
        }).collect(Collectors.toList()));


        return responseDTO;
    }
}
