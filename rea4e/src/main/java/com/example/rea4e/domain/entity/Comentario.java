package com.example.rea4e.domain.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "COMENTARIO")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PERGUNTA", nullable = false)
    private String pergunta;

    @ManyToOne // Um comentário é escrito por um único autor
    @JsonIgnore
    @JsonManagedReference
    @JoinColumn(name = "USUARIO_ID", nullable = false) // Nome da coluna no banco de dados
    @OnDelete(action = OnDeleteAction.CASCADE)//se o usuario for deletado, os comentarios tambem serao
    private Usuario autor;

    @ManyToOne
    @JsonIgnore//ignora a serialização
    @JsonManagedReference//referencia gerenciada
    @JoinColumn(name = "VIDEO_ID", nullable = false) // Nome da coluna no banco de dados
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Video videoRelacionado;


    public Comentario(String pergunta, Usuario autor, Video reaRelacionado) {
        this.pergunta = pergunta;
        this.autor = autor;
        this.videoRelacionado = reaRelacionado;
    }
}
