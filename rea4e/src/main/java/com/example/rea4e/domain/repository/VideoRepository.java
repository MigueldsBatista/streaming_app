package com.example.rea4e.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.rea4e.domain.entity.Video;



@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

@Query(value = "SELECT * FROM VIDEO WHERE USUARIO_ID = :autorId", nativeQuery = true)
List<Video> findByAutor(Long autorId);

@Query(value = "SELECT * FROM VIDEO WHERE CATEGORIA = :categoria", nativeQuery = true)
List<Video> findByCategoria(String categoria);//Por meio da assinatura do método, o Spring Data JPA entende que a consulta deve ser feita com base no atributo categoria da entidade Video.
}
