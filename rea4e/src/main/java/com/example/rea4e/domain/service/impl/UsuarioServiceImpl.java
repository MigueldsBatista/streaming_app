package com.example.rea4e.domain.service.impl;

import org.springframework.stereotype.Service;

import com.example.rea4e.domain.entity.Usuario;

import com.example.rea4e.domain.service.BaseService;
import com.example.rea4e.domain.service.UsuarioEventPublisher;
import com.example.rea4e.domain.service.UsuarioService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UsuarioServiceImpl extends BaseService<Usuario> implements UsuarioService {
    
    private final UsuarioEventPublisher eventPublisher;

    public UsuarioServiceImpl(UsuarioEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    public void favoritarVideo(Long usuarioId, Long videoId){
        eventPublisher.publishvideoFavoritado(usuarioId, videoId);
    }
    
    public void desfavoritarVideo(Long usuarioId, Long videoId){
        eventPublisher.publishvideoDesfavoritado(usuarioId, videoId);
    }

   


}

