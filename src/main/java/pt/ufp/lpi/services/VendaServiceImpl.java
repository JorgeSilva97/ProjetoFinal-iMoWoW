package pt.ufp.lpi.services;

import org.springframework.stereotype.Service;
import pt.ufp.lpi.models.Venda;
import pt.ufp.lpi.repositories.VendaRepository;

import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService
{

    private VendaRepository vendaRepository;



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
}
