package pt.ufp.lpi.lectures.services;

import pt.ufp.lpi.lectures.models.Imovel;

import java.util.Optional;

public interface ImovelService
{
    Optional<Imovel> findById(Long id);


}
