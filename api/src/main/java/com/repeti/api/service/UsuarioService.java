package com.repeti.api.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.repeti.api.model.Usuario;


@Service
public interface UsuarioService extends UserDetailsService {
    
    public Usuario salvar(Usuario usuario);

    public void removeUsuario(Usuario usuario);

    public Usuario getUsuarioById(Integer id);

    public Usuario findByEmail(String email);

    public List<Usuario> getListUsuario();

    public UserDetails autenticar(Usuario usuario);

    public Boolean isEmailNotUsed(Usuario usuario);

}
