package pt.ufp.lpi.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.lectures.models.Distrito;

@Repository
public interface DistritoRepository extends CrudRepository<Distrito, Long>
{

}
