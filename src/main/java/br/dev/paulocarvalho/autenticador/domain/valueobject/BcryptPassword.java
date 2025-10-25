package br.dev.paulocarvalho.autenticador.domain.valueobject;

import br.dev.paulocarvalho.autenticador.domain.exception.InvalidPasswordException;
import br.dev.paulocarvalho.autenticador.domain.exception.PasswordMatchesException;
import io.quarkus.elytron.security.common.BcryptUtil;

public class BcryptPassword {

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
    private static final String BCRYPT_REGEX = "^\\$2[aby]?\\$\\d{2}\\$[./A-Za-z0-9]{53}$";

    public static boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    private final String hashPassword;

    public BcryptPassword(String password) throws InvalidPasswordException {
        if (password != null && password.matches(BCRYPT_REGEX)) {
            this.hashPassword = password;
        } else {
            if (!isValid(password)) {
                throw new InvalidPasswordException();
            }
            this.hashPassword = BcryptUtil.bcryptHash(password);
        }
    }

    public void checkPassword(String plainPassword) throws PasswordMatchesException {
        if (!BcryptUtil.matches(plainPassword, hashPassword)) {
            throw new PasswordMatchesException();
        }
    }

    @Override
    public String toString() {
        return hashPassword;
    }

    public static void main(String[] args) {
        System.out.println(BcryptUtil.bcryptHash("Solteiro@2021"));
    }
}
