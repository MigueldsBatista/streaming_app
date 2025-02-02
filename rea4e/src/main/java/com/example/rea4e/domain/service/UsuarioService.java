package com.example.rea4e.domain.service;


import com.example.rea4e.domain.entity.Usuario;


public interface UsuarioService {

Usuario salvar(Usuario usuario);
void deletar(Long id);
Usuario buscarPorId(Long id);
void favoritarVideo(Long usuarioId, Long video);
void desfavoritarVideo(Long usuarioId, Long videoId);
void adicionarPermissaoUsuario(Long usuarioId, String permissao);
void removerPermissaoUsuario(Long usuarioId, String permissao);
Usuario obterUsuarioPorEmail(String email);

}
