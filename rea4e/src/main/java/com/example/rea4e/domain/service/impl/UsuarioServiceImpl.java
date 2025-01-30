package com.example.rea4e.domain.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.publisher.UsuarioEventPublisher;
import com.example.rea4e.domain.repository.UsuarioRepository;
import com.example.rea4e.domain.service.BaseService;
import com.example.rea4e.domain.service.UsuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends BaseService<Usuario> implements UsuarioService {
    
    private final UsuarioEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repositorio;
    
    public void favoritarVideo(Long usuarioId, Long videoId){
        eventPublisher.publishvideoFavoritado(usuarioId, videoId);
    }
    
    public void desfavoritarVideo(Long usuarioId, Long videoId){
        eventPublisher.publishvideoDesfavoritado(usuarioId, videoId);
    }

   
    @Override
    public void adicionarPermissaoUsuario(Long usuarioId, String permissao) {
        eventPublisher.publishAdicionarPermissao(usuarioId, permissao);        
    }

    @Override
    public void removerPermissaoUsuario(Long usuarioId, String permissao) {
        eventPublisher.publishRemoverPermissao(usuarioId, permissao);
    }


    @Override
    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return super.salvar(usuario);
    }   

    @Override
    public Usuario obterUsuarioPorEmail(String email) {
        return repositorio.findByEmail(email);
        
    }
}

