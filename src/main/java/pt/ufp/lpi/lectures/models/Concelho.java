package pt.ufp.lpi.lectures.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;

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

}
