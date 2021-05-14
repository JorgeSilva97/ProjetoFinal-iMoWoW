package pt.ufp.lpi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.*;
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
    public List<HistoricoVenda> findAllHistoricos()
    {
        List <HistoricoVenda> historicoVendas = new ArrayList<>();
        historicoVendaRepository.findAll().forEach(historicoVendas::add);
        return historicoVendas;
    }

    @Override
    public Optional<HistoricoVenda> criaHistoricoVenda(Long idConcelho, float precoAtual)
    {
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalConcelho.isEmpty())
        {
            Concelho concelho = optionalConcelho.get();
            HistoricoVenda historicoVenda = HistoricoVenda.builder()
                    .concelho(concelho)
                    .data(LocalDateTime.now())
                    .precoAntigo(precoAtual)
                    .build();
            return Optional.of(historicoVendaRepository.save(historicoVenda));
        }
        return Optional.empty();
    }

    @Override
    public Optional<HistoricoArrendamento> criaHistoricoArrendamento(Long idConcelho, float precoAtual)
    {
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalConcelho.isEmpty())
        {
            Concelho concelho = optionalConcelho.get();
            HistoricoArrendamento historicoArrendamento= HistoricoArrendamento.builder()
                    .concelho(concelho)
                    .data(LocalDateTime.now())
                    .precoAntigo(precoAtual)
                    .build();
            return Optional.of(historicoArrendamentoRepository.save(historicoArrendamento));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Float> getValorFuturoDeArrendamento(Long idArrendamento)
    {
        Optional<Arrendamento> optionalArrendamento = arrendamentoRepository.findById(idArrendamento);
        if (optionalArrendamento.isPresent())
        {
            Arrendamento arrendamento = optionalArrendamento.get();
            return Optional.of(arrendamento.calculaArrendamento());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Float> getValorFuturoDaVenda (Long idVenda)
    {
        Optional<Venda> optionalVenda = vendaRepository.findById(idVenda);
        if (optionalVenda.isPresent())
        {
            Venda venda = optionalVenda.get();
            return Optional.of(venda.calcularVenda());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getAvalicaoNegocio (Long idVenda)
    {
        Optional<Venda> optionalVenda = vendaRepository.findById(idVenda);
        if (optionalVenda.isPresent())
        {
            Venda venda = optionalVenda.get();
            return Optional.of(venda.avaliacaoNegocioVenda());
        }
        return Optional.empty();
    }
}
