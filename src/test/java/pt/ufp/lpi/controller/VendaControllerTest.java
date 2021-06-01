package pt.ufp.lpi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.controller.dtos.VendaDTO;
import pt.ufp.lpi.controller.dtos.converter.DTOToModelConversor;
import pt.ufp.lpi.models.enumerado.Avaliacao;
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
    void createVenda() throws Exception
    {
        VendaDTO venda = VendaDTO.builder()
                .id(1L)
                .imovelId(1L)
                .precoTotal(100000)
                .build();

        when(utilizadorService.criaVenda(conversor.converterDTOParaVenda(venda))).
                thenReturn(Optional.of(conversor.converterDTOParaVenda(venda)));
        String vendaAsJsonString = new ObjectMapper().writeValueAsString(venda);
        mockMvc.perform(post("/venda").content(vendaAsJsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        VendaDTO vendaExistente = VendaDTO.builder()
                .id(1L)
                .imovelId(1L)
                .precoTotal(200000)
                .build();

        String vendaExistenteAsJsonString = new ObjectMapper().writeValueAsString(vendaExistente);
        mockMvc.perform(post("/venda").content(vendaExistenteAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }

    @Test
    void getValorVenda() throws Exception
    {

        when(aplicacaoService.getValorFuturoDaVenda(1L)).thenReturn(100000f);

        String response = mockMvc.perform(get("/venda/valor/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(100000f, Float.parseFloat(response));
        assertNotEquals(600f, Float.parseFloat(response));
    }

    @Test
    void getAvalicaoVenda() throws Exception
    {
        when(aplicacaoService.getAvalicaoNegocioVenda(1L)).thenReturn(Optional.of(Avaliacao.Bom));

        String response = mockMvc.perform(get("/venda/avaliacao/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(Avaliacao.Bom.name(), response);
        assertNotEquals(Avaliacao.Mau.name(), response);
    }

}