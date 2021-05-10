package pt.ufp.lpi.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Concelho
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private float precoMedio;
    @ManyToOne
    private Distrito distrito;
    @OneToMany(mappedBy = "concelho", cascade = CascadeType.ALL)
    private List<Historico> historicos = new ArrayList<>();

}
