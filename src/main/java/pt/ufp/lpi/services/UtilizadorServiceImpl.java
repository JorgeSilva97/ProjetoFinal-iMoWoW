package pt.ufp.lpi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.*;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;
import pt.ufp.lpi.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilizadorServiceImpl implements UtilizadorService
{

    private final UtilizadorRepository utilizadorRepository;
    private final ConcelhoRepository concelhoRepository;
    private final ImovelRepository imovelRepository;
    private final VendaRepository vendaRepository;
    private final ArrendamentoRepository arrendamentoRepository;


    @Override
    public List<Concelho> findAllConcelhos() {
        List<Concelho> concelhos = new ArrayList<>();
        concelhoRepository.findAll().forEach(concelhos::add);
        return concelhos;
    }

    @Override
    public Optional<Concelho> findConcelhoById(Long id) { return concelhoRepository.findById(id); }

    @Override
    public Optional<Utilizador> adicionaConcelho(Long idUtilizador, Long idConcelho)
    {
        Optional<Utilizador> optionalUtilizador = utilizadorRepository.findById(idUtilizador);
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalUtilizador.isPresent() && optionalConcelho.isPresent())
        {
            Utilizador utilizador = optionalUtilizador.get();
            Concelho concelho = optionalConcelho.get();
            utilizador.adicionaConcelho(concelho);
            return Optional.of(utilizadorRepository.save(utilizador));
        }
        return Optional.empty();
    }

    @Override
    public List<Imovel> findAllImoveis()
    {
        List<Imovel> imoveis = new ArrayList<>();
        imovelRepository.findAll().forEach(imoveis::add);
        return imoveis;
    }

    @Override
    public Optional<Imovel> findImoveloById(Long id) { return imovelRepository.findById(id); }

    @Override
    public List<Imovel> findImoveisByUser(Long userId)
    {
        List<Imovel> imoveis = new ArrayList<>();
        Optional<Utilizador> optionalUtilizador = utilizadorRepository.findById(userId);
        if (optionalUtilizador.isPresent())
        {
            Utilizador utilizador = optionalUtilizador.get();
            for (Imovel i : utilizador.getImoveis())
                imoveis.add(i);
            return imoveis;
        }
        return null;
    }

    @Override
    public Optional<Imovel> criaImovel(Long idUtilizador, Long idConcelho, Topologia topologia,
                                       EstadoImovel estadoImovel, int ano, float metros,
                                       boolean piscina, boolean jardim, boolean garagem,
                                       boolean elevador)
    {
        Optional<Utilizador> optionalUtilizador = utilizadorRepository.findById(idUtilizador);
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalUtilizador.isPresent() && optionalConcelho.isPresent())
        {
            Utilizador utilizador = optionalUtilizador.get();
            Concelho concelho = optionalConcelho.get();
            Imovel imovel = Imovel.builder()
                    .utilizador(utilizador)
                    .anoConstrução(ano)
                    .concelho(concelho)
                    .dataAnuncio(LocalDateTime.now())
                    .elevador(elevador)
                    .estado(estadoImovel)
                    .garagem(garagem)
                    .jardim(jardim)
                    .piscina(piscina)
                    .metrosQuadrados(metros)
                    .topologia(topologia)
                    .build();
            utilizador.adicionaImovel(imovel);
            return Optional.of(imovelRepository.save(imovel));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Imovel> criaImovel(Imovel imovel) {
        return criaImovel(imovel.getUtilizador().getId(),imovel.getConcelho().getId(),imovel.getTopologia(),
                imovel.getEstado(),imovel.getAnoConstrução(),imovel.getMetrosQuadrados(),imovel.isPiscina(),
                imovel.isJardim(),imovel.isGaragem(),imovel.isElevador());
    }

    @Override
    public Optional<Venda> criaVenda(Long idImovel, float precoTotal)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(idImovel);
        if (optionalImovel.isPresent())
        {
            Imovel imovel = optionalImovel.get();
            Venda venda = Venda.builder()
                    .imovel(imovel)
                    .precoTotal(precoTotal)
                    .build();
            return Optional.of(vendaRepository.save(venda));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Venda> criaVenda(Venda venda) {
        return criaVenda(venda.getImovel().getId(), venda.getPrecoTotal());
    }

    @Override
    public Optional<Arrendamento> criaArrendamento(Long idImovel, float precoArrendamento)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(idImovel);
        if (optionalImovel.isPresent())
        {
            Imovel imovel = optionalImovel.get();
            Arrendamento arrendamento = Arrendamento.builder()
                    .precoArrendamento(precoArrendamento)
                    .imovel(imovel)
                    .build();
            return Optional.of(arrendamentoRepository.save(arrendamento));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Arrendamento> criaArrendamento(Arrendamento arrendamento) {
        return criaArrendamento(arrendamento.getImovel().getId(), arrendamento.getPrecoArrendamento());
    }

    @Override
    public float consultaPrecoMetroQuadrado(Long idConcelho)
    {
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalConcelho.isPresent())
        {
            Concelho concelho = optionalConcelho.get();
            return concelho.getPrecoMedioVenda();
        }
        return 0;
    }


}
