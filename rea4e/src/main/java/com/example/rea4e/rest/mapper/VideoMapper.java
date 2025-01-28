package com.example.rea4e.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.service.UsuarioService;
import com.example.rea4e.rest.dto.VideoDTO;

@Component
public class VideoMapper{
    private final UsuarioService usr;
    public VideoMapper(UsuarioService usr) {
        this.usr = usr;
    }
    public Video toVideo(VideoDTO videoDTO) {
        Video video = new Video();
        video.setTitulo(videoDTO.getTitulo());
        video.setDescricao(videoDTO.getDescricao());
        video.setUrl(videoDTO.getUrl());
        video.setCategoria(videoDTO.getCategoria());
        Usuario autor = usr.buscarPorId(videoDTO.getAutorId());
        Assert.notNull(autor, "Autor não encontrado");
        video.setAutor(autor);
        
        return video;
    }
    public VideoDTO toDTO(Video video) {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setTitulo(video.getTitulo());
        videoDTO.setDescricao(video.getDescricao());
        videoDTO.setAutorId(video.getId());
        videoDTO.setId(video.getId());
        videoDTO.setUrl(video.getUrl());
        videoDTO.setCategoria(video.getCategoria());
        return videoDTO;
    }
    public List<VideoDTO> toDTOList(List<Video> videos) {
        return videos.
        stream().
        map(this::toDTO).
        collect(Collectors.toList());
    }
}