package br.com.teste.vr.controller;

import br.com.teste.vr.data.request.CardRequestDto;
import br.com.teste.vr.data.response.CardResponseDto;
import br.com.teste.vr.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping( "/cartoes" )
@Tag( name = "Cartões", description = "Cartões" )
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(@Valid @RequestBody CardRequestDto dto) {
        return new ResponseEntity<CardResponseDto>(service.create(dto), HttpStatus.CREATED );
    }

    @GetMapping( value = "/{cardNumber}" )
    public ResponseEntity<CardResponseDto> getCardByNumber(@PathVariable String cardNumber) {
        return new ResponseEntity< >(service.findByCardNumber(cardNumber), HttpStatus.OK);
    }
}
