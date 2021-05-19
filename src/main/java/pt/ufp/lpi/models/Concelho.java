package pt.ufp.lpi.models;

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
public class Concelho
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private float precoMedioVenda;
    private float precoMedioArrendamento;
    @ManyToOne
    private Distrito distrito;
    @OneToMany(mappedBy = "concelho", cascade = CascadeType.ALL)
    private List<HistoricoVenda> historicoVendas = new ArrayList<>();
    @OneToMany(mappedBy = "concelho", cascade = CascadeType.ALL)
    private List<HistoricoArrendamento> historicoArrendamentos = new ArrayList<>();

    public HistoricoVenda adicionaHistoricoVenda(HistoricoVenda historicoVenda)
    {
        if (!historicoVendas.contains(historicoVenda))
        {
            this.historicoVendas.add(historicoVenda);
            System.out.println("Adicionou histórico de venda ao concelho "+this.getNome());
        }
        return null;
    }


    public HistoricoArrendamento adicionaHistoricoArrendamento(HistoricoArrendamento historicoArrendamento)
    {
        if (!historicoArrendamentos.contains(historicoArrendamento))
        {
            this.historicoArrendamentos.add(historicoArrendamento);
            System.out.println("Adicionou histórico de arrrendamento ao concelho "+this.getNome());
        }
        return null;
    }


}
