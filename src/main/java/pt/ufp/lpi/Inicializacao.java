package pt.ufp.lpi;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Distrito;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Utilizador;
import pt.ufp.lpi.repositories.ConcelhoRepository;
import pt.ufp.lpi.repositories.DistritoRepository;
import pt.ufp.lpi.repositories.ImovelRepository;
import pt.ufp.lpi.repositories.UtilizadorRepository;

import java.util.Date;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConcelhoRepository concelhoRepository;
    @Autowired
    private DistritoRepository distritoRepository;
    @Autowired
    private UtilizadorRepository utilizadorRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        System.out.println("\n\n\nInicializou\n\n\n");

        Distrito porto = new Distrito();
        porto.setNome("Porto");
        Concelho gaia = new Concelho();
        gaia.setNome("Gaia");
        Concelho matosinhos = new Concelho();
        matosinhos.setNome("Matosinhos");
        Concelho maia = new Concelho();
        maia.setNome("Maia");
        porto.adicionaConcelho(gaia);
        porto.adicionaConcelho(matosinhos);
        porto.adicionaConcelho(maia);

        this.distritoRepository.save(porto);

        Utilizador jorge = new Utilizador();
        jorge.setUserName("jfps97");

        this.utilizadorRepository.save(jorge);


        jorge.adicionaConcelho(gaia);
        Imovel casaDasAmoras = new Imovel();
        casaDasAmoras.setDataAnuncio(new Date());
        jorge.adicionaImovel(casaDasAmoras);


        this.concelhoRepository.save(gaia);
//        this.concelhoRepository.save(matosinhos);
//        this.concelhoRepository.save(maia);


        this.imovelRepository.save(casaDasAmoras);



    }
}
