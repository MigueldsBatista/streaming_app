package com.example.rea4e.domain.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;


import java.util.ArrayList;
import java.util.HashSet;

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

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "NOME", nullable = false)
    private String nome;
 
    @ManyToOne//um criador cria varios usuarios
    @JoinColumn(name = "CRIADOR_ID")
    private Usuario criador;

    @ManyToMany
    @JoinTable(
        name = "CURTIDAS",
        joinColumns = @JoinColumn(name = "USUARIO_ID"),
        inverseJoinColumns = @JoinColumn(name = "VIDEO_ID")
        
    )
    private List<Video> videosFavoritos = new ArrayList<>(); // Inicializando
    
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "PERMISSOES",
        joinColumns = @JoinColumn(name = "USUARIO_ID")
        
    )
    @Column(name = "PERMISSAO")
    private Set<String> permissoes = new HashSet<String>();


    public Usuario (String email, String password, String name){
        this.email = email;
        this.senha = password;
        this.nome = name;
    }

    
}



