package pt.ufp.lpi.lectures.services;

import java.util.Optional;

public interface VendaService
{
    Optional<Float> getValorFuturoDaVenda(Long idVenda);
}
