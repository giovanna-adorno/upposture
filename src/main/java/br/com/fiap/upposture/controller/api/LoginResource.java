package br.com.fiap.upposture.controller.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginResource {

    @Size(max = 100)
    @NotBlank
    private String email;

    @Size(max = 100)
    @NotBlank
    private String password;

}
