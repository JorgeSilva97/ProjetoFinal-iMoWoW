package pt.ufp.lpi.services;

import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;

import java.util.List;
import java.util.Optional;

public interface UtilizadorService
{
    List<Concelho> findAllConcelhos();
    Optional<Concelho> findConcelhoById (Long id);
    //------------------------------------------------------------
    List<Imovel> findAllImoveis();
    Optional<Imovel> findImoveloById (Long id);
    Optional<Imovel> criaImovel(Imovel imovel);
    Optional<Imovel> criaVenda(Imovel imovel, float precoTotal);
    Optional<Imovel> criaArrendamento(Imovel imovel, float precoArrendamento);
    //------------------------------------------------------------
    Optional<Float> consultaPrecoMetroQuadrado(Long idConcelho);
    //------------------------------------------------------------


}
