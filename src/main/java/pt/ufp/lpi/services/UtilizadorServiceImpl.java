package pt.ufp.lpi.services;

import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.Arrendamento;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Venda;
import pt.ufp.lpi.repositories.ArrendamentoRepository;
import pt.ufp.lpi.repositories.ConcelhoRepository;
import pt.ufp.lpi.repositories.ImovelRepository;
import pt.ufp.lpi.repositories.VendaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorServiceImpl implements UtilizadorService
{

    private ConcelhoRepository concelhoRepository;
    private ImovelRepository imovelRepository;
    private VendaRepository vendaRepository;
    private ArrendamentoRepository arrendamentoRepository;

    @Override
    public List<Concelho> findAllConcelhos() {
        List<Concelho> concelhos = new ArrayList<>();
        concelhoRepository.findAll().forEach(concelhos::add);
        return concelhos;
    }

    @Override
    public Optional<Concelho> findConcelhoById(Long id) { return concelhoRepository.findById(id); }

    @Override
    public List<Imovel> findAllImoveis() {
        List<Imovel> imoveis = new ArrayList<>();
        imovelRepository.findAll().forEach(imoveis::add);
        return imoveis;
    }

    @Override
    public Optional<Imovel> findImoveloById(Long id) { return imovelRepository.findById(id); }

    @Override
    public Optional<Imovel> criaImovel(Imovel imovel)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(imovel.getId());
        if (optionalImovel.isEmpty())
            return Optional.of(imovelRepository.save(imovel));
        return Optional.empty();
    }

    @Override
    public Optional<Venda> criaVenda(Imovel imovel, float precoTotal)
    {
        Venda venda = new Venda(imovel, precoTotal);
        return Optional.of(vendaRepository.save(venda));
    }

    @Override
    public Optional<Imovel> criaArrendamento(Imovel imovel, float precoArrendamento)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(imovel.getId());
        if (optionalImovel.isPresent())
        {
            Arrendamento arrendamento = new Arrendamento();
            arrendamento.setImovel(imovel);
            arrendamento.setPrecoArrendamento(precoArrendamento);
            arrendamentoRepository.save(arrendamento);
            return Optional.of(imovelRepository.save(imovel));

        }
        return Optional.empty();
    }

    @Override
    public Optional<Float> consultaPrecoMetroQuadrado(Long idConcelho)
    {
        Optional<Concelho> optionalConcelho = concelhoRepository.findById(idConcelho);
        if (optionalConcelho.isPresent())
        {
            Concelho concelho = optionalConcelho.get();
            return Optional.of(concelho.getPrecoMedio());
        }

        return Optional.empty();
    }


}
