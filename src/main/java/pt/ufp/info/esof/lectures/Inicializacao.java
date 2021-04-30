package pt.ufp.info.esof.lectures;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.lectures.models.Concelho;
import pt.ufp.info.esof.lectures.models.Distrito;
import pt.ufp.info.esof.lectures.models.Imovel;
import pt.ufp.info.esof.lectures.models.Utilizador;
import pt.ufp.info.esof.lectures.repositories.ConcelhoRepository;
import pt.ufp.info.esof.lectures.repositories.DistritoRepository;
import pt.ufp.info.esof.lectures.repositories.ImovelRepository;
import pt.ufp.info.esof.lectures.repositories.UtilizadorRepository;

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

        Utilizador jorge = new Utilizador();
        jorge.setUserName("jfps97");
        jorge.adicionaConcelho(gaia);
        Imovel casaDasAmoras = new Imovel();
        jorge.adicionaImovel(casaDasAmoras);


        this.concelhoRepository.save(gaia);
        this.concelhoRepository.save(matosinhos);
        this.concelhoRepository.save(maia);
        this.distritoRepository.save(porto);
        //this.imovelRepository.save(casaDasAmoras);
        this.utilizadorRepository.save(jorge);


    }
}
