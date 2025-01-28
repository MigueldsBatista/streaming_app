package com.example.rea4e.domain.service;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.rea4e.domain.event.*;

@Service
public class UsuarioEventPublisher {
    private final ApplicationEventPublisher publisher;

    public UsuarioEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishvideoFavoritado(Long usuarioId, Long videoId) {
        VideoFavoritadoEvent event = new VideoFavoritadoEvent(this, usuarioId, videoId);
        publisher.publishEvent(event);
    }

    public void publishvideoDesfavoritado(Long usuarioId, Long videoId) {
    VideoDesfavoritadoEvent event = new VideoDesfavoritadoEvent(this, usuarioId, videoId);
    publisher.publishEvent(event);
    }

}
