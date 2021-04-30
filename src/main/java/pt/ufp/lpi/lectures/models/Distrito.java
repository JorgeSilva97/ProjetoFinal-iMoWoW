package pt.ufp.lpi.lectures.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Distrito
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "distrito", cascade = CascadeType.ALL)
    private List<Concelho> concelhos = new ArrayList<>();


    public Concelho adicionaConcelho(Concelho concelho)
    {
        if (!this.concelhos.contains(concelho))
        {
            this.concelhos.add(concelho);
            concelho.setDistrito(this);
            System.out.println("Adicionou "+concelho.getNome()+" ao Distrito "+this.nome);
            return concelho;
        }
        return null;
    }


}
