package br.dev.paulocarvalho.autenticador.domain.model;

import br.dev.paulocarvalho.arquitetura.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenTest {

    @Test
    void deveCriarRefreshTokenComSucesso() throws BusinessException {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("joao.silva")
                .email("joao.silva@email.com")
                .password("Senha@123")
                .name("João Silva")
                .build();

        // Act
        RefreshToken refreshToken = RefreshToken.create(user);

        // Assert
        assertNotNull(refreshToken);
        assertNotNull(refreshToken.getToken());
        assertEquals(user, refreshToken.getUser());
        assertNotNull(refreshToken.getCreationDateTime());
        assertNotNull(refreshToken.getExpirationDateTime());
        assertTrue(refreshToken.getExpirationDateTime().isAfter(refreshToken.getCreationDateTime()));
    }

    @Test
    void deveRenovarTokenComSucesso() throws BusinessException {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("joao.silva")
                .email("joao.silva@email.com")
                .password("Senha@123")
                .name("João Silva")
                .build();

        RefreshToken refreshToken = RefreshToken.create(user);
        String tokenAntigo = refreshToken.getToken();
        LocalDateTime dataExpiracaoAntiga = refreshToken.getExpirationDateTime();

        // Act
        refreshToken.renovarToken();

        // Assert
        assertNotEquals(tokenAntigo, refreshToken.getToken());
        assertTrue(refreshToken.getExpirationDateTime().isAfter(dataExpiracaoAntiga));
    }

    @Test
    void deveRetornarTempoMaximoDuracaoCorretamente() throws BusinessException {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("joao.silva")
                .email("joao.silva@email.com")
                .password("Senha@123")
                .name("João Silva")
                .build();

        RefreshToken refreshToken = RefreshToken.create(user);

        // Act
        long tempoMaximo = refreshToken.getTempoMaximoDuracaoSegundos();

        // Assert
        assertEquals(1800, tempoMaximo); // 30 minutos * 60 segundos
    }

    @Test
    void deveCriarRefreshTokenComDataHoraExpiracaoCorreta() throws BusinessException {
        // Arrange
        User user = User.builder()
                .id(1L)
                .username("joao.silva")
                .email("joao.silva@email.com")
                .password("Senha@123")
                .name("João Silva")
                .build();

        // Act
        RefreshToken refreshToken = RefreshToken.create(user);
        LocalDateTime dataHoraExpiracao = refreshToken.getExpirationDateTime();
        LocalDateTime dataHoraCriacao = refreshToken.getCreationDateTime();

        // Assert
        assertEquals(30, Math.abs(dataHoraExpiracao.getMinute() - dataHoraCriacao.getMinute()));
    }
}
