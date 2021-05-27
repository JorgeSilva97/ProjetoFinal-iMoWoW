package pt.ufp.lpi.controller.dto;

import lombok.Data;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;

import java.util.ArrayList;
import java.util.List;

@Data
public class UtilizadorResponseDTO
{

    private String userName;
    private String email;
    private String password;
    private float orcamentoLimite;
    private List<Concelho> concelhosPreferenciais = new ArrayList<>();
    private List<Imovel> imoveis = new ArrayList<>();


}
