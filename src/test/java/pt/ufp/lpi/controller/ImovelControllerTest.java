package pt.ufp.lpi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.enumerado.Topologia;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImovelController.class)
class ImovelControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UtilizadorService utilizadorService;
    private ObjectMapper objectMapper;

    @Test
    void getAllImoveis() throws Exception
    {
        Imovel imovel1 = Imovel.builder()
                .elevador(true)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T1_1)
                .build();
        Imovel imovel2 = Imovel.builder()
                .elevador(false)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T2_1)
                .build();
        //List<Imovel> imoveis = Arrays.asList(imovel1, imovel2);

        //when(utilizadorService.findAllImoveis()).thenReturn(imoveis);

        String httpResponseAsString = mockMvc.perform(get("/imovel")).andDo(print()).andExpect(
                status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getImovelById()
    {

    }

    @Test
    void createImovel()
    {

    }
}