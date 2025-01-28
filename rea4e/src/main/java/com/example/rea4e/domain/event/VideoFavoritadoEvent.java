package com.example.rea4e.domain.event;

import org.springframework.context.ApplicationEvent;


public class VideoFavoritadoEvent extends ApplicationEvent{
    private final Long usuarioId;
    private final Long videoId;    

    public VideoFavoritadoEvent(Object source, Long usuarioId, Long videoId) {
        super(source);
        this.usuarioId = usuarioId;
        this.videoId = videoId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    
}