package pt.ufp.lpi.services;

import pt.ufp.lpi.models.Arrendamento;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Venda;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import java.time.LocalDateTime;
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
    /*Optional<Imovel> criaImovel(Long idImovel, Concelho concelho, Topologia top,
                                EstadoImovel estado, LocalDateTime ano, float metros,
                                boolean piscina, boolean jardim, boolean garagem,
                                boolean elevador);*/
    Optional<Venda> criaVenda(Long idImovel, Venda venda);
    Optional<Arrendamento> criaArrendamento(Long idImovel, Arrendamento precoArrendamento);
    //------------------------------------------------------------
    Optional<Float> consultaPrecoMetroQuadrado(Long idConcelho);
    //------------------------------------------------------------


}
