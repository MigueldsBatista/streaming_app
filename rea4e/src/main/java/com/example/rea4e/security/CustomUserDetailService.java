package com.example.rea4e.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.service.UsuarioService;

import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

    private final UsuarioService usr;
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usr.obterUsuarioPorEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getSenha())
            .roles(usuario.getPermissoes().toArray(new String[0]))//String
            .build();
    }

    
}
