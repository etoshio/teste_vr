package br.com.teste.vr.controller;

import br.com.teste.vr.data.model.Card;
import br.com.teste.vr.data.request.TransactionRequestDto;
import br.com.teste.vr.data.response.TransactionResponseDto;
import br.com.teste.vr.repository.CardRepository;
import br.com.teste.vr.service.TransactionService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardRepository cardRepository;

    @Test
    void testCreateCardAndCreateTransation() throws Exception {
        Card cartao = Card.builder()
                .numeroCartao("6549873025634501")
                .saldo(BigDecimal.valueOf(500))
                .senha("1234")
                .build();
        cardRepository.save(cartao);

        TransactionRequestDto transacaoDTO = TransactionRequestDto.builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1234")
                .valor(BigDecimal.TEN)
                .build();

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateTransactionWithCardNotFound() throws Exception {
        TransactionRequestDto transacaoDTO = TransactionRequestDto.builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1234")
                .valor(BigDecimal.TEN)
                .build();

        mockMvc.perform(post("/transacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCardAndCreateTransationWithInsufficientBalance() throws Exception {
        Card cartao = Card.builder()
                .numeroCartao("6549873025634501")
                .saldo(BigDecimal.valueOf(5))
                .senha("1234")
                .build();
        cardRepository.save(cartao);

        TransactionRequestDto transacaoDTO = TransactionRequestDto.builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1234")
                .valor(BigDecimal.TEN)
                .build();

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testCreateCardAndCreateTransationWithPasswordInvalid() throws Exception {
        Card cartao = Card.builder()
                .numeroCartao("6549873025634501")
                .saldo(BigDecimal.valueOf(5))
                .senha("1234")
                .build();
        cardRepository.save(cartao);

        TransactionRequestDto transacaoDTO = TransactionRequestDto.builder()
                .numeroCartao("6549873025634501")
                .senhaCartao("1111")
                .valor(BigDecimal.TEN)
                .build();

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoDTO)))
                .andExpect(status().isUnprocessableEntity());
    }
}
