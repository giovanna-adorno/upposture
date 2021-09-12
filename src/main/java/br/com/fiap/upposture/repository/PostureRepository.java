package br.com.fiap.upposture.repository;

import br.com.fiap.upposture.model.PositionEnum;
import br.com.fiap.upposture.model.Posture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostureRepository extends JpaRepository<Posture, Long> {

    Page<Posture> findByPosition(PositionEnum position, Pageable pageable);

}
