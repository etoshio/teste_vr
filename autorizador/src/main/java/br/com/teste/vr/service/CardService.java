package br.com.teste.vr.service;

import br.com.teste.vr.data.exception.ErrorException;
import br.com.teste.vr.data.exception.ErrorType;
import br.com.teste.vr.data.request.CardRequestDto;
import br.com.teste.vr.data.response.CardResponseDto;
import br.com.teste.vr.repository.CardRepository;
import br.com.teste.vr.repository.model.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    private ModelMapper mapper = new ModelMapper();
    public CardResponseDto create(final CardRequestDto dto) {
        Card cardEntity = mapper.map(dto, Card.class);
        if (checkCardExist(cardEntity) != null) {
            throw new ErrorException(ErrorType.DUPLICATED_CARD);
        }

        return mapper.map(repository.save(cardEntity), CardResponseDto.class);
    }

    private Card checkCardExist(Card cardEntity) {
        return repository.findByCardNumber(cardEntity.getCardNumber().trim()).orElse(null);
    }

    public CardResponseDto findByCardNumber(String cardNumber) {
        Card card = checkCardExist(Card.builder().cardNumber(cardNumber).build());
        if(ObjectUtils.isEmpty(card)) {
            throw new ErrorException(ErrorType.NOT_FOUND);
        }
        return mapper.map(card, CardResponseDto.class);
    }
}
