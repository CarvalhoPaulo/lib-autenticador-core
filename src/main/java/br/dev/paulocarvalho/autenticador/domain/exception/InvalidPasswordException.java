package br.dev.paulocarvalho.autenticador.domain.exception;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super(AutenticadorErrorCodeEnum.INVALID_PASSWORD);
    }
}
