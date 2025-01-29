package com.example.rea4e.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.rest.dto.UsuarioDTO;


@Component
public class UsuarioMapper {
    public UsuarioMapper(){}

    public Usuario toUsuario(UsuarioDTO usuarioDTO){
        Usuario candidate = new Usuario();
        candidate.setEmail(usuarioDTO.getEmail());
        candidate.setId(usuarioDTO.getId());
        candidate.setNome(usuarioDTO.getNome());
        candidate.setSenha(usuarioDTO.getSenha());
        return candidate;
    }

    public UsuarioDTO toDTO(Usuario usuario){
        UsuarioDTO candidate = new UsuarioDTO();
        candidate.setEmail(usuario.getEmail());
        candidate.setNome(usuario.getNome());
        candidate.setId(usuario.getId());
        candidate.setSenha(usuario.getSenha());
        return candidate;
    }

    public List<UsuarioDTO> toDTOList(List<Usuario> usuarios){
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
