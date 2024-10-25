# MyCodeBlog

## 📖 Sobre o Projeto

O **MyCodeBlog** é uma plataforma de blog voltada para desenvolvedores compartilharem conhecimento técnico e experiências. O projeto foi desenvolvido usando **Spring Boot** com a ideia de oferecer uma interface para criar, editar e ler posts de forma eficiente e organizada.

---

## 🚀 Funcionalidades

- **CRUD de Postagens**: Permite criar, ler, atualizar e deletar posts.
- **Autenticação de Usuários**: Mecanismo de login para proteger o conteúdo de posts.
- **Tags e Categorias**: Classificação de posts para facilitar a navegação e pesquisa.
- **Interface Simples e Responsiva**: Layout amigável e intuitivo para uma boa experiência do usuário.

---

## 🛠 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **Thymeleaf** para renderização do front-end
- **PostgreSQL** para armazenamento de dados
- **Spring Security** para autenticação e autorização
- **Lombok** para reduzir o código boilerplate

---

## 📂 Estrutura do Projeto

Abaixo está uma estrutura básica das pastas e arquivos do projeto:

```plaintext
src
|-- main
|   |-- java
|   |   |-- com
|   |   |   |-- victor
|   |   |   |   |-- mycodeblog
|   |   |   |       |-- controller       # Controladores das rotas da aplicação
|   |   |   |       |-- model            # Modelos e entidades
|   |   |   |       |-- repository       # Interfaces de repositório (CRUD)
|   |   |   |       |-- service          # Regras de negócio e lógica de aplicação
|   |-- resources
|       |-- templates                   # Templates Thymeleaf para páginas HTML
|       |-- application.properties      # Configurações da aplicação

## Clone o repositório:

git clone https://github.com/victor-engineer/MyCodeBlog.git

Configure o banco de dados no arquivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/mycodeblog
spring.datasource.username=postgres
spring.datasource.password=postgres

## compile e run no projeto:
mvn clean install
mvn spring-boot:run

acesse a aplicação no navegador
http://localhost:8081

🤝 Contribuindo

Se você deseja contribuir para o projeto:

    Fork o repositório.
    Crie uma branch com a nova funcionalidade ou correção de bug:

    git checkout -b minha-nova-funcionalidade

Commit as mudanças:

git commit -m "Descrição das alterações"


Push a branch:

git push origin minha-nova-funcionalidade

📫 Contato

Criado por Victor - Entre em contato se tiver dúvidas!
