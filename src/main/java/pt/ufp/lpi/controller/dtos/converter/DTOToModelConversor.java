package pt.ufp.lpi.controller.dtos.converter;

import pt.ufp.lpi.controller.dtos.ConcelhoDTO;
import pt.ufp.lpi.controller.dtos.ImovelDTO;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;

public class DTOToModelConversor {

    private static DTOToModelConversor instance;

    private DTOToModelConversor(){

    }

    public static DTOToModelConversor getInstance(){
        if(instance==null){
            instance=new DTOToModelConversor();
        }

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

    public Imovel converterDTOParaImovel(ImovelDTO imovelDTO){
        return Imovel.builder()
               // .concelho.setId(imovelDTO.getConcelhoId())
               // .utilizador.setId(imovelDTO.getConcelhoId())
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
                //.historicoArrendamentos(concelho.getHistoricoArrendamentos())
                //.historicoVendas(concelho.getHistoricoVendas())
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

}
