package br.com.teste.vr.service;

import br.com.teste.vr.data.exception.CardException;
import br.com.teste.vr.data.model.Card;
import br.com.teste.vr.data.request.CardRequestDto;
import br.com.teste.vr.data.response.CardResponseDto;
import br.com.teste.vr.repository.CardRepository;
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
        if (getCard(cardEntity) != null) {
            throw new CardException("CARD_DUPLICATED", dto, 422);
        }

        return mapper.map(repository.save(cardEntity), CardResponseDto.class);
    }

    private Card getCard(Card card) {
        return repository.findByNumeroCartao(card.getNumeroCartao().trim()).orElse(null);
    }

    public CardResponseDto findByCardNumber(String cardNumber) {
        Card card = getCard(Card.builder().numeroCartao(cardNumber).build());
        if(ObjectUtils.isEmpty(card)) {
            throw new CardException("CARD_NOT_FOUND", null, 404);
        }
        return mapper.map(card, CardResponseDto.class);
    }
}
