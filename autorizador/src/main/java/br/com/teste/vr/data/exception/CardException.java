package br.com.teste.vr.data.exception;

import br.com.teste.vr.data.request.CardRequestDto;
import lombok.Getter;

public class CardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private CardRequestDto requestDto;

    @Getter
    private Integer errorCode;

    public CardException(final String message, CardRequestDto requestDto, Integer errorCode) {
        super(message);
        this.requestDto = requestDto;
        this.errorCode = errorCode;
    }

    public CardException(final Throwable cause) {
        super(cause);
    }
}
