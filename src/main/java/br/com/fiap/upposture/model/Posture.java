package br.com.fiap.upposture.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Posture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A posição deve ser válida")
    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TipPosture",
            joinColumns = @JoinColumn(name = "posture_id"),
            inverseJoinColumns = @JoinColumn(name = "tip_id"))
    private List<Tip> tips;

}