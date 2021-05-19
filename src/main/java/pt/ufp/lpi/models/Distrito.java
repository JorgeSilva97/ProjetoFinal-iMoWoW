package pt.ufp.lpi.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
