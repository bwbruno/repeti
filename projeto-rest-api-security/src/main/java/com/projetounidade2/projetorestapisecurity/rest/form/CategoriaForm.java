package com.projetounidade2.projetorestapisecurity.rest.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.projetounidade2.projetorestapisecurity.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaForm {
    
    @NotNull @NotEmpty @Length(min = 5)
    private String categoria;

    public Categoria converter() {
        return new Categoria(this.categoria);
    }
}
