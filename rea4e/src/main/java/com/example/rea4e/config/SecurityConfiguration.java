package com.example.rea4e.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.rea4e.domain.service.UsuarioService;
import com.example.rea4e.security.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

//para interagir com mysql eu uso

/*

@Configuration é uma anotação de configuração que indica que a classe é uma classe de configuração do Spring, ou seja, ela contém métodos que configuram o contexto da aplicação.

 @EnableWebSecurity é uma anotação de configuração que importa automaticamente a configuração de segurança padrão do Spring que contem as seguintes funcionalidades
    - Um filtro de segurança que protege a aplicação de ataques
    - Um filtro que gerencia a autenticação dos usuários
    - Um filtro que gerencia a autorização dos usuários
    - Um filtro que gerencia a sessão dos usuários
    
 
 SecurityFilterChain é um filtro de segurança que é responsável por proteger a aplicação de ataques, ele intercepta as requisições e verifica se o usuário tem permissão para acessar a aplicação.
 
 */


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

        

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF para testes com Postman/cURL
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/usuario/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/usuario/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()) // Habilita autenticação básica
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //Com o bcrypt o hash é gerado de forma aleatória, nao é possivel fazer um
        //traceback, pra fazer o match o encoder verifica se é possivel gerar o mesmo hash a partir daquela senha
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        String encodedPassword = encoder.encode("123");
        System.out.println("Senha codificada: " + encodedPassword);
    
        UserDetails user = User.builder()
            .username("user")
            .password(encodedPassword)
            .roles("USER")
            .build();
    
        UserDetails admin = User.builder()
            .username("admin")
            .password(encodedPassword)
            .roles("ADMIN")
            .build();
    
        return new InMemoryUserDetailsManager(user, admin);
    }
    

    @Bean WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> {
            web.ignoring().requestMatchers(
                "/v2/api-docs/**",
                            "/v3/api-docs/**",
                            "/swagger-resources/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/webjars/**"

            );
        };
    }


    // @Bean
    // public UserDetailsService userDetailsService(UsuarioService usr){
    //     return new CustomUserDetailService(usr);
    // }


}
