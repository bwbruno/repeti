package com.projetounidade2.projetorestapisecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetounidade2.projetorestapisecurity.model.Alternativa;

public interface AlternativaRepository extends JpaRepository<Alternativa, Integer> {

    @Query(value = "SELECT a FROM Alternativa a WHERE a.questao.id = :questaoId")
    List<Alternativa> recuperarPorQuestaoId(@Param("questaoId") int questaoId);
}
