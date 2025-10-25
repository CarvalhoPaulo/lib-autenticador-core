package br.dev.paulocarvalho.autenticador.domain.exception;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;

public class CredentialsNotFoundException extends BusinessException {
    public CredentialsNotFoundException() {
        super(AutenticadorErrorCodeEnum.INVALID_CREDENTIALS, "Credenciais não informadas");
    }
}
