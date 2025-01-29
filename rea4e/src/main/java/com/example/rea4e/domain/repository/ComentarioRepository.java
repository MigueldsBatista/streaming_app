package com.example.rea4e.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.rea4e.domain.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Retorna todos os comentários de um video específico
    @Query(value = "SELECT * FROM COMENTARIO WHERE VIDEO_ID = :videoId", nativeQuery = true)
    List<Comentario> findByVideoId(Long videoId);


}
