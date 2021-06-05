package pt.ufp.lpi;

import lombok.SneakyThrows;
import org.hibernate.type.LocalDateTimeType;
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
                .precoMedioArrendamento(8)
                .precoMedioVenda(1676)
                .nome("Vila Nova de Gaia")
                .distrito(porto)
                .build();
        HistoricoArrendamento gaiaha3 = HistoricoArrendamento.builder()
                .concelho(gaia)
                .data(LocalDateTime.of(2021, 3, 1, 0,0))
                .precoAntigo(8.2f)
                .build();
        HistoricoVenda gaiahv3 = HistoricoVenda.builder()
                .concelho(gaia)
                .precoAntigo(1690)
                .data(LocalDateTime.of(2021, 3, 1, 0,0))
                .build();
        HistoricoArrendamento gaiaha4 = HistoricoArrendamento.builder()
                .concelho(gaia)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .precoAntigo(7.8f)
                .build();
        HistoricoVenda gaiahv4 = HistoricoVenda.builder()
                .concelho(gaia)
                .precoAntigo(1700)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .build();
        HistoricoArrendamento gaiaha5 = HistoricoArrendamento.builder()
                .concelho(gaia)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .precoAntigo(8f)
                .build();
        HistoricoVenda gaiahv5 = HistoricoVenda.builder()
                .concelho(gaia)
                .precoAntigo(1686)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .build();
        gaia.adicionaHistoricoArrendamento(gaiaha3);
        gaia.adicionaHistoricoVenda(gaiahv3);
        gaia.adicionaHistoricoArrendamento(gaiaha4);
        gaia.adicionaHistoricoVenda(gaiahv4);
        gaia.adicionaHistoricoArrendamento(gaiaha5);
        gaia.adicionaHistoricoVenda(gaiahv5);

        Concelho maia = Concelho.builder()
                .precoMedioArrendamento(7)
                .precoMedioVenda(1398)
                .nome("Maia")
                .distrito(porto)
                .build();
        HistoricoArrendamento maiaha5 = HistoricoArrendamento.builder()
                .concelho(maia)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .precoAntigo(7.3f)
                .build();
        HistoricoVenda maiahv5 = HistoricoVenda.builder()
                .concelho(maia)
                .precoAntigo(1403)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .build();
        HistoricoArrendamento maiaha4 = HistoricoArrendamento.builder()
                .concelho(maia)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .precoAntigo(7.4f)
                .build();
        HistoricoVenda maiahv4 = HistoricoVenda.builder()
                .concelho(maia)
                .precoAntigo(1410)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .build();
        HistoricoArrendamento maiaha3 = HistoricoArrendamento.builder()
                .concelho(maia)
                .data(LocalDateTime.of(2021, 3, 1, 0,0))
                .precoAntigo(7.6f)
                .build();
        HistoricoVenda maiahv3 = HistoricoVenda.builder()
                .concelho(maia)
                .precoAntigo(1407)
                .data(LocalDateTime.of(2021, 3, 1, 0,0))
                .build();
        maia.adicionaHistoricoVenda(maiahv3);
        maia.adicionaHistoricoArrendamento(maiaha3);
        maia.adicionaHistoricoVenda(maiahv4);
        maia.adicionaHistoricoArrendamento(maiaha4);
        maia.adicionaHistoricoVenda(maiahv5);
        maia.adicionaHistoricoArrendamento(maiaha5);

        Concelho matosinhos = Concelho.builder()
                .precoMedioArrendamento(9.5f)
                .precoMedioVenda(2400)
                .nome("Matosinhos")
                .distrito(porto)
                .build();
        HistoricoArrendamento matosinhosha3 = HistoricoArrendamento.builder()
                .concelho(matosinhos)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .precoAntigo(9.3f)
                .build();
        HistoricoVenda matosinhoshv3 = HistoricoVenda.builder()
                .concelho(matosinhos)
                .precoAntigo(2377)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .build();
        HistoricoArrendamento matosinhosha4 = HistoricoArrendamento.builder()
                .concelho(matosinhos)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .precoAntigo(9.2f)
                .build();
        HistoricoVenda matosinhoshv4 = HistoricoVenda.builder()
                .concelho(matosinhos)
                .precoAntigo(2356)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .build();
        HistoricoArrendamento matosinhosha5 = HistoricoArrendamento.builder()
                .concelho(matosinhos)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .precoAntigo(9.4f)
                .build();
        HistoricoVenda matosinhoshv5 = HistoricoVenda.builder()
                .concelho(matosinhos)
                .precoAntigo(2367)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .build();
        matosinhos.adicionaHistoricoVenda(matosinhoshv3);
        matosinhos.adicionaHistoricoArrendamento(matosinhosha3);
        matosinhos.adicionaHistoricoVenda(matosinhoshv4);
        matosinhos.adicionaHistoricoArrendamento(matosinhosha4);
        matosinhos.adicionaHistoricoVenda(matosinhoshv5);
        matosinhos.adicionaHistoricoArrendamento(matosinhosha5);

        Concelho portoC = Concelho.builder()
                .precoMedioArrendamento(10.7f)
                .precoMedioVenda(3023)
                .nome("Porto")
                .distrito(porto)
                .build();
        HistoricoArrendamento portoha3 = HistoricoArrendamento.builder()
                .concelho(matosinhos)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .precoAntigo(11)
                .build();
        HistoricoVenda portohv3 = HistoricoVenda.builder()
                .concelho(portoC)
                .precoAntigo(3035)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .build();
        HistoricoArrendamento portoha4 = HistoricoArrendamento.builder()
                .concelho(portoC)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .precoAntigo(10.7f)
                .build();
        HistoricoVenda portohv4 = HistoricoVenda.builder()
                .concelho(portoC)
                .precoAntigo(3028)
                .data(LocalDateTime.of(2021, 4, 1, 0,0))
                .build();
        HistoricoArrendamento portoha5 = HistoricoArrendamento.builder()
                .concelho(portoC)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .precoAntigo(10.4f)
                .build();
        HistoricoVenda portohv5 = HistoricoVenda.builder()
                .concelho(matosinhos)
                .precoAntigo(3020)
                .data(LocalDateTime.of(2021, 5, 1, 0,0))
                .build();
        portoC.adicionaHistoricoArrendamento(portoha3);
        portoC.adicionaHistoricoVenda(portohv3);
        portoC.adicionaHistoricoArrendamento(portoha4);
        portoC.adicionaHistoricoVenda(portohv4);
        portoC.adicionaHistoricoArrendamento(portoha5);
        portoC.adicionaHistoricoVenda(portohv5);


        porto.adicionaConcelho(gaia);
        porto.adicionaConcelho(matosinhos);
        porto.adicionaConcelho(maia);
        porto.adicionaConcelho(portoC);

        this.distritoRepository.save(porto);

        Utilizador jorge = new Utilizador();
        jorge.setUserName("jfps97");

        this.utilizadorRepository.save(jorge);





        this.concelhoRepository.save(gaia);
        this.concelhoRepository.save(matosinhos);
        this.concelhoRepository.save(maia);






    }
}
