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
import com.example.rea4e.rest.dto.UsuarioDTO;
import com.example.rea4e.rest.dto.VideoDTO;
import com.example.rea4e.rest.mapper.UsuarioMapper;
import com.example.rea4e.rest.mapper.VideoMapper;

@RestController//RestController vai anotar os metodos com @ResponseBody que indica o retorno em JSON
@RequestMapping("/api/usuario")//RequestMapping vai mapear a URL
public class UsuarioController {
    private UsuarioService usr;
    private UsuarioMapper userMapper;
    private VideoMapper videoMapper;
    public UsuarioController(UsuarioService usr, UsuarioMapper mapper, VideoMapper videoMapper) {
        this.usr = usr;
        this.userMapper = mapper;
        this.videoMapper = videoMapper;
    }


    // Achar um usuário pelo id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usr.buscarPorId(id);
        UsuarioDTO usuarioDTO = userMapper.toDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    // Criar um usuário
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = userMapper.toUsuario(usuarioDTO);
        Usuario saved = usr.salvar(usuario);
        UsuarioDTO savedDTO = userMapper.toDTO(saved);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
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
    public ResponseEntity<List<VideoDTO>> listarFavoritos(@PathVariable Long usuarioId) {
        Usuario usuario = usr.buscarPorId(usuarioId);
        List<Video> favoritos = usuario.getVideosFavoritos();
        return ResponseEntity.ok(videoMapper.toDTOList(favoritos));
    }
    
    // Desfavoritar uma aula
    @DeleteMapping("/{usuarioId}/favoritar/{videoId}")
    public ResponseEntity<Void> desfavoritarAula(@PathVariable Long usuarioId, @PathVariable Long videoId) {
        usr.desfavoritarVideo(usuarioId, videoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return ResponseEntity.ok(userMapper.toDTO(usr.salvar(usuario)));
    }


}
