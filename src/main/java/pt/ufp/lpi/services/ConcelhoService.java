package pt.ufp.lpi.services;

import pt.ufp.lpi.models.Historico;

import java.util.List;
import java.util.Optional;

public interface ConcelhoService
{
    List<Historico> findAllHistoricos();
    Optional<Historico> criaHistorico(Historico historico);
}
