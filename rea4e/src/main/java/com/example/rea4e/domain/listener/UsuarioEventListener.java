package com.example.rea4e.domain.listener;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.event.AdicionarPermissaoUsuarioEvent;
import com.example.rea4e.domain.event.RemoverPermissaoUsuarioEvent;
import com.example.rea4e.domain.event.VideoDesfavoritadoEvent;
import com.example.rea4e.domain.event.VideoFavoritadoEvent;
import com.example.rea4e.domain.exception.ValueNotFoundException;
import com.example.rea4e.domain.exception.DuplicateException;
import com.example.rea4e.domain.service.UsuarioService;
import com.example.rea4e.domain.service.VideoService;

@Component
public class UsuarioEventListener {
    private final UsuarioService usuarioService;
    private final VideoService reaService;

    public UsuarioEventListener(UsuarioService usuarioService, VideoService reaService) {
        this.usuarioService = usuarioService;
        this.reaService = reaService;
    }

    @EventListener
    public void handlevideoFavoritadoEvent(VideoFavoritadoEvent event) {
        Long usuarioId=event.getUsuarioId();
        Long videoId=event.getVideoId();
        Video video = reaService.buscarPorId(videoId);//aqui vai verificar se o video existe
        Usuario usuario = usuarioService.buscarPorId(usuarioId);//a função verifica se a entidade existe
        if (usuario.getVideosFavoritos().contains(video)) {
            throw new DuplicateException("Video já favoritado.");
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
            throw new ValueNotFoundException("Video não está presente na lista de favoritos.");
        }
            usuario.getVideosFavoritos().remove(video);
            usuarioService.salvar(usuario);
    }

    @EventListener
    public void handleAdicionarPermissao(AdicionarPermissaoUsuarioEvent event){
        Long usuarioId=event.getUsuarioId();
        String permissao = event.getPermissao();
        
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        if(usuario.getPermissoes().contains(permissao)){
            throw new DuplicateException("Permissão já adicionada a este usuário.");
        }
        usuario.getPermissoes().add(permissao);
        usuarioService.salvar(usuario);
    }

    @EventListener
    public void handleRemoverPermissao(RemoverPermissaoUsuarioEvent event){
        Long usuarioId=event.getUsuarioId();
        String permissao = event.getPermissao();
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        if(usuario.getPermissoes().contains(permissao)){
            throw new ValueNotFoundException("Permissão não listada no usuário");
        }
        usuario.getPermissoes().remove(permissao);
        usuarioService.salvar(usuario);
    }

}


/*
 * 
 * public void adicionarPermissaoUsuario(String permissao) {
        
        if(permissao.isEmpty() || permissao.isBlank()){
            throw new IllegalArgumentException("A permissão não pode estar vazia");
        } 

        if (this.grupos == null || this.grupos.isEmpty()) {
            this.grupos = permissao; // Caso não tenha permissões, define a primeira.
        } else if (!this.grupos.contains(permissao)) {
            this.grupos += "," + permissao; // Adiciona apenas se não existir.
        }
    }
    
    public void removerPermissaoUsuario(String permissao) {
        if (this.grupos == null || this.grupos.isEmpty()) {
            return; // Se estiver vazio, não há o que remover.
        }
    
        // Substitui a permissão removida garantindo que não haja vírgulas extras
        this.grupos = this.grupos.replace("," + permissao, "")
                                 .replace(permissao + ",", "")
                                 .replace(permissao, "")
                                 .replaceAll(",{2,}", ",") // Remove vírgulas duplas
                                 .replaceAll("^,|,$", ""); // Remove vírgulas do início ou fim
    }
 * 
 */