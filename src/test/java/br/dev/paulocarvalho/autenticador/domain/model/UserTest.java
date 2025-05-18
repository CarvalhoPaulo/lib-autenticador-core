package br.dev.paulocarvalho.autenticador.domain.model;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void deveCriarUserComSucesso() throws BusinessException {
        // Arrange
        Long id = 1L;
        String name = "João Silva";
        String username = "joao.silva";
        String email = "joao@email.com";
        String password = "Senha@123";

        // Act
        User user = User.builder()
                .id(id)
                .username(username)
                .name(name)
                .email(email)
                .password(password)
                .build();

        // Assert
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertDoesNotThrow(() -> user.checkPassword(password));
    }

}
