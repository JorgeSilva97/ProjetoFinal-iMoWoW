package pt.ufp.lpi.lectures.services;

import pt.ufp.lpi.lectures.models.Concelho;
import pt.ufp.lpi.lectures.models.Imovel;

import java.util.List;
import java.util.Optional;

public interface UtilizadorService
{
    List<Concelho> findAllConcelhos();
    Optional<Concelho> findConcelhoByNome (String nomeConcelho);
    Optional<Concelho> findConcelhoById (Long id);
    //------------------------------------------------------------
    List<Imovel> findAllImoveis();
    Optional<Imovel> findImoveloById (Long id);
    Optional<Imovel> criaImovel(Imovel imovel);
}
