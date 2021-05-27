package pt.ufp.lpi.controller.dto.conversores;

import pt.ufp.lpi.controller.dto.ImovelDTO;
//import pt.ufp.lpi.controller.dto.ImovelResponseDTO;
import pt.ufp.lpi.models.Imovel;

public class ConverterImovelParaDTO implements Conversor<ImovelDTO, Imovel>
{
    @Override
    public ImovelDTO converter(Imovel imovel) {
        //ImovelDTO responseDTO = new ImovelDTO();
        //responseDTO.setConcelho(imovel.getConcelho());
        //responseDTO.setIdUser(imovel.getUtilizador().getId());
        //responseDTO.setAnoConstrução(imovel.getAnoConstrução());


        //return responseDTO;

     return null;
    }
}
