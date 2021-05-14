package pt.ufp.lpi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilizador
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private float orcamentoLimite;
    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL)
    private List<Concelho> concelhosPreferenciais = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<Imovel> imoveis = new ArrayList<>();



    public Concelho adicionaConcelho(Concelho concelho)
    {
        if (!this.concelhosPreferenciais.contains(concelho))
        {
            this.concelhosPreferenciais.add(concelho);
           // concelho.adicionaUtilizador(this);
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
