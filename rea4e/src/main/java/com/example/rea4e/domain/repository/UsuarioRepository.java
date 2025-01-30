package com.example.rea4e.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.rea4e.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM USUARIO WHERE EMAIL = :email", nativeQuery = true)
    Usuario findByEmail(String email);
}