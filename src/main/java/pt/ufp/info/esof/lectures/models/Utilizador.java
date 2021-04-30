package pt.ufp.info.esof.lectures.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Utilizador
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private float orcamentoLimite;
    @JsonIgnore
    @ManyToMany (mappedBy = "utilizadores", cascade = CascadeType.ALL)
    private List<Concelho> concelhos = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<Imovel> imoveis = new ArrayList<>();



    public Concelho adicionaConcelho(Concelho concelho)
    {
        if (!this.concelhos.contains(concelho))
        {
            this.concelhos.add(concelho);
            concelho.adicionaUtilizador(this);
            System.out.println("Adicionou "+concelho.getNome()+" ao Utilizador "+this.getUserName());
            return concelho;
        }
        return null;
    }

    public Imovel adicionaImovel(Imovel imovel)
    {
        if (!this.imoveis.contains(imovel))
        {
            this.imoveis.add(imovel);
            imovel.setUtilizador(this);
            System.out.println("Adicionou Imóvel ao Utilizador "+this.userName);
            return imovel;
        }
        return null;
    }

}
