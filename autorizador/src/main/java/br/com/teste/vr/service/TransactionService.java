package br.com.teste.vr.service;

import br.com.teste.vr.data.exception.ErrorException;
import br.com.teste.vr.data.exception.ErrorType;
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
        Card card = cardRepository.findByCardNumber(dto.getCardNumber())
                .orElseThrow(() -> new ErrorException(ErrorType.NOT_FOUND));
        if (!checkPassword(card, dto)) {
            throw new ErrorException(ErrorType.PASSWORD_INVALID);
        }
        if (!checkValue(card, dto)) {
            throw new ErrorException(ErrorType.INSUFFICIENT_BALANCE);
        }

        card.setBalance(card.getBalance().subtract(dto.getValue()));
        TransactionResponseDto transactionResponseDto = mapper.map(cardRepository.save(card),
                TransactionResponseDto.class);

        repository.save(mapper.map(dto, Transaction.class));
        return transactionResponseDto;
    }

    private Boolean checkValue(Card card, TransactionRequestDto dto) {
        return card.getBalance().compareTo(dto.getValue()) >= 0;
    }

    private Boolean checkPassword(Card card, TransactionRequestDto dto) {
        return card.getPassword().equals(dto.getPassword());
    }
}
