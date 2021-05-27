package pt.ufp.lpi.controller.dto.conversores;

public interface Conversor<O, I>
{
    O converter(I i);
}
