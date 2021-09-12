package br.com.fiap.upposture.controller.api;

import br.com.fiap.upposture.model.StatusEnum;
import br.com.fiap.upposture.model.User;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        repository.save(user);

        URI uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@RequestBody @Valid User newUser, @PathVariable Long id ) {
        Optional<User> optional = repository.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        User user = optional.get();

        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        repository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> inactive(@PathVariable Long id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        user.get().setStatus(StatusEnum.INACTIVE);
        repository.save(user.get());

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> login(@RequestBody @Valid LoginResource login ) {
        return ResponseEntity.of(repository.findByEmailAndPasswordAndStatus(login.getEmail(), login.getPassword(), StatusEnum.ACTIVE));
    }

}

/*
@Autowired
    private UserRepository repository;

    @GetMapping
    public Page<User> index(@RequestParam(required = false) String name,
                            @PageableDefault(size = 20) Pageable pageable) {
        if (name == null)
            return repository.findAll(pageable);

        return repository.findByNameContains(name, pageable);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        repository.save(user);
        URI uri = uriBuilder
                .path("/api/user/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.ok().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@RequestBody @Valid User newUser, @PathVariable Long id ) {
        Optional<User> optional = repository.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        User user = optional.get();

        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        repository.save(user);

        log.info("Usuário id=" + id + " alterado para " + user.toString());

        return ResponseEntity.ok(user);
    }
 */
