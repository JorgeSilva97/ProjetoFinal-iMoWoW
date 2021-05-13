package pt.ufp.lpi.services;

import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.Arrendamento;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Venda;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;
import pt.ufp.lpi.repositories.ArrendamentoRepository;
import pt.ufp.lpi.repositories.ConcelhoRepository;
import pt.ufp.lpi.repositories.ImovelRepository;
import pt.ufp.lpi.repositories.VendaRepository;

import java.time.LocalDateTime;
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

    /*@Override
    public Optional<Imovel> criaImovel(Long idImovel, Concelho concelho, Topologia top, EstadoImovel estado,
                                       LocalDateTime ano, float metros, boolean piscina, boolean jardim,
                                       boolean garagem, boolean elevador)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(idImovel);
        if (optionalImovel.isEmpty())
        {
            Imovel imovel = optionalImovel.get();
            imovel.setConcelho(concelho);
            imovel.setTopologia(top);
            imovel.setEstado(estado);
            imovel.setAnoConstrução(ano);
            imovel.setMetrosQuadrados(metros);
            imovel.setPiscina(piscina);
            imovel.setJardim(jardim);
            imovel.setGaragem(garagem);
            imovel.setElevador(elevador);
            imovel.setDataAnuncio(LocalDateTime.now());
            return Optional.of(imovelRepository.save(imovel));
        }
        return Optional.empty();
    }*/

    @Override
    public Optional<Imovel> criaImovel(Imovel imovel)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(imovel.getId());
        if (optionalImovel.isPresent())
            return Optional.of(imovelRepository.save(imovel));
        return Optional.empty();
    }



    @Override
    public Optional<Venda> criaVenda(Long idImovel, Venda venda)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(idImovel);
        if (optionalImovel.isPresent())
        {
            Imovel imovel = optionalImovel.get();
            venda.setImovel(imovel);
            return Optional.of(vendaRepository.save(venda));
        }
        return Optional.empty();

    }

    @Override
    public Optional<Arrendamento> criaArrendamento(Long idImovel, Arrendamento arrendamento)
    {
        Optional<Imovel> optionalImovel = imovelRepository.findById(idImovel);
        if (optionalImovel.isPresent())
        {
            Imovel imovel = optionalImovel.get();
            arrendamento.setImovel(imovel);
            return Optional.of(arrendamentoRepository.save(arrendamento));

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
