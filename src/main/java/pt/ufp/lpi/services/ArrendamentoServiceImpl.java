package pt.ufp.lpi.services;

import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.Arrendamento;
import pt.ufp.lpi.repositories.ArrendamentoRepository;

import java.util.Optional;

@Service
public class ArrendamentoServiceImpl implements ArrendamentoServive
{

    private ArrendamentoRepository arrendamentoRepository;

    @Override
    public Optional<Float> getValorDeArrendamento(Long idArrendamento)
    {
        Optional<Arrendamento> optionalArrendamento = arrendamentoRepository.findById(idArrendamento);
        if (optionalArrendamento.isPresent())
        {
            Arrendamento arrendamento = optionalArrendamento.get();
            return Optional.of(arrendamento.calculaArrendamento());
        }
        return Optional.empty();
    }
}
