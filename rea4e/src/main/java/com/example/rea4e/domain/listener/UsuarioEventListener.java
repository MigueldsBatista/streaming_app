package com.example.rea4e.domain.listener;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.event.VideoDesfavoritadoEvent;
import com.example.rea4e.domain.event.VideoFavoritadoEvent;
import com.example.rea4e.domain.exception.ResourceAlreadyFavoritedException;
import com.example.rea4e.domain.exception.ResourceNotPresentInFavouritesException;
import com.example.rea4e.domain.repository.UsuarioRepository;
import com.example.rea4e.domain.service.UsuarioService;
import com.example.rea4e.domain.service.VideoService;

@Component
public class UsuarioEventListener {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final VideoService reaService;

    public UsuarioEventListener(UsuarioService usuarioService, UsuarioRepository usuarioRepository, VideoService reaService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.reaService = reaService;
    }

    @EventListener
    public void handlevideoFavoritadoEvent(VideoFavoritadoEvent event) {
        Long usuarioId=event.getUsuarioId();
        Long videoId=event.getVideoId();
        Video video = reaService.buscarPorId(videoId);//aqui vai verificar se o video existe
        Usuario usuario = usuarioService.buscarPorId(usuarioId);//a função verifica se a entidade existe
        if (usuario.getVideosFavoritos().contains(video)) {
            throw new ResourceAlreadyFavoritedException("Video já favoritado.");
        }
            usuario.getVideosFavoritos().add(video);
            usuarioService.salvar(usuario);
        
    }

    @EventListener
    public void handleVideoDesfavoritadoEvent(VideoDesfavoritadoEvent event) {
        Long usuarioId=event.getUsuarioId();
        Long videoId=event.getvideoId();
        Video video = reaService.buscarPorId(videoId);//aqui vai verificar se o video existe
        Usuario usuario = usuarioService.buscarPorId(usuarioId);//a função verifica se a entidade existe
        if (!usuario.getVideosFavoritos().contains(video)) {
            throw new ResourceNotPresentInFavouritesException("Video não está presente na lista de favoritos.");
        }
            usuario.getVideosFavoritos().remove(video);
            usuarioRepository.save(usuario);
    }

}