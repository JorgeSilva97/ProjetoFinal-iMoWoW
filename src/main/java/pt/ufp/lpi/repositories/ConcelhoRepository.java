package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Concelho;

import java.util.Optional;

@Repository
public interface ConcelhoRepository extends CrudRepository<Concelho, Long>
{

}
