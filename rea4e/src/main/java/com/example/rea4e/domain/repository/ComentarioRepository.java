package com.example.rea4e.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rea4e.domain.entity.Comentario;
import com.example.rea4e.domain.entity.Video;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> { // Long é o tipo da chave primária da tabela

    // public List<Comentario> findByNameLike(String name);

    // Retorna todos os comentários de um video específico
    List<Comentario> findByVideoRelacionado(Video video);

    // Alternativamente, para buscar usando o ID do video relacionado
    List<Comentario> findByVideoRelacionado_Id(Long videoId);

    

}
