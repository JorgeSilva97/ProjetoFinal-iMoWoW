package pt.ufp.lpi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.enumerado.Avalicao;
import pt.ufp.lpi.services.AplicacaoService;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendaController.class)
class VendaControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UtilizadorService utilizadorService;
    @MockBean
    private AplicacaoService aplicacaoService;
    private final DTOToModelConversor conversor = DTOToModelConversor.getInstance();


    @Test
    void getValorVenda() throws Exception
    {

        when(aplicacaoService.getValorFuturoDaVenda(1L)).thenReturn(1000f);


        String response=mockMvc.perform(get("/venda/valor/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(1000f,Float.parseFloat(response));

    }

    @Test
    void getAvalicaoVenda() throws Exception
    {
        when(aplicacaoService.getAvalicaoNegocioVenda(1L)).thenReturn(Optional.of(Avalicao.Bom));

        String response=mockMvc.perform(get("/venda/avaliacao/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(Avalicao.Bom.name(), response);
    }

}