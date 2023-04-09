package com.repeti.api.service.impl;

import java.util.List;

import com.repeti.api.exception.RegraNegocioException;
import com.repeti.api.model.Permissao;
import com.repeti.api.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.repeti.api.service.PermissaoService;


@Component
public class PermissaoServiceImpl implements PermissaoService {

    @Autowired
    PermissaoRepository permissaoRepository;

    @Override
    public Permissao savePermissao(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    @Override
    public void removePermissao(Long id) {
       permissaoRepository.deleteById(id);
    }

    @Override
    public Permissao getPermissaoById(Long id) {
        return permissaoRepository
                        .findById(id)
                        .map(
                            permissao -> {
                                return permissao;
                            }
                        ).orElseThrow(
                            () -> new RegraNegocioException("Permissão '" + id + "' não foi encontrada")
                        );
    }

    @Override
    public Permissao getPermissaoByNome(String nome) {
        return permissaoRepository
                        .findByNome(nome)
                        .map(
                            permissao -> {
                                return permissao;
                            }
                        ).orElseThrow(
                            () -> new RegraNegocioException("Permissão '" + nome + "' não foi encontrada")
                        );
    }

    @Override
    public List<Permissao> getListPermissao() {
        return permissaoRepository.findAll();
    }

    @Override
    public void atualizarPermissao(Long id, String nome) {
      var p = permissaoRepository.getReferenceById(id);
      p.setNome(nome);
      permissaoRepository.save(p);
    }
   
}