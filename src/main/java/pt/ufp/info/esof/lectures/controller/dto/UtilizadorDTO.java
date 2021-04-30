package pt.ufp.info.esof.lectures.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pt.ufp.info.esof.lectures.models.Concelho;
import pt.ufp.info.esof.lectures.models.Imovel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private float orcamentoLimite;



}
