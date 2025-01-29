DROP database streaming_app_db;

CREATE DATABASE streaming_app_db;

USE streaming_app_db;

CREATE TABLE `USUARIO` (
    `ID` bigint NOT NULL AUTO_INCREMENT,
    `EMAIL` varchar(255) NOT NULL UNIQUE,
    `NOME` varchar(255) NOT NULL,
    `SENHA` varchar(255) NOT NULL,
    PRIMARY KEY (`ID`)
);


CREATE TABLE `VIDEO` (
`ID` bigint NOT NULL AUTO_INCREMENT,
`CATEGORIA` enum('BACKEND','DATA_SCIENCE','DESIGN','DEVOPS','FRONTEND','FULLSTACK','IA','MACHINE_LEARNING','PROGRAMACAO','SEGURANCA','TESTES','UI','UX') NOT NULL,
`DESCRICAO` varchar(500) DEFAULT NULL,
`TITULO` varchar(255) NOT NULL,
`URL` varchar(255) NOT NULL,
`USUARIO_ID` bigint NOT NULL,
PRIMARY KEY (`ID`),
FOREIGN KEY (`USUARIO_ID`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE
);

CREATE TABLE `COMENTARIO` (
    `ID` bigint NOT NULL AUTO_INCREMENT,
    `PERGUNTA` varchar(255) DEFAULT NULL,
    `USUARIO_ID` bigint NOT NULL,
    `VIDEO_ID` bigint NOT NULL,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (`VIDEO_ID`) REFERENCES `VIDEO` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`USUARIO_ID`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE
);


CREATE TABLE `CURTIDAS` (
    `ID` bigint NOT NULL AUTO_INCREMENT,
    `USUARIO_ID` bigint NOT NULL,
    `VIDEO_ID` bigint NOT NULL,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (`VIDEO_ID`) REFERENCES `VIDEO` (`ID`) ON DELETE CASCADE,
    FOREIGN KEY (`USUARIO_ID`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE
);

INSERT INTO USUARIO (EMAIL, NOME, SENHA) VALUES
('user1@example.com', 'João Silva', 'senha123'),
('user2@example.com', 'Maria Oliveira', 'senha456'),
('user3@example.com', 'Carlos Souza', 'senha789'),
('user4@example.com', 'Fernanda Lima', 'senha101'),
('user5@example.com', 'Roberto Costa', 'senha202'),
('user6@example.com', 'Luciana Pereira', 'senha303'),
('user7@example.com', 'Ricardo Santos', 'senha404'),
('user8@example.com', 'Ana Souza', 'senha505'),
('user9@example.com', 'Paulo Oliveira', 'senha606'),
('user10@example.com', 'Juliana Costa', 'senha707');


INSERT INTO VIDEO (CATEGORIA, DESCRICAO, TITULO, URL, USUARIO_ID) VALUES
('BACKEND', 'Curso básico de Backend com Java.', 'Backend com Java', 'https://example.com/backend-java', 1),
('FRONTEND', 'Curso de desenvolvimento Frontend com React.', 'Frontend com React', 'https://example.com/frontend-react', 2),
('DESIGN', 'Fundamentos do Design Gráfico.', 'Design Gráfico Básico', 'https://example.com/design-basico', 3),
('DEVOPS', 'Introdução ao DevOps e automação.', 'DevOps na Prática', 'https://example.com/devops-pratica', 4),
('MACHINE_LEARNING', 'Aprenda Machine Learning com Python.', 'Machine Learning com Python', 'https://example.com/ml-python', 5),
('IA', 'Curso de Inteligência Artificial com TensorFlow.', 'Inteligência Artificial com TensorFlow', 'https://example.com/ia-tensorflow', 6),
('PROGRAMACAO', 'Fundamentos de programação em C.', 'Programação em C', 'https://example.com/programacao-c', 7),
('SEGURANCA', 'Introdução à segurança da informação.', 'Segurança da Informação', 'https://example.com/seguranca', 8),
('TESTES', 'Automatização de testes com Selenium.', 'Automatização de Testes', 'https://example.com/testes-selenium', 9),
('UX', 'Melhorando a experiência do usuário.', 'Experiência do Usuário', 'https://example.com/ux', 10);

INSERT INTO CURTIDAS (USUARIO_ID, VIDEO_ID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(1, 3),
(2, 5),
(3, 7),
(4, 9),
(5, 6),
(6, 2),
(7, 8),
(8, 10),
(9, 1),
(10, 4);

INSERT INTO COMENTARIO (pergunta, USUARIO_ID, VIDEO_ID) VALUES
('Como posso melhorar meu código em Java?', 1, 1),
('Quais são as melhores práticas de CSS para React?', 2, 2),
('Quais ferramentas posso usar para edição gráfica?', 3, 3),
('Qual a melhor estratégia de implantação para o DevOps?', 4, 4),
('O que são algoritmos supervisionados em Machine Learning?', 5, 5),
('Como treinar uma rede neural em TensorFlow?', 6, 6),
('Como utilizar ponteiros em C?', 7, 7),
('Quais são os principais tipos de ataques cibernéticos?', 8, 8),
('Como criar testes automatizados com Selenium?', 9, 9),
('Como realizar uma pesquisa de UX com usuários?', 10, 10);

