package com.example.rea4e.rest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.example.rea4e.domain.entity.Categorias;
import com.example.rea4e.domain.entity.Video;
import com.example.rea4e.domain.service.VideoService;
import com.example.rea4e.rest.dto.VideoDTO;
import com.example.rea4e.rest.mapper.VideoMapper;


@RestController//RestController vai anotar os metodos com @ResponseBody que indica o retorno em JSON
@RequestMapping("/api/video")
public class VideoController{

    private VideoService servico;
    private VideoMapper mapper;

    public VideoController(VideoService servico, VideoMapper mapper){
        this.mapper=mapper;
        this.servico=servico;
    }

    @GetMapping
    public List<VideoDTO> listar() {

        return mapper.toDTOList(servico.listar());
    }

    @GetMapping("/autor/{autorId}")
    public List<VideoDTO> listarvideosPorAutor(@PathVariable Long autorId) {
        return mapper.toDTOList(servico.listarvideosPorAutor(autorId));
        
    }

    @GetMapping("/categoria/{categoria}")
    public List<VideoDTO> listarvideosPorCategoria(@PathVariable String categoria) {
        Categorias cat = Categorias.valueOf(categoria);
        return mapper.toDTOList(servico.listarvideosPorCategoria(cat));
    }


    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(servico.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<VideoDTO> salvar(@RequestBody VideoDTO videoDTO) {
        Video video = mapper.toVideo(videoDTO);
        Video salvo = servico.salvar(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> atualizar(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        Video video = mapper.toVideo(videoDTO);
        video.setId(id);
        Video atualizado = servico.salvar(video);
        return ResponseEntity.ok(mapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-categorias")
    public ResponseEntity<Set<String>> listarCategorias() {
        Set<String> categorias = Arrays.stream(Categorias.values())
                                       .map(Categorias::name)
                                       .collect(Collectors.toSet());

        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}