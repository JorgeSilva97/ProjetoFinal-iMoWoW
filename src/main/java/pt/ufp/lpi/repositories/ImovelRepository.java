package pt.ufp.lpi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.models.Imovel;

@Repository
public interface ImovelRepository extends CrudRepository<Imovel,Long> {
}
