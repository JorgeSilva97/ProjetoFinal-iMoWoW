package pt.ufp.lpi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.*;
import pt.ufp.lpi.models.enumerado.Avaliacao;
import pt.ufp.lpi.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AplicacaoServiceImpl implements AplicacaoService
{
    private final ArrendamentoRepository arrendamentoRepository;
    private final VendaRepository vendaRepository;
    private final ConcelhoRepository concelhoRepository;
    private final HistoricoVendaRepository historicoVendaRepository;
    private final HistoricoArrendamentoRepository historicoArrendamentoRepository;


    @Override
    public List<HistoricoVenda> findAllHistoricosVenda()
    {
        List <HistoricoVenda> historicoVendas = new ArrayList<>();
        historicoVendaRepository.findAll().forEach(historicoVendas::add);
        return historicoVendas;
    }

    @Override
    public List<HistoricoArrendamento> findAllHistoricosArrendamento()
    {
        List <HistoricoArrendamento> historicoArrendamentos = new ArrayList<>();
        historicoArrendamentoRepository.findAll().forEach(historicoArrendamentos::add);
        return historicoArrendamentos;
    }

    @Override
    public Optional<HistoricoVenda> criaHistoricoVenda(Long idConcelho, float precoAtual, float precoAntigo)
    {
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalConcelho.isPresent())
        {
            Concelho concelho = optionalConcelho.get();
            HistoricoVenda historicoVenda = HistoricoVenda.builder()
                    .concelho(concelho)
                    .data(LocalDateTime.now())
                    .precoAntigo(precoAntigo)
                    .build();
            concelho.setPrecoMedioVenda(precoAtual);
            concelho.adicionaHistoricoVenda(historicoVenda);
            return Optional.of(historicoVendaRepository.save(historicoVenda));
        }
        return Optional.empty();
    }

    @Override
    public Optional<HistoricoArrendamento> criaHistoricoArrendamento(Long idConcelho, float precoAtual, float precoAntigo)
    {
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalConcelho.isPresent())
        {
            Concelho concelho = optionalConcelho.get();
            HistoricoArrendamento historicoArrendamento= HistoricoArrendamento.builder()
                    .concelho(concelho)
                    .data(LocalDateTime.now())
                    .precoAntigo(precoAntigo)
                    .build();
            concelho.adicionaHistoricoArrendamento(historicoArrendamento);
            concelho.setPrecoMedioArrendamento(precoAtual);
            return Optional.of(historicoArrendamentoRepository.save(historicoArrendamento));
        }
        return Optional.empty();
    }


    @Override
    public float getValorFuturoDeArrendamento(Long idArrendamento)
    {
        Optional<Arrendamento> optionalArrendamento = arrendamentoRepository.findById(idArrendamento);
        if (optionalArrendamento.isPresent())
        {
            Arrendamento arrendamento = optionalArrendamento.get();
            return arrendamento.calculaArrendamento();
        }
        return 0;
    }

    @Override
    public float getValorFuturoDaVenda (Long idVenda)
    {
        Optional<Venda> optionalVenda = vendaRepository.findById(idVenda);
        if (optionalVenda.isPresent())
        {
            Venda venda = optionalVenda.get();
            return venda.calcularVenda();
        }
        return 0;
    }

   @Override
    public Optional<Avaliacao> getAvalicaoNegocioVenda(Long idVenda)
    {
        Optional<Venda> optionalVenda = vendaRepository.findById(idVenda);
        if (optionalVenda.isPresent())
        {
            Venda venda = optionalVenda.get();
            return Optional.of(venda.avaliacaoNegocioVenda());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Avaliacao> getAvalicaoNegocioArrendamento(Long idArrendamento)
    {
        Optional<Arrendamento> optionalArrendamento = arrendamentoRepository.findById(idArrendamento);
        if (optionalArrendamento.isPresent())
        {
            Arrendamento arrendamento = optionalArrendamento.get();
            return Optional.of(arrendamento.avaliacaoNegocioArrendamento());
        }
        return Optional.empty();
    }
}
