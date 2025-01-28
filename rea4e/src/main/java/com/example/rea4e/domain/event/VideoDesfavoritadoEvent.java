package com.example.rea4e.domain.event;

import org.springframework.context.ApplicationEvent;

public class VideoDesfavoritadoEvent extends ApplicationEvent {
    private final Long usuarioId;
    private final Long videoId;

    public VideoDesfavoritadoEvent(Object source, Long usuarioId, Long videoId) {
        super(source);
        this.usuarioId = usuarioId;
        this.videoId = videoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getvideoId() {
        return videoId;
    }
}
