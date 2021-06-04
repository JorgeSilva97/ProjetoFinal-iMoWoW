package pt.ufp.lpi;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.lpi.models.*;
import pt.ufp.lpi.repositories.ConcelhoRepository;
import pt.ufp.lpi.repositories.DistritoRepository;
import pt.ufp.lpi.repositories.ImovelRepository;
import pt.ufp.lpi.repositories.UtilizadorRepository;

import java.time.LocalDateTime;
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
        Concelho gaia = Concelho.builder()
                .precoMedioArrendamento(60)
                .precoMedioVenda(200)
                .nome("gaia")
                .distrito(porto)
                .build();
        HistoricoArrendamento ha1 = HistoricoArrendamento.builder()
                .concelho(gaia)
                .data(LocalDateTime.now())
                .precoAntigo(50)
                .build();
        HistoricoVenda hv1 = HistoricoVenda.builder()
                .concelho(gaia)
                .precoAntigo(190)
                .data(LocalDateTime.now())
                .build();
        gaia.adicionaHistoricoArrendamento(ha1);
        gaia.adicionaHistoricoVenda(hv1);

        Concelho maia = Concelho.builder()
                .precoMedioArrendamento(70)
                .precoMedioVenda(170)
                .nome("maia")
                .distrito(porto)
                .build();
        HistoricoArrendamento ha2 = HistoricoArrendamento.builder()
                .concelho(maia)
                .data(LocalDateTime.now())
                .precoAntigo(68)
                .build();
        HistoricoVenda hv2 = HistoricoVenda.builder()
                .concelho(gaia)
                .precoAntigo(190)
                .data(LocalDateTime.now())
                .build();
        maia.adicionaHistoricoVenda(hv2);
        maia.adicionaHistoricoArrendamento(ha2);

        Concelho matosinhos = Concelho.builder()
                .precoMedioArrendamento(90)
                .precoMedioVenda(280)
                .nome("matosinhos")
                .distrito(porto)
                .build();
        HistoricoArrendamento ha3 = HistoricoArrendamento.builder()
                .concelho(matosinhos)
                .data(LocalDateTime.now())
                .precoAntigo(84)
                .build();
        HistoricoVenda hv3 = HistoricoVenda.builder()
                .concelho(matosinhos)
                .precoAntigo(268)
                .data(LocalDateTime.now())
                .build();
        matosinhos.adicionaHistoricoVenda(hv3);
        matosinhos.adicionaHistoricoArrendamento(ha3);

        porto.adicionaConcelho(gaia);
        porto.adicionaConcelho(matosinhos);
        porto.adicionaConcelho(maia);

        this.distritoRepository.save(porto);

        Utilizador jorge = new Utilizador();
        jorge.setUserName("jfps97");

        this.utilizadorRepository.save(jorge);


        jorge.adicionaConcelho(gaia);
        Imovel casaDasAmoras = new Imovel();
        casaDasAmoras.setDataAnuncio(LocalDateTime.now());
        jorge.adicionaImovel(casaDasAmoras);


        this.concelhoRepository.save(gaia);
//        this.concelhoRepository.save(matosinhos);
//        this.concelhoRepository.save(maia);


        this.imovelRepository.save(casaDasAmoras);



    }
}
