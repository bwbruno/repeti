package com.projetounidade2.projetorestapisecurity.rest.controllers;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projetounidade2.projetorestapisecurity.exception.SenhaInvalidaException;
import com.projetounidade2.projetorestapisecurity.model.Permissao;
import com.projetounidade2.projetorestapisecurity.model.Usuario;
import com.projetounidade2.projetorestapisecurity.rest.dto.TokenDTO;
import com.projetounidade2.projetorestapisecurity.rest.dto.UsuarioDTO;
import com.projetounidade2.projetorestapisecurity.rest.form.LoginForm;
import com.projetounidade2.projetorestapisecurity.rest.form.UsuarioPermissaoForm;
import com.projetounidade2.projetorestapisecurity.security.JwtService;
import com.projetounidade2.projetorestapisecurity.service.PermissaoService;
import com.projetounidade2.projetorestapisecurity.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PermissaoService permissaoService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @GetMapping
    public List<UsuarioDTO> find() {
       return UsuarioDTO.converter(usuarioService.getListUsuario()); 
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        return UsuarioDTO.converter(usuario);
    }

    @GetMapping("/info")
    public Usuario info(Principal principal) {
        Usuario usuario = usuarioService.findByEmail(principal.getName());
        return usuario;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar( @RequestBody @Valid Usuario usuario ) {

        if(usuarioService.isEmailNotUsed(usuario)){
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            return UsuarioDTO.converter(usuarioService.salvar(usuario));
        }
        return new UsuarioDTO();
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody LoginForm credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getEmail(), "Bearer", token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("{id}/permissoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UsuarioDTO atribuirPermissao(@PathVariable Integer id, @RequestBody UsuarioPermissaoForm form) {
            Permissao permissao = permissaoService.getPermissaoByNome(form.getNomePermissao());
            Usuario usuario = usuarioService.atribuirPermissao(id, permissao);
            return new UsuarioDTO(usuario);

    }
    @PostMapping("{email}/email/permissao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UsuarioDTO atribuirPermissaoporEmail(@PathVariable String email) {
            Permissao permissao = permissaoService.getPermissaoByNome("ROLE_CLIENTEFREE");
            Usuario usuario = usuarioService.atribuirPermissaoPorEmail(email, permissao);
            return new UsuarioDTO(usuario);

    }


    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id) {
        usuarioService.removeUsuario(usuarioService.getUsuarioById(Integer.parseInt(id)));
    }
}
