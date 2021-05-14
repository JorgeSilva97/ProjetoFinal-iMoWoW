package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.HistoricoVenda;

@Repository
public interface HistoricoVendaRepository extends CrudRepository<HistoricoVenda, Long> {
}
