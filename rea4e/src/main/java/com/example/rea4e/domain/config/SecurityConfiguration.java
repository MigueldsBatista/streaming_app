package com.example.rea4e.domain.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
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
public class SecurityConfiguration {

    @Bean//Estamos sobrescrevendo o padrao do Spring Security
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .csrf(AbstractHttpConfigurer::disable)//Vamos desabilitar por causa do front-end
        //Cross-Site Request Forgery é um tipo de ataque que ocorre quando um usuário mal-intencionado envia uma solicitação para um site que o usuário está autenticado.
        .formLogin(configurer ->{
            configurer.loginPage("/login");//Vamos permitir que a página de login seja acessada por todos
        })
        .httpBasic(Customizer.withDefaults())//no padrao ele vai fazer a autenticação via Basic Auth
        .authorizeHttpRequests(authorizer -> {
            authorizer.requestMatchers("/login").permitAll();
            authorizer.requestMatchers(HttpMethod.DELETE,"/api/**").hasAnyRole("ADMIN");
            authorizer.requestMatchers("/api/**").hasAnyRole("USER", "ADMIN");
            authorizer.anyRequest().authenticated();

            //qualquer coisa depois do anyRequest vai ser ignorado

        })//Vamos autorizar todas as requisições
        .build();

        //Desse modo é igual o padrao da biblioteca 
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //Com o bcrypt o hash é gerado de forma aleatória, nao é possivel fazer um
        //traceback, pra fazer o match o encoder verifica se é possivel gerar o mesmo hash a partir daquela senha
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user = User.builder().
        username("user")
        .password(encoder.encode("123"))
        .roles("USER")
        .build();

        UserDetails admin = User.builder().
        username("admin")
        .password(encoder.encode("123"))
        .roles("ADMIN")
        .build();
        return new InMemoryUserDetailsManager(user, admin);//caso fosse de um banco de dados
        //usariamos return new JdbcUserDetailsManager(dataSource);
    }
}
