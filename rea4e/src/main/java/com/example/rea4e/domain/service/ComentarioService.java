package com.example.rea4e.domain.service;
import java.util.List;

import com.example.rea4e.domain.entity.Comentario;
import com.example.rea4e.domain.entity.Video;

public interface ComentarioService {
Comentario salvar(Comentario comentario);
Comentario buscarPorId(Long id);
void deletar(Long id);

//TODO: listarComentariosPorvideo(Long videoId)
List<Comentario> listarComentariosPorvideo(Long videoId);

//TODO: buscarComentariosPorUsuario(Long usuarioId)

//TODO: contarComentariosPorvideo(Long videoId)

Integer contarComentariosPorvideo(Long videoId);

List<Comentario> listarComentariosPorvideo(Video video);


}
