package br.com.fiap.upposture.repository;

import br.com.fiap.upposture.model.Posture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostureRepository extends JpaRepository<Posture, Long> {
}
