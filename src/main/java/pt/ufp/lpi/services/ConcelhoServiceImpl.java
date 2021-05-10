package pt.ufp.lpi.services;

import pt.ufp.lpi.models.Historico;
import pt.ufp.lpi.repositories.HistoricoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcelhoServiceImpl implements ConcelhoService
{
    private HistoricoRepository historicoRepository;

    @Override
    public List<Historico> findAllHistoricos()
    {
        List <Historico> historicos = new ArrayList<>();
        historicoRepository.findAll().forEach(historicos::add);
        return historicos;
    }

    @Override
    public Optional<Historico> criaHistorico(Historico historico)
    {
        Optional<Historico> optionalHistorico = historicoRepository.findById(historico.getId());
        if (optionalHistorico.isEmpty())
            return Optional.of(historicoRepository.save(historico));
        return Optional.empty();
    }


}
