package com.example.rea4e.domain.listener;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.rea4e.domain.entity.*;
import com.example.rea4e.domain.event.*;
import com.example.rea4e.domain.exception.*;
import com.example.rea4e.domain.service.*;
import com.example.rea4e.domain.repository.UsuarioRepository;

@Component
public class UsuarioEventListener {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final RecursoEducacionalAbertoService reaService;

    public UsuarioEventListener(UsuarioService usuarioService, UsuarioRepository usuarioRepository, RecursoEducacionalAbertoService reaService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.reaService = reaService;
    }

    @EventListener
    public void handleRecursoFavoritadoEvent(RecursoFavoritadoEvent event) {
        Long usuarioId=event.getUsuarioId();
        Long recursoId=event.getRecursoId();
        RecursoEducacionalAberto recurso = reaService.buscarPorId(recursoId);//aqui vai verificar se o recurso existe
        Usuario usuario = usuarioService.buscarPorId(usuarioId);//a função verifica se a entidade existe
        if (usuario.getReasFavoritos().contains(recurso)) {
            throw new ResourceAlreadyFavoritedException("Recurso já favoritado.");
        }
            usuario.getReasFavoritos().add(recurso);
            usuarioService.salvar(usuario);
        
    }

    @EventListener
    public void handleRecursoDesfavoritadoEvent(RecursoDesfavoritadoEvent event) {
        Long usuarioId=event.getUsuarioId();
        Long recursoId=event.getRecursoId();
        RecursoEducacionalAberto recurso = reaService.buscarPorId(recursoId);//aqui vai verificar se o recurso existe
        Usuario usuario = usuarioService.buscarPorId(usuarioId);//a função verifica se a entidade existe
        if (!usuario.getReasFavoritos().contains(recurso)) {
            throw new ResourceNotPresentInFavouritesException("Recurso não está presente na lista de favoritos.");
        }
            usuario.getReasFavoritos().add(recurso);
            usuarioRepository.save(usuario);
    }

}