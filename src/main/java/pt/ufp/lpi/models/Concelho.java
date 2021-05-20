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
    @Builder.Default
    private List<HistoricoVenda> historicoVendas = new ArrayList<>();
    @OneToMany(mappedBy = "concelho", cascade = CascadeType.ALL)
    @Builder.Default
    private List<HistoricoArrendamento> historicoArrendamentos = new ArrayList<>();


    public HistoricoVenda adicionaHistoricoVenda(HistoricoVenda historicoVenda)
    {
        if (!this.historicoVendas.contains(historicoVenda))
        {
            historicoVenda.setConcelho(this);
            historicoVendas.add(historicoVenda);
            System.out.println("Adicionou histórico de venda ao concelho "+this.getNome());
            return historicoVenda;
        }
        return null;
    }

    public HistoricoArrendamento adicionaHistoricoArrendamento(HistoricoArrendamento historicoArrendamento)
    {
        if (!historicoArrendamentos.contains(historicoArrendamento))
        {
            historicoArrendamento.setConcelho(this);
            this.historicoArrendamentos.add(historicoArrendamento);
            System.out.println("Adicionou histórico de arrrendamento ao concelho "+this.getNome());
            return historicoArrendamento;
        }
        return null;
    }


}
