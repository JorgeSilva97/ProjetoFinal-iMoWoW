package pt.ufp.lpi.services;

import java.util.Optional;

public interface VendaService
{
    Optional<Float> getValorFuturoDaVenda(Long idVenda);
}
