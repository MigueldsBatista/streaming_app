package com.example.rea4e.security;

import org.hibernate.UnknownEntityTypeException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public String obterUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        

        // Verifica se o principal é uma instância de UserDetails
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            return principal.getUsername(); // Retorna o nome de usuário do principal
        } 
        
        else if(authentication instanceof AnonymousAuthenticationToken){
            return null;
        }

        else {
            // Caso o principal não seja uma instância de UserDetails
            throw new UnknownEntityTypeException("Erro: O principal não é uma instância de UserDetails: "+ authentication.getClass().getSimpleName());
        }
    }
}
