package pt.ufp.lpi.services;

import pt.ufp.lpi.models.*;
import pt.ufp.lpi.models.enumerado.EstadoImovel;
import pt.ufp.lpi.models.enumerado.Topologia;

import java.util.List;
import java.util.Optional;

public interface UtilizadorService
{
    List<Concelho> findAllConcelhos();
    Optional<Concelho> findConcelhoById (Long id);
    Optional<Utilizador> adicionaConcelho(Long idUtilizador, Long idConcelho);
    //------------------------------------------------------------
    List<Imovel> findAllImoveis();
    Optional<Imovel> findImoveloById (Long id);
    Optional<Imovel> criaImovel(Long idUtilizador,Long idConcelho, Topologia topologia,
                                EstadoImovel estadoImovel, int ano, float metros,
                                boolean piscina, boolean jardim, boolean garagem,
                                boolean elevador);
    Optional<Imovel> criaImovel(Imovel imovel);
    Optional<Venda> criaVenda(Long idImovel, float precoTotal);
    Optional<Arrendamento> criaArrendamento(Long idImovel, float precoArrendamento);
    //------------------------------------------------------------
    float consultaPrecoMetroQuadrado(Long idConcelho);
    //------------------------------------------------------------


}
