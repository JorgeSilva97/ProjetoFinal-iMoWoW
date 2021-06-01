package pt.ufp.lpi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.controller.dtos.ArrendamentoDTO;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.enumerado.Avaliacao;
import pt.ufp.lpi.services.AplicacaoService;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArrendamentoController.class)
class ArrendamentoControllerTest
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
    void createArrendamento() throws Exception
    {
        ArrendamentoDTO arrendamento = ArrendamentoDTO.builder()
                .imovelId(1L)
                .id(1L)
                .precoArrendamento(750)
                .build();

        when(utilizadorService.criaArrendamento(conversor.converterDTOParaArrendamento(arrendamento)))
                .thenReturn(Optional.of(conversor.converterDTOParaArrendamento(arrendamento)));
        String arrendamentoAsJsonString = new ObjectMapper().writeValueAsString(arrendamento);
        mockMvc.perform(post("/arrendamento").content(arrendamentoAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        ArrendamentoDTO arrendamentoExistente = ArrendamentoDTO.builder()
                .imovelId(1L)
                .id(1L)
                .precoArrendamento(550)
                .build();

        String arrendamentoExixtenteAsJsonString = new ObjectMapper().writeValueAsString(arrendamentoExistente);
        mockMvc.perform(post("/arrendamento").content(arrendamentoExixtenteAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void getValorArrendamento() throws Exception
    {
        when(aplicacaoService.getValorFuturoDeArrendamento(1L)).thenReturn(600f);

        String response = mockMvc.perform(get("/arrendamento/valor/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(600f, Float.parseFloat(response));
        assertNotEquals(1000f, Float.parseFloat(response));
    }

    @Test
    void getAvaliacaoArrendamento() throws Exception
    {
        when(aplicacaoService.getAvalicaoNegocioArrendamento(1L)).thenReturn(Optional.of(Avaliacao.Mau));


        String response = mockMvc.perform(get("/arrendamento/avaliacao/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(Avaliacao.Mau.name(), response);
        assertNotEquals(Avaliacao.Bom.name(), response);
    }
}