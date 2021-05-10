package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Distrito;

@Repository
public interface DistritoRepository extends CrudRepository<Distrito, Long>
{

}
