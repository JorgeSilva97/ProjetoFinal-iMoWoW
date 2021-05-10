package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Arrendamento;

@Repository
public interface ArrendamentoRepository extends CrudRepository<Arrendamento,Long> {
}
