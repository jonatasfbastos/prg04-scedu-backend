package br.com.ifba.scedu.domain.entities.evaluation.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
