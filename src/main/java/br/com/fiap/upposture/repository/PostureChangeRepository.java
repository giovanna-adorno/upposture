package br.com.fiap.upposture.repository;

import br.com.fiap.upposture.model.PositionEnum;
import br.com.fiap.upposture.model.PostureChange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostureChangeRepository extends JpaRepository<PostureChange, Long> {

    List<PostureChange> findAllByUser_IdOrderByDateDesc(Long userId);

    int countByPositionAndDateAfterAndUser_Id(PositionEnum position, LocalDateTime date, Long userId);

    int countByDateAfterAndUser_Id(LocalDateTime date, Long userId);
}
