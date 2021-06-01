package pt.ufp.lpi.services;

import pt.ufp.lpi.models.HistoricoArrendamento;
import pt.ufp.lpi.models.HistoricoVenda;
import pt.ufp.lpi.models.enumerado.Avaliacao;

import java.util.List;
import java.util.Optional;

public interface AplicacaoService
{
    List<HistoricoVenda> findAllHistoricosVenda();
    List<HistoricoArrendamento> findAllHistoricosArrendamento();
    Optional<HistoricoVenda> criaHistoricoVenda(Long idConcelho, float precoAtual, float precoAntigo);
    Optional<HistoricoArrendamento> criaHistoricoArrendamento(Long idConcelho, float precoAtual, float precoAntigo);
    float getValorFuturoDeArrendamento(Long idArrendamento);
    float getValorFuturoDaVenda(Long idVenda);
    Optional<Avaliacao> getAvalicaoNegocioVenda(Long idVenda);
    Optional<Avaliacao> getAvalicaoNegocioArrendamento(Long idArrendamento);
}
