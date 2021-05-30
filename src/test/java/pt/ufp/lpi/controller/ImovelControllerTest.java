package pt.ufp.lpi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.lpi.controller.dtos.ImovelDTO;
import pt.ufp.lpi.models.Concelho;
import pt.ufp.lpi.models.Imovel;
import pt.ufp.lpi.models.Utilizador;
import pt.ufp.lpi.models.enumerado.Topologia;
import pt.ufp.lpi.services.UtilizadorService;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Utilizador jorge = Utilizador.builder()
                .userName("jorge")
                .build();
        Imovel imovel1 = Imovel.builder()
                .utilizador(jorge)
                .concelho(gaia)
                .elevador(true)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T1_1)
                .build();
        Imovel imovel2 = Imovel.builder()
                .utilizador(jorge)
                .concelho(maia)
                .elevador(false)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T2_1)
                .build();
        List<Imovel> imoveis = Arrays.asList(imovel1, imovel2);
        when(utilizadorService.findAllImoveis()).thenReturn(imoveis);
        String httpResponseAsString = mockMvc.perform(get("/imovel")).andDo(print()).andExpect(
                status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
    }

    @Test
    void getImovelById() throws Exception
    {
        Concelho maia = Concelho.builder()
                .nome("maia")
                .precoMedioVenda(350)
                .precoMedioArrendamento(16)
                .build();
        Utilizador jorge = Utilizador.builder()
                .userName("jorge")
                .build();
        Imovel imovel = Imovel.builder()
                .utilizador(jorge)
                .concelho(maia)
                .elevador(true)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T1_1)
                .build();
        when(utilizadorService.findImoveloById(1L)).thenReturn(Optional.of(imovel));
        String httpResponseAsString = mockMvc.perform(get("/imovel/1")).andExpect(
                status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/utilizador/2")).andExpect(status().isNotFound());

    }

   /* @Test
    void getImoveisByUser() throws Exception
    {

    }*/

    @Test
    void createImovel() throws Exception
    {
        Concelho maia = Concelho.builder()
                .nome("maia")
                .precoMedioVenda(350)
                .precoMedioArrendamento(16)
                .build();
        Utilizador jorge = Utilizador.builder()
                .userName("jorge")
                .build();
        Imovel novoImovel = Imovel.builder()
                .utilizador(jorge)
                .concelho(maia)
                .elevador(true)
                .piscina(true)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T1_1)
                .build();
        when(utilizadorService.criaImovel(novoImovel)).thenReturn(Optional.of(novoImovel));
        String imovelAsJsonString = new ObjectMapper().writeValueAsString(novoImovel);
        mockMvc.perform(post("/imovel").content(imovelAsJsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Imovel imovelExistente = Imovel.builder()
                .utilizador(jorge)
                .concelho(maia)
                .elevador(true)
                .piscina(false)
                .jardim(true)
                .garagem(false)
                .topologia(Topologia.T1)
                .build();
        String imovelExistenteAsJsonString = new ObjectMapper().writeValueAsString(imovelExistente);
        mockMvc.perform(post("/imovel").content(imovelExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());



    }
}