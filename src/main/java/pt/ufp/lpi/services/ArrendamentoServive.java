package pt.ufp.lpi.services;

import java.util.Optional;

public interface ArrendamentoServive
{
    Optional<Float> getValorDeArrendamento(Long idArrendamento);
}
