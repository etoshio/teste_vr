package br.com.teste.vr.controller;

import br.com.teste.vr.data.request.TransactionRequestDto;
import br.com.teste.vr.data.response.TransactionResponseDto;
import br.com.teste.vr.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping( "/transacoes" )
@Validated
@Tag( name = "Transações", description = "Transações" )
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity createTransaction(@Valid @RequestBody TransactionRequestDto dto) {
        return new ResponseEntity(service.createTransaction(dto), HttpStatus.CREATED );
    }
}
