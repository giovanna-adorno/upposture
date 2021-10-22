package br.com.fiap.upposture.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo wrongPosture é obrigatório")
    @Digits(integer = 3, fraction = 2, message = "O campo deve possuir no máximo 3 dígitos inteiros e 2 decimais")
    private BigDecimal wrongPosture;

    @NotNull(message = "O campo correctPosture é obrigatório")
    @Digits(integer = 3, fraction = 2, message = "O campo deve possuir no máximo 3 dígitos inteiros e 2 decimais")
    private BigDecimal correctPosture;

    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}