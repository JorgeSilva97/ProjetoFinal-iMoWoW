package pt.ufp.info.esof.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.models.Distrito;

@Repository
public interface DistritoRepository extends CrudRepository<Distrito, Long>
{

}
