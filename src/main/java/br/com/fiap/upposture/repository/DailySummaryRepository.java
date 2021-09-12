package br.com.fiap.upposture.repository;

import br.com.fiap.upposture.model.DailySummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
    Page<DailySummary> findAllByUser_Id(Long user_id, Pageable pageable);

    Optional<DailySummary> findByUser_IdAndId(Long id, Long user_id);
}
