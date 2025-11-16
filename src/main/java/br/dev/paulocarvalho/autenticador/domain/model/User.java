package br.dev.paulocarvalho.autenticador.domain.model;

import br.dev.paulocarvalho.arquitetura.domain.exception.InvalidEmailException;
import br.dev.paulocarvalho.arquitetura.domain.exception.InvalidFullNameException;
import br.dev.paulocarvalho.arquitetura.domain.entity.Entity;
import br.dev.paulocarvalho.arquitetura.domain.valueobject.Email;
import br.dev.paulocarvalho.arquitetura.domain.valueobject.FullName;
import br.dev.paulocarvalho.autenticador.domain.exception.InvalidPasswordException;
import br.dev.paulocarvalho.autenticador.domain.exception.InvalidUsernameException;
import br.dev.paulocarvalho.autenticador.domain.exception.PasswordMatchesException;
import br.dev.paulocarvalho.autenticador.domain.valueobject.BcryptPassword;
import br.dev.paulocarvalho.autenticador.domain.valueobject.Username;
import lombok.Builder;
import lombok.Getter;


public class User implements Entity<Long> {
    @Getter
    private final Long id;
    private final FullName name;
    private final Username username;
    private final Email email;
    private final BcryptPassword password;
    @Getter
    private final Profile profile;
    @Getter
    private Boolean active = true;

    @Builder
    public User(Long id,
                String name,
                String username,
                String email,
                String password,
                Profile profile,
                Boolean active) throws InvalidEmailException,
            InvalidPasswordException,
            InvalidUsernameException,
            InvalidFullNameException {
        this.id = id;
        this.name = new FullName(name);
        this.username = new Username(username);
        this.email = new Email(email);
        this.password = new BcryptPassword(password);
        this.profile = profile;
        if (this.active) {
            this.active = active;
        }
    }

    public User checkPassword(String plainPassword) throws PasswordMatchesException {
        this.password.checkPassword(plainPassword);
        return this;
    }

    public User delete() {
        this.active = false;
        return this;
    }

    public String getName() {
        return name.toString();
    }

    public String getUsername() {
        return username.toString();
    }

    public String getEmail() {
        return email.toString();
    }

    public String getPassword() {
        return password.toString();
    }

}
