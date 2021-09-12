package br.com.fiap.upposture.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "E-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    @NotBlank(message = "O campo e-mail é obrigatório")
    private String email;

    @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
    @NotBlank(message = "O campo password é obrigatório")
    private String password;

    @Size(max = 11, message = "O cpf deve ter no máximo 11 caracteres")
    @NotBlank(message = "O campo cpf é obrigatório")
    private String cpf;

    @Size(max = 20, message = "O telefone deve ter no máximo 100 caracteres")
    @NotBlank(message = "O campo phone é obrigatório")
    private String phone;

    @Size(max = 100 , message = "O nome deve ter no máximo 100 caracteres")
    @NotBlank(message = "O campo name é obrigatório")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo status é obrigatório")
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo gender é obrigatório")
    private GenderEnum gender;

}
