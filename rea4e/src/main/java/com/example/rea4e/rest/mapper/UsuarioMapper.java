package com.example.rea4e.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.rest.dto.UsuarioDTO;

public class UsuarioMapper {
    public UsuarioMapper(){}

    public Usuario toUsuario(UsuarioDTO usuarioDTO){
        Usuario candidate = new Usuario();
        candidate.setEmail(usuarioDTO.getEmail());
        candidate.setId(usuarioDTO.getId());
        candidate.setName(usuarioDTO.getName());
        return candidate;
    }

    public UsuarioDTO toDTO(Usuario usuario){
        UsuarioDTO candidate = new UsuarioDTO();
        candidate.setEmail(usuario.getEmail());
        candidate.setName(usuario.getName());
        candidate.setId(usuario.getId());
        return candidate;
    }

    public List<UsuarioDTO> toDTOList(List<Usuario> usuarios){
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
