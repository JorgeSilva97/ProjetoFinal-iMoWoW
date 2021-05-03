package pt.ufp.lpi.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.lectures.models.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>
{
}
