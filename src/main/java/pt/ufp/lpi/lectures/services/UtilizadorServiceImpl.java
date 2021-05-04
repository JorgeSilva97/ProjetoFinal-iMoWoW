package pt.ufp.lpi.lectures.services;

import org.springframework.stereotype.Service;
import pt.ufp.lpi.lectures.models.Arrendamento;
import pt.ufp.lpi.lectures.models.Concelho;
import pt.ufp.lpi.lectures.models.Imovel;
import pt.ufp.lpi.lectures.models.Venda;
import pt.ufp.lpi.lectures.repositories.ConcelhoRepository;
import pt.ufp.lpi.lectures.repositories.ImovelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorServiceImpl implements UtilizadorService
{

    private ConcelhoRepository concelhoRepository;
    private ImovelRepository imovelRepository;

    @Override
    public List<Concelho> findAllConcelhos() {
        List<Concelho> concelhos = new ArrayList<>();
        concelhoRepository.findAll().forEach(concelhos::add);
        return concelhos;
    }

    @Override
    public Optional<Concelho> findConcelhoByNome(String nomeConcelho){return concelhoRepository.findByNome(nomeConcelho);}

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
    public Optional<Imovel> adicionaVendaAoImovel(Long imovelId, Venda venda)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(imovelId);
        if (optionalImovel.isPresent())
        {
            Imovel imovel = optionalImovel.get();
            venda.setImovel(imovel);
            return Optional.of(imovel);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Imovel> adicionaArrendamentoAoImovel(Long imovelId, Arrendamento arrendamento)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(imovelId);
        if (optionalImovel.isPresent())
        {
            Imovel imovel = optionalImovel.get();
            arrendamento.setImovel(imovel);
            return Optional.of(imovel);
        }
        return Optional.empty();
    }

    //consulta informação m^2 de cada concelho


}
