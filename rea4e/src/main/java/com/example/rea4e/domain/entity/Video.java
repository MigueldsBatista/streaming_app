package com.example.rea4e.domain.entity;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VIDEO")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(nullable = false, name = "TITULO") 
    private String titulo;

    @ManyToOne//muitos videos para um usuario
    @JoinColumn(name = "USUARIO_ID", nullable = false) 
    private Usuario autor;

    @Column(nullable = false, name = "URL") 
    private String url;      // URL do video

    @Column(length = 500, name = "DESCRICAO") // Sugestão: limite de caracteres para descrição
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "CATEGORIA")
    private Categorias categoria;    


    public Video(String titulo, Usuario autor, String url, String descricao, Categorias categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.url = url;
        this.descricao = descricao;
        this.categoria = categoria;
    }

}
