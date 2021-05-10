package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Historico;

@Repository
public interface HistoricoRepository extends CrudRepository<Historico, Long> {
}
