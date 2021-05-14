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
    private float precoMedio;
    @ManyToOne
    private Distrito distrito;
    @OneToMany(mappedBy = "concelho", cascade = CascadeType.ALL)
    private List<HistoricoVenda> historicoVendas = new ArrayList<>();
    @OneToMany(mappedBy = "concelho", cascade = CascadeType.ALL)
    private List<HistoricoArrendamento> historicoArrendamentos = new ArrayList<>();

}
