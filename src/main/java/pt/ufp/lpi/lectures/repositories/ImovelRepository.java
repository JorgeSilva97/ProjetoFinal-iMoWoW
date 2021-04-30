package pt.ufp.lpi.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.lpi.lectures.models.Imovel;

import java.util.Optional;

@Repository
public interface ImovelRepository extends CrudRepository<Imovel,Long> {
}
