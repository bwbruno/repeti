package com.projetounidade2.projetorestapisecurity.rest.dto.lista_de_estudos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CriarListaDeEstudosDto {
    @NotBlank(message = "Campo 'nome' não pode ser vazio")
    String nome;
}
