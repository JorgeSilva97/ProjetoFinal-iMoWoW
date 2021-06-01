package pt.ufp.lpi.controller.dtos.converter;

import pt.ufp.lpi.controller.dtos.*;
import pt.ufp.lpi.models.*;

import java.util.stream.Collectors;

public class DTOToModelConversor
{
    private static DTOToModelConversor instance;

    private DTOToModelConversor(){ }

    public static DTOToModelConversor getInstance()
    {
        if(instance==null)
            instance=new DTOToModelConversor();
        return instance;
    }

    public ImovelDTO converterImovelParaDTO(Imovel imovel)
    {
        return ImovelDTO.builder()
                .concelhoId(imovel.getConcelho().getId())
                .userId(imovel.getUtilizador().getId())
                .anoConstrução(imovel.getAnoConstrução())
                .estadoImovel(imovel.getEstado())
                .metrosQuadrados(imovel.getMetrosQuadrados())
                .elevador(imovel.isElevador())
                .garagem(imovel.isGaragem())
                .jardim(imovel.isJardim())
                .piscina(imovel.isPiscina())
                .topologia(imovel.getTopologia())
                .build();
    }

    public Imovel converterDTOParaImovel(ImovelDTO imovelDTO)
    {
        Concelho concelho1 = Concelho.builder().id(imovelDTO.getConcelhoId()).build();
        Utilizador user = Utilizador.builder().id(imovelDTO.getUserId()).build();
        return Imovel.builder()
                .concelho(concelho1)
                .utilizador(user)
                .estado(imovelDTO.getEstadoImovel())
                .metrosQuadrados(imovelDTO.getMetrosQuadrados())
                .anoConstrução(imovelDTO.getAnoConstrução())
                .topologia(imovelDTO.getTopologia())
                .garagem(imovelDTO.isGaragem())
                .jardim(imovelDTO.isJardim())
                .elevador(imovelDTO.isGaragem())
                .piscina(imovelDTO.isPiscina())
                .build();
    }

    public ConcelhoDTO converterConcelhoParaDTO(Concelho concelho)
    {
        return ConcelhoDTO.builder()
                .distrito(concelho.getDistrito())
                .nome(concelho.getNome())
                .historicoArrendamentos(concelho.getHistoricoArrendamentos().stream().map(historicoArrendamento ->
                {
                    HistoricoArrendamentoDTO historicoArrendamentoDTO = HistoricoArrendamentoDTO.builder()
                            .concelho(historicoArrendamento.getConcelho())
                            .data(historicoArrendamento.getData())
                            .precoAntigo(historicoArrendamento.getPrecoAntigo())
                            .build();
                    return historicoArrendamentoDTO;
                }).collect(Collectors.toList()))
                .historicoVendas(concelho.getHistoricoVendas().stream().map(historicoVenda ->
                {
                    HistoricoVendaDTO historicoVendaDTO = HistoricoVendaDTO.builder()
                            .concelho(historicoVenda.getConcelho())
                            .data(historicoVenda.getData())
                            .precoAntigo(historicoVenda.getPrecoAntigo())
                            .build();
                    return historicoVendaDTO;
                }).collect(Collectors.toList()))
                .precoMedioArrendamento(concelho.getPrecoMedioArrendamento())
                .precoMedioVenda(concelho.getPrecoMedioVenda())
                .build();
    }

    public Concelho converterDTOParaConcelho(ConcelhoDTO concelhoDTO)
    {
        return Concelho.builder()
                .distrito(concelhoDTO.getDistrito())
                .nome(concelhoDTO.getNome())
                .precoMedioArrendamento(concelhoDTO.getPrecoMedioArrendamento())
                .precoMedioVenda(concelhoDTO.getPrecoMedioVenda())
                //.historicoArrendamentos(concelhoDTO.getHistoricoArrendamentos())
                //.historicoVendas(concelhoDTO.getHistoricoVendas())
                .build();
    }

    public VendaDTO converterVendaParaDTO(Venda venda)
    {
        return VendaDTO.builder()
                .imovelId(venda.getImovel().getId())
                .precoTotal(venda.getPrecoTotal())
                .build();
    }

    public Venda converterDTOParaVenda(VendaDTO vendaDTO)
    {
        Imovel i = Imovel.builder().id(vendaDTO.getImovelId()).build();
        return Venda.builder()
                .imovel(i)
                .precoTotal(vendaDTO.getPrecoTotal())
                .build();
    }

    public ArrendamentoDTO converterArrendamentoParaDTO(Arrendamento arrendamento)
    {
        return ArrendamentoDTO.builder()
                .imovelId(arrendamento.getId())
                .precoArrendamento(arrendamento.getPrecoArrendamento())
                .build();
    }

    public Arrendamento converterDTOParaArrendamento(ArrendamentoDTO arrendamentoDTO)
    {
        Imovel i = Imovel.builder().id(arrendamentoDTO.getImovelId()).build();
        return Arrendamento.builder()
                .imovel(i)
                .precoArrendamento(arrendamentoDTO.getPrecoArrendamento())
                .build();
    }

    public ValorVendaDTO converterValorVendaParaDTO(Venda venda)
    {
        return ValorVendaDTO.builder()
                .precoTotal(venda.getPrecoTotal())
                .build();
    }

}
