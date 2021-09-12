package br.com.fiap.upposture.controller.api;

import br.com.fiap.upposture.model.DailySummary;
import br.com.fiap.upposture.model.User;
import br.com.fiap.upposture.repository.DailySummaryRepository;
import br.com.fiap.upposture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/daily-summaries")
public class DailySummaryController {

    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<DailySummary> findAll(@PathVariable Long userId, @PageableDefault(size = 20) Pageable pageable) {
        return dailySummaryRepository.findAllByUser_Id(userId, pageable);
    }

    @PostMapping
    public ResponseEntity<DailySummary> create(@PathVariable Long userId, @RequestBody @Valid DailySummary dailySummary, UriComponentsBuilder uriBuilder) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
             return ResponseEntity.notFound().build();
        }

        dailySummary.setUser(user.get());

        dailySummaryRepository.save(dailySummary);

        URI uri = uriBuilder
                .path("/users/{userId}/daily-summaries/{id}")
                .buildAndExpand(userId, dailySummary.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dailySummary);
    }

    @GetMapping("{id}")
    public ResponseEntity<DailySummary> findOne(@PathVariable Long userId, @PathVariable Long id) {
        return ResponseEntity.of(dailySummaryRepository.findByUser_IdAndId(userId, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DailySummary> delete(@PathVariable Long userId, @PathVariable Long id){
        Optional<DailySummary> dailySummary = dailySummaryRepository.findByUser_IdAndId(userId, id);

        if(dailySummary.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dailySummaryRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<DailySummary> update(@PathVariable Long userId, @RequestBody @Valid DailySummary newDailySummary, @PathVariable Long id ) {
        Optional<DailySummary> optional = dailySummaryRepository.findByUser_IdAndId(userId, id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        DailySummary dailySummary = optional.get();

        dailySummary.setAlertPosture(newDailySummary.getAlertPosture());
        dailySummary.setCorrectPosture(newDailySummary.getCorrectPosture());
        dailySummary.setWrongPosture(newDailySummary.getWrongPosture());
        dailySummary.setDate(newDailySummary.getDate());

        dailySummaryRepository.save(dailySummary);

        return ResponseEntity.ok(dailySummary);
    }

}
