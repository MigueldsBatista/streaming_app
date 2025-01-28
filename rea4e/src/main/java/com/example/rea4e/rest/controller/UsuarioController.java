package com.example.rea4e.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rea4e.domain.entity.Usuario;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.service.UsuarioService;

@RestController//RestController vai anotar os metodos com @ResponseBody que indica o retorno em JSON
@RequestMapping("/api/usuario")//RequestMapping vai mapear a URL
public class UsuarioController {

    @Autowired
    private UsuarioService usr;


    // Achar um usuário pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usr.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // Criar um usuário
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usr.salvar(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    // Deletar um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usr.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Favoritar uma aula
    @PostMapping("/{usuarioId}/favoritar/{videoId}")
    public ResponseEntity<Void> favoritarAula(@PathVariable Long usuarioId, @PathVariable Long videoId) {
        usr.favoritarVideo(usuarioId, videoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}/favoritos")
    public ResponseEntity<List<Video>> listarFavoritos(@PathVariable Long usuarioId) {
        Usuario usuario = usr.buscarPorId(usuarioId);
        return ResponseEntity.ok(usuario.getVideosFavoritos());
    }
    
    // Desfavoritar uma aula
    @DeleteMapping("/{usuarioId}/favoritar/{videoId}")
    public ResponseEntity<Void> desfavoritarAula(@PathVariable Long usuarioId, @PathVariable Long videoId) {
        usr.desfavoritarVideo(usuarioId, videoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return ResponseEntity.ok(usr.salvar(usuario));
    }


}
