package br.com.fiap.upposture.controller.api;

import br.com.fiap.upposture.model.PositionEnum;
import br.com.fiap.upposture.model.Posture;
import br.com.fiap.upposture.repository.PostureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postures")
public class PostureController {

    @Autowired
    private PostureRepository postureRepository;

    @GetMapping
    public Page<Posture> index(@RequestParam(required = false) PositionEnum position,
                               @PageableDefault(size = 20) Pageable pageable) {
        if (position == null)
            return postureRepository.findAll(pageable);

        return postureRepository.findByPosition(position, pageable);
    }

}
