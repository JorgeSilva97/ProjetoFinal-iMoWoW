package pt.ufp.info.esof.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.models.Imovel;

@Repository
public interface ImovelRepository extends CrudRepository<Imovel,Long> {
}
