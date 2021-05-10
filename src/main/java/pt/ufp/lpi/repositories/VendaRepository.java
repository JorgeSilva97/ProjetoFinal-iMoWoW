package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>
{
}
