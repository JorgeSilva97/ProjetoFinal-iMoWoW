package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.HistoricoArrendamento;

@Repository
public interface HistoricoArrendamentoRepository extends CrudRepository<HistoricoArrendamento, Long> {
}
