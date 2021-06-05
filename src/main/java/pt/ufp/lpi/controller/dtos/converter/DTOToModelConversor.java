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
                .id(imovel.getId())
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
                .elevador(imovelDTO.isElevador())
                .piscina(imovelDTO.isPiscina())
                .build();
    }

    public ConcelhoDTO converterConcelhoParaDTO(Concelho concelho)
    {
        return ConcelhoDTO.builder()
                .id(concelho.getId())
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



    public Venda converterDTOParaVenda(VendaDTO vendaDTO)
    {
        Concelho c = Concelho.builder().id(vendaDTO.getConcelhoId()).build();
        Imovel i = Imovel.builder()
                .id(vendaDTO.getImovelId())
                .utilizador(Utilizador.builder().id(vendaDTO.getUserId()).build())
                .anoConstrução(vendaDTO.getAnoConstrução())
                .estado(vendaDTO.getEstadoImovel())
                .elevador(vendaDTO.isElevador())
                .jardim(vendaDTO.isJardim())
                .garagem(vendaDTO.isGaragem())
                .piscina(vendaDTO.isPiscina())
                .metrosQuadrados(vendaDTO.getMetrosQuadrados())
                .topologia(vendaDTO.getTopologia())
                .concelho(c)
                .build();
        return Venda.builder()
                .imovel(i)
                .precoTotal(vendaDTO.getPrecoTotal())
                .build();
    }

    public Arrendamento converterDTOParaArrendamento(ArrendamentoDTO arrendamentoDTO)
    {
        Concelho c = Concelho.builder().id(arrendamentoDTO.getConcelhoId()).build();
        Imovel i = Imovel.builder()
                .id(arrendamentoDTO.getImovelId())
                .utilizador(Utilizador.builder().id(arrendamentoDTO.getUserId()).build())
                .anoConstrução(arrendamentoDTO.getAnoConstrução())
                .estado(arrendamentoDTO.getEstadoImovel())
                .elevador(arrendamentoDTO.isElevador())
                .jardim(arrendamentoDTO.isJardim())
                .garagem(arrendamentoDTO.isGaragem())
                .piscina(arrendamentoDTO.isPiscina())
                .metrosQuadrados(arrendamentoDTO.getMetrosQuadrados())
                .topologia(arrendamentoDTO.getTopologia())
                .concelho(c)
                .build();
        return Arrendamento.builder()
                .imovel(i)
                .precoArrendamento(arrendamentoDTO.getPrecoArrendamento())
                .build();
    }

    public AvaliacaoVendaDTO converterVendaParaAvaliacaoVendaDTO(Venda venda)
    {
        System.out.print(venda.getPrecoTotal());
        return AvaliacaoVendaDTO.builder().vendaId(venda.getId())
                .valorAvaliacao(venda.calcularVenda())
                .avaliacao(venda.avaliacaoNegocioVenda())
                .vendaId(venda.getId())
                .build();

    }

    public AvaliacaoArrendamentoDTO converterArrendamentoParaAvaliacaoArrendamentoDTO(Arrendamento arrendamento)
    {
        return AvaliacaoArrendamentoDTO.builder()
                .valorAvaliacaoArr(arrendamento.calculaArrendamento())
                .avaliacaoArr(arrendamento.avaliacaoNegocioArrendamento())
                .arrendamentoId(arrendamento.getId())
                .build();
    }




}
