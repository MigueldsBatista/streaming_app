package com.example.rea4e.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "ID", nullable = false)  
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;


    @JsonIgnore//diz para o parser que isso deve ser ignorado no nosso json
    @ManyToMany
    @JoinTable(
        name = "USUARIOS_FAVORITOS",
        joinColumns = @JoinColumn(name = "USUARIO_ID"),
        inverseJoinColumns = @JoinColumn(name = "VIDEO_ID")
    )
    private List<Video> videosFavoritos = new ArrayList<>(); // Inicializando
    
    
    public Usuario (String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
}



