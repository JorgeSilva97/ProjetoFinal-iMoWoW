package pt.ufp.lpi.services;

import pt.ufp.lpi.models.HistoricoArrendamento;
import pt.ufp.lpi.models.HistoricoVenda;
import pt.ufp.lpi.models.enumerado.Avalicao;

import java.util.List;
import java.util.Optional;

public interface AplicacaoService
{
    List<HistoricoVenda> findAllHistoricos();
    Optional<HistoricoVenda> criaHistoricoVenda(Long idConcelho, float precoAtual);
    Optional<HistoricoArrendamento> criaHistoricoArrendamento(Long idConcelho, float precoAtual);
    Optional<Float> getValorFuturoDeArrendamento(Long idArrendamento);
    Optional<Float> getValorFuturoDaVenda(Long idVenda);
    Optional<Avalicao> getAvalicaoNegocio (Long idVenda);
}
