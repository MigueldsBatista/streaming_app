package com.example.rea4e.domain.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.example.rea4e.domain.event.AdicionarPermissaoUsuarioEvent;
import com.example.rea4e.domain.event.RemoverPermissaoUsuarioEvent;
import com.example.rea4e.domain.event.VideoDesfavoritadoEvent;
import com.example.rea4e.domain.event.VideoFavoritadoEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioEventPublisher {
    private final ApplicationEventPublisher publisher;


    public void publishvideoFavoritado(Long usuarioId, Long videoId) {
        VideoFavoritadoEvent event = new VideoFavoritadoEvent(this, usuarioId, videoId);
        publisher.publishEvent(event);
    }

    public void publishvideoDesfavoritado(Long usuarioId, Long videoId) {
    VideoDesfavoritadoEvent event = new VideoDesfavoritadoEvent(this, usuarioId, videoId);
    publisher.publishEvent(event);
    }

    public void publishAdicionarPermissao(Long usuaroId, String permissao){
        AdicionarPermissaoUsuarioEvent event = new AdicionarPermissaoUsuarioEvent(this, usuaroId, permissao);
        publisher.publishEvent(event);
    }
    public void publishRemoverPermissao(Long usuaroId, String permissao){
        RemoverPermissaoUsuarioEvent event = new RemoverPermissaoUsuarioEvent(this, usuaroId, permissao);
        publisher.publishEvent(event);
    }


    
}
