package pt.ufp.lpi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConcelhoController.class)
class ConcelhoControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UtilizadorService utilizadorService;
    private ObjectMapper objectMapper;

    @Test
    void getAllConcelhos() throws Exception
    {
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .precoMedioVenda(400)
                .precoMedioArrendamento(15)
                .build();
        Concelho maia = Concelho.builder()
                .nome("maia")
                .precoMedioVenda(350)
                .precoMedioArrendamento(16)
                .build();
        List<Concelho> concelhos = Arrays.asList(gaia, maia);

        when(utilizadorService.findAllConcelhos()).thenReturn(concelhos);

        String httpResponseAsString = mockMvc.perform(get("/concelho")).andDo(print()).andExpect(
                status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getConcelhoById() throws Exception
    {
        Concelho gaia = Concelho.builder()
                .nome("gaia")
                .precoMedioVenda(400)
                .precoMedioArrendamento(15)
                .build();

        when(utilizadorService.findConcelhoById(1L)).thenReturn(Optional.of(gaia));

        String httpResponseAsString = mockMvc.perform(get("/concelho/1")).andExpect(
                status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/utilizador/2")).andExpect(status().isNotFound());
    }
}