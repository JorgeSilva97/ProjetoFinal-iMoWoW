package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.enumerado.EstadoImovel;

@Repository
public interface EstadoRepository extends CrudRepository<EstadoImovel, Long>
{
}

