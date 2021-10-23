package br.com.fiap.upposture.controller.api;

import br.com.fiap.upposture.model.DailySummary;
import br.com.fiap.upposture.model.PostureChange;
import br.com.fiap.upposture.repository.PostureChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static br.com.fiap.upposture.model.PositionEnum.IDEAL;

@RestController
@RequestMapping("/posture-changes")
public class PostureChangeController {

    @Autowired
    private PostureChangeRepository postureChangeRepository;

    @GetMapping("/daily-summaries/{userId}")
    public ResponseEntity<DailySummary> dailySummary(@PathVariable Long userId) {
        BigDecimal totalPosture = new BigDecimal(postureChangeRepository.countByDateAfterAndUser_Id
                (LocalDate.now().atStartOfDay(), userId));

        if (totalPosture.equals(BigDecimal.ZERO)) {
            return ResponseEntity.notFound().build();

        }
        BigDecimal correctPosture = new BigDecimal(postureChangeRepository.countByPositionAndDateAfterAndUser_Id(IDEAL,
                LocalDate.now().atStartOfDay(), userId)).multiply(new BigDecimal(100))
                .divide(totalPosture, 0, RoundingMode.DOWN);

        BigDecimal wrongPosture = new BigDecimal(100).subtract(correctPosture);

        DailySummary dailySummary = DailySummary.builder().correctPosture(correctPosture).wrongPosture(wrongPosture)
                .date(LocalDate.now()).build();
        return ResponseEntity.ok(dailySummary);
    }

    @GetMapping("/{userId}/")
    public ResponseEntity<PostureChange> index(@PathVariable Long userId) {
        List<PostureChange> postureChanges = postureChangeRepository.findAllByUser_IdOrderByDateDesc(userId);

        if (postureChanges.isEmpty() || dateIsValid(postureChanges.get(0).getDate()) ||
                postureChanges.get(0).getPosition().equals(IDEAL)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(postureChanges.get(0));
    }

    private boolean dateIsValid(LocalDateTime date) {
        return !LocalDateTime.now().minus(2, ChronoUnit.MINUTES).isBefore(date);
    }
}
