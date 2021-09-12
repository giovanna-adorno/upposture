package br.com.fiap.upposture.repository;

import br.com.fiap.upposture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}