package br.com.teste.vr.service;

import br.com.teste.vr.data.exception.TransactionException;
import br.com.teste.vr.data.model.Card;
import br.com.teste.vr.data.model.Transaction;
import br.com.teste.vr.data.request.TransactionRequestDto;
import br.com.teste.vr.data.response.TransactionResponseDto;
import br.com.teste.vr.repository.CardRepository;
import br.com.teste.vr.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private CardRepository cardRepository;
    private ModelMapper mapper = new ModelMapper();
    public TransactionResponseDto createTransaction(TransactionRequestDto dto) {
        Card card = cardRepository.findByNumeroCartao(dto.getNumeroCartao())
                .orElseThrow(() -> new TransactionException("CARTAO_INEXISTENTE", 404));
        if (!checkPassword(card, dto)) {
            throw new TransactionException("SENHA_INVALIDA", 422);
        }
        if (!checkValue(card, dto)) {
            throw new TransactionException("SALDO_INSUFICIENTE", 422);
        }

        card.setSaldo(card.getSaldo().subtract(dto.getValor()));
        TransactionResponseDto transactionResponseDto = mapper.map(cardRepository.save(card),
                TransactionResponseDto.class);

        repository.save(mapper.map(dto, Transaction.class));
        return transactionResponseDto;
    }

    private Boolean checkValue(Card card, TransactionRequestDto dto) {
        return card.getSaldo().compareTo(dto.getValor()) >= 0;
    }

    private Boolean checkPassword(Card card, TransactionRequestDto dto) {
        return card.getSenha().equals(dto.getSenhaCartao());
    }
}
