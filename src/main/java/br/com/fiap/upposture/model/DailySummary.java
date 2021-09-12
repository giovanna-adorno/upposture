package br.com.fiap.upposture.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class DailySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal wrongPosture;

    @Digits(integer = 3, fraction = 2)
    private BigDecimal alertPosture;

    @Digits(integer = 3, fraction = 2)
    private double correctPosture;

    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}