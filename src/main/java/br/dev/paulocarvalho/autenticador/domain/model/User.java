package br.dev.paulocarvalho.autenticador.domain.model;

import br.dev.paulocarvalho.arquitetura.domain.model.Model;
import br.dev.paulocarvalho.autenticador.domain.exception.*;
import lombok.Builder;
import lombok.Getter;


public class User implements Model<Long> {
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
            InvalidPasswordlException,
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

    public User checkPassword(String plainPassword) throws SenhaInvalidaException {
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

    public String getPassword() { return password.toString(); }

}
