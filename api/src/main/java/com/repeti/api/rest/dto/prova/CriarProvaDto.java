package com.repeti.api.rest.dto.prova;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CriarProvaDto {
    @NotBlank(message = "Campo 'nome' não pode ser vazio")
    String nome;
}
