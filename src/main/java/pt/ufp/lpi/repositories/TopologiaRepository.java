package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.enumerado.Topologia;

@Repository
public interface TopologiaRepository extends CrudRepository<Topologia, Long> {
}
