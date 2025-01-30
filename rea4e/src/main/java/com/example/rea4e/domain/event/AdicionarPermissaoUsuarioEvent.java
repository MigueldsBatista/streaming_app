package com.example.rea4e.domain.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class AdicionarPermissaoUsuarioEvent extends ApplicationEvent{


private final Long usuarioId;
private final String permissao;

public AdicionarPermissaoUsuarioEvent(Object source, Long usuarioId, String permissao){
    super(source);
    this.permissao=permissao;
    this.usuarioId=usuarioId;
}
    
}
