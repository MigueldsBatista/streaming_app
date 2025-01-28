package com.example.rea4e.domain.service;

import java.util.List;

import com.example.rea4e.domain.entity.Categorias;
import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.entity.Video;

public interface VideoService {
    
Video salvar(Video video);

Video buscarPorId(Long id);

void deletar(Long id);

List<Video> listar();

List<Video> listarvideosPorAutor(Usuario autor);

List<Video> listarvideosPorAutor(Long autorId);

List<Video> listarvideosPorCategoria(Categorias categoria);

}
