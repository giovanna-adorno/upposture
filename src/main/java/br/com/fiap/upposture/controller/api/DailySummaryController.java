package br.com.fiap.upposture.controller.api;

import br.com.fiap.upposture.repository.DailySummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daily-summary")
public class DailySummaryController {

    @Autowired
    private DailySummaryRepository repository;

}
