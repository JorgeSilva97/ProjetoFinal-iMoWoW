package pt.ufp.lpi.controller.dto;

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


    public ImovelDTO converterImovelToDTO(Imovel imovel){
        return ImovelDTO.builder()
                .concelhoId(imovel.getConcelho().getId())
                .anoConstrução(imovel.getAnoConstrução())

                .build();
    }

    public Imovel converterDTOToImovel(ImovelDTO imovelDTO){
        return Imovel.builder()
                //.concelhoId(imovel.getConcelho().getId())

                .anoConstrução(imovelDTO.getAnoConstrução())

                .build();
    }
}
