package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Utilizador;

@Repository
public interface UtilizadorRepository extends CrudRepository<Utilizador, Long>
{
}
