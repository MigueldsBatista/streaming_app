package com.example.rea4e.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rea4e.domain.entity.Comentario;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.repository.ComentarioRepository;
import com.example.rea4e.domain.service.BaseService;
import com.example.rea4e.domain.service.ComentarioService;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class ComentarioServiceImpl extends BaseService<Comentario> implements ComentarioService{
private final ComentarioRepository comentarioRepository;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    // Todos os métodos estão disponíveis via herança.
    // Implementações específicas do domínio podem ser adicionadas aqui
    public List<Comentario> listarComentariosPorvideo(Video video){
        List<Comentario> lista = comentarioRepository.findByVideoRelacionado(video);
        return lista;
    }

   public List<Comentario> listarComentariosPorvideo(Long videoId){
        List<Comentario> lista = comentarioRepository.findByVideoRelacionado_Id(videoId);
        return lista;
    }

    public Integer contarComentariosPorvideo(Long videoId){
        Integer comentarios = comentarioRepository.findByVideoRelacionado_Id(videoId).size();
        return comentarios;
    }


}