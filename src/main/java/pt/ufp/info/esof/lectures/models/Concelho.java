package pt.ufp.info.esof.lectures.models;

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
    @ManyToMany
    private List<Utilizador> utilizadores = new ArrayList<>();


    public Utilizador adicionaUtilizador(Utilizador utilizador)
    {
        if (!this.utilizadores.contains(utilizador))
        {
            this.utilizadores.add(utilizador);
            System.out.println("Adicionou "+utilizador.getUserName()+" ao Concelho "+this.nome);
            return utilizador;
        }
        return null;
    }
}
