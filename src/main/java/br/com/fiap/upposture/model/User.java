package br.com.fiap.upposture.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String password;

    @Size(max = 14)
    private String cpf;

    @Size(max = 20)
    private String phone;

    @Size(max = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

}
