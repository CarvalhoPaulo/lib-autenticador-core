package br.dev.paulocarvalho.autenticador.domain.exception;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;

public class PasswordMatchesException extends BusinessException {
    public PasswordMatchesException() {
        super(AutenticadorErrorCodeEnum.INVALID_CREDENTIALS);
    }
}
