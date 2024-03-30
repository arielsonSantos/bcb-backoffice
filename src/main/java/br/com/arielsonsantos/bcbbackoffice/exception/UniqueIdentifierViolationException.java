package br.com.arielsonsantos.bcbbackoffice.exception;

public class UniqueIdentifierViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UniqueIdentifierViolationException(final String message) {
        super(message);
    }
}
