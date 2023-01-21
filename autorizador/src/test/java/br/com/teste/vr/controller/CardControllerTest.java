package br.com.teste.vr.controller;

import br.com.teste.vr.data.request.CardRequestDto;
import br.com.teste.vr.data.response.CardResponseDto;
import br.com.teste.vr.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CardControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateCard() throws Exception {
        CardRequestDto cartao = CardRequestDto.builder().numeroCartao("6549873025634501").senha("1234").build();
        mockMvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDuplicateCard() throws Exception {
        CardRequestDto cartao = CardRequestDto.builder().numeroCartao("6549873025634501").senha("1234").build();

        mockMvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartao)));

        mockMvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartao)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.error.numeroCartao", is("6549873025634501")))
                .andExpect(jsonPath("$.error.senha", is("1234")));
    }

    @Test
    void testGetBalance() throws Exception {
        CardRequestDto cartao = CardRequestDto.builder().numeroCartao("6549873025634501").senha("1234").build();

        mockMvc.perform(post("/cartoes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartao)));

        mockMvc.perform(get("/cartoes/6549873025634501")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testeGetBalanceNotFound() throws Exception {
        CardRequestDto cartao = CardRequestDto.builder().numeroCartao("6549873025634501").senha("1234").build();

        mockMvc.perform(post("/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartao)));

        mockMvc.perform(get("/cartoes/6549873025634502")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
