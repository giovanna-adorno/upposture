package br.com.fiap.upposture.repository;

import br.com.fiap.upposture.model.StatusEnum;
import br.com.fiap.upposture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPasswordAndStatus(String email, String password, StatusEnum status);
    boolean existsByEmail(String email);
}