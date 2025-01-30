## Autenticação x Autorização

- **Autenticação**: É o processo de verificar se o usuário é quem ele diz ser. Geralmente é feito através de um login e senha, mas pode ser feito de outras formas, como por exemplo, através de um token, dois fatores como sms e etc.

- **Autorização**: É o processo de verificar se o usuário tem permissão para acessar determinado recurso. Por exemplo, um usuário pode estar autenticado, mas não ter permissão para acessar a página de administração.

## Principais tipos de autenticação

Como o spring resolve nosso problema, usando o spring security, ele nos fornece uma série de opções para autenticação, como:

- **Basic Authentication**: É o método mais simples de autenticação, ele envia o usuário e senha em texto puro, por isso não é recomendado para ambientes de produção.

- **Form Based Authentication**: É o método mais comum, onde o usuário informa o login e senha em um formulário.

- **OAuth2**: É um protocolo de autorização, que permite que um aplicativo acesse recursos em nome do usuário.

- **JWT**: É um token que é enviado no header da requisição, ele é assinado e contém informações do usuário.

Bearer token: temos dois tipos principais, o jwt e o token que possui informações relevantes pro usuário 



LOGICA DE AUTENTICACAO


Authentication é um objeto que carrega as informações importantes de quem é o usuario
carregado, independente do método, no final é criado um objeto desse

UserDetails
Carrega o login, senha e roles/authorities

UserDetailsService interface que provem um metodo loadUserByUsername() e
retorna uma instancia userDetails

podemos injetar um UsuarioRepository pra ir no banco de dados e buscar nossas informções

UserDetails <- UserDetailsService <- UserRepository <-> Database
Tudo isso acontece no filter chain

//TODO

finalizar as implementações dos controllers, e avançar nos logins

principalmente também aprimorar o tratamento de erro da aplicação, nao pode ocorrer null pointer exception

