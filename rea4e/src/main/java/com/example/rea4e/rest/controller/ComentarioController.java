package com.example.rea4e.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rea4e.domain.entity.Comentario;
import com.example.rea4e.domain.service.ComentarioService;

@RestController
@RequestMapping("api/comentarios")
public class ComentarioController {

private final ComentarioService servico;

public ComentarioController(ComentarioService servico) {
    this.servico = servico;
}

    @PostMapping
    public ResponseEntity<Comentario> salvar(@RequestBody Comentario comentario ) {
        Comentario comentarioSalvo = servico.salvar(comentario);
        return ResponseEntity.ok(comentarioSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
        Comentario comentario = servico.buscarPorId(id);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario comentarioAtualizado = servico.salvar(comentario);
        return ResponseEntity.ok(comentarioAtualizado);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<Comentario>> listarComentariosPorvideo(@PathVariable Long videoId) {
        List<Comentario> comentario = servico.listarComentariosPorvideo(videoId);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/video/{videoId}/contar")
    public ResponseEntity<Integer> contarComentariosPorvideo(@PathVariable Long videoId) {
        Integer comentario = servico.contarComentariosPorvideo(videoId);
        return ResponseEntity.ok(comentario);
    }

}
