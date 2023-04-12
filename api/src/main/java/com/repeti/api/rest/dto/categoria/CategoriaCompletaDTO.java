package com.repeti.api.rest.dto.categoria;

import java.util.ArrayList;
import java.util.List;

import com.repeti.api.model.Categoria;
import com.repeti.api.model.Questao;
import com.repeti.api.rest.dto.questao.QuestaoCompletaDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaCompletaDTO {
    private Integer id;
    private String categoria;
    private List<QuestaoCompletaDTO> questoes; 


    public CategoriaCompletaDTO(Categoria categoria){
        this.id = categoria.getId();
        this.categoria = categoria.getCategoria();

        List<QuestaoCompletaDTO> a = new ArrayList<QuestaoCompletaDTO>();
        // for (Questao questao : categoria.getQuestoes()) {
        //     a.add(QuestaoCompletaDTO.from(questao));
        // }
        this.questoes = a;
    }
}
