package com.example.rea4e.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rea4e.domain.entity.Categorias;
import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.repository.VideoRepository;
import com.example.rea4e.domain.service.BaseService;
import com.example.rea4e.domain.service.UsuarioService;
import com.example.rea4e.domain.service.VideoService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class VideoServiceImpl extends BaseService<Video> implements VideoService {
    private final UsuarioService usuarioService;
    private final VideoRepository reaRepositorio;

    public VideoServiceImpl(UsuarioService usuarioService, 
                                            VideoRepository reaRepositorio) {
        this.usuarioService = usuarioService;
        this.reaRepositorio = reaRepositorio;
    }

    @Override
    public List<Video> listarvideosPorAutor(Long autorId) {
        Usuario autor = usuarioService.buscarPorId(autorId);

        return listarvideosPorAutor(autor);//
    }

    @Override
    public List<Video> listarvideosPorAutor(Usuario autor) {
        List<Video> lista = reaRepositorio.findByAutor(autor.getId());
        return lista;
    }

    @Override
    public List<Video> listarvideosPorCategoria(Categorias categoria) {
        String cat = categoria.toString();
        List<Video> lista = reaRepositorio.findByCategoria(cat);
        return lista;
    }



}
