# api-products-with-spring-security
A rest api of products and categoria using spring security

# API de Gestão de Produtos com Spring Security & JWT

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth0-black?logo=jsonwebtokens)
![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?logo=spring&logoColor=white)
![Status](https://img.shields.io/badge/status-finalizado-brightgreen)

---

> Projeto desenvolvido para fins de **aprendizado**, com foco em autenticação stateless com JWT, autorização baseada em papéis (roles) e boas práticas de arquitetura em camadas com Spring Boot.

### 📋 Sobre o projeto

API REST para gestão de **produtos**, **categorias** e **usuários**, com autenticação via **JWT** e controle de acesso baseado em **roles** (`ADMIN`, `MANAGER`, `USER`). O projeto aplica conceitos de Spring Security como `SecurityFilterChain`, filtros customizados e autorização por endpoint/método HTTP.

🚀 Tecnologias utilizadas

- **Java**
- **Spring Boot**
- **Spring Security** — configuração de `SecurityFilterChain`, sessão stateless e filtro customizado (`OncePerRequestFilter`)
- **JWT** (biblioteca `java-jwt` da Auth0) — geração e validação de tokens
- **Spring Data JPA** — persistência das entidades `Product`, `Category` e `User`
- **Bean Validation** (Jakarta Validation) — validação dos dados de entrada (`@NotBlank`, `@NotNull`, `@Valid`)
- **BCrypt** — hash de senhas (`BCryptPasswordEncoder`)
- **Lombok** — redução de boilerplate (getters, setters, construtores)
- **Padrão DTO** (Request/Response) — desacoplamento entre entidades e camada de API

🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
Controller → Service → Repository → Entity
```

```
src/main/java/project/product/spring/security
├── config
│   ├── ConfigurationSecurity.java   # SecurityFilterChain, PasswordEncoder, AuthenticationManager
│   └── SecurityFilter.java          # Filtro JWT (OncePerRequestFilter)
├── controller
│   ├── AuthenticationController.java
│   ├── CategoryController.java
│   ├── ProductController.java
│   └── UserController.java
├── dto
│   ├── TokenResponse.java
│   ├── category (CategoryRequest / CategoryResponse)
│   ├── product  (ProductRequest / ProductResponse)
│   └── user     (UserRequest / UserResponse)
├── entities
│   ├── Category.java
│   ├── Product.java
│   ├── User.java                    # implements UserDetails
│   └── UserRole.java                # ADMIN, MANAGER, USER
├── repository
│   ├── CategoryRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
└── services
    ├── CategoryService.java
    ├── ProductServices.java
    ├── TokenService.java            # geração e validação de tokens JWT
    ├── UserServices.java            # implements UserDetailsService
    └── exeption
        ├── ResourceNotFound.java
        └── VerifyLoginException.java
```

🔐 Fluxo de autenticação

1. O usuário é criado em `POST /authentication/create/user` (senha é armazenada com hash BCrypt).
2. O login é feito em `POST /authentication/login`, retornando um **token JWT** (`TokenResponse`).
3. Nas requisições seguintes, o token deve ser enviado no header:
   ```
   Authorization: Bearer <token>
   ```
4. O `SecurityFilter` intercepta cada requisição, valida o token via `TokenService` e carrega o usuário autenticado no `SecurityContextHolder`.
5. O acesso a cada endpoint é então autorizado (ou não) conforme a role do usuário.

#### Papéis (roles) e autoridades

O usuário `MANAGER` acumula as autoridades de `ADMIN`, que por sua vez acumula as de `USER`, simulando uma hierarquia de permissões:

| Role    | Autoridades concedidas                          |
|---------|--------------------------------------------------|
| MANAGER | `ROLE_MANAGER`, `ROLE_ADMIN`, `ROLE_USER`        |
| ADMIN   | `ROLE_ADMIN`, `ROLE_USER`                        |
| USER    | `ROLE_USER`                                       |

### 📡 Endpoints

#### Autenticação (`/authentication`)

| Método | Endpoint             | Acesso   | Descrição                  |
|--------|-----------------------|----------|-----------------------------|
| POST   | `/authentication/login`       | Público | Autentica e retorna um token JWT |
| POST   | `/authentication/create/user` | Público | Cria um novo usuário              |

#### Produtos (`/product`)

| Método | Endpoint                 | Acesso              | Descrição              |
|--------|--------------------------|---------------------|--------------------------|
| POST   | `/product/save`          | MANAGER, ADMIN       | Cadastra um produto      |
| GET    | `/product/find/all`      | MANAGER              | Lista todos os produtos  |
| PUT    | `/product/{productId}`   | MANAGER              | Atualiza um produto      |
| DELETE | `/product/{productId}`   | MANAGER, ADMIN       | Remove um produto        |

#### Categorias (`/category`)

| Método | Endpoint                   | Acesso              | Descrição                |
|--------|-----------------------------|---------------------|----------------------------|
| POST   | `/category/save`            | MANAGER, ADMIN       | Cadastra uma categoria    |
| GET    | `/category/find/all`        | MANAGER              | Lista todas as categorias |
| GET    | `/category/{categoryId}`    | Autenticado           | Busca categoria por ID     |
| PUT    | `/category/{categoryId}`    | MANAGER              | Atualiza uma categoria    |

#### Usuários (`/user`)

| Método | Endpoint            | Acesso      | Descrição                |
|--------|----------------------|-------------|----------------------------|
| GET    | `/user/find/all`     | Autenticado | Lista todos os usuários    |
| PUT    | `/user/{userId}`     | Autenticado | Atualiza um usuário        |

### ⚙️ Configuração e execução

1. Configure a chave secreta usada para assinar os tokens JWT no `application.properties`:
   ```properties
   api.product.security=sua-chave-secreta
   ```
2. Configure a conexão com o banco de dados relacional (PostgreSQL, MySQL, H2, etc.):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Rode o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Teste os endpoints com Postman/Insomnia, começando por `POST /authentication/create/user` e `POST /authentication/login` para obter o token.

🎯 Objetivo

Este projeto foi criado com fins de estudo, para consolidar na prática conceitos de **Spring Security**, **autenticação JWT**, **autorização baseada em roles** e **arquitetura REST em camadas**.

