# 🛒 CRUD de Produtos – Supermercado (Arquitetura em Camadas)

## 📌 Descrição do Projeto

Este projeto consiste em um **sistema CRUD de produtos para um supermercado**, desenvolvido em **Java**, executado via **console**.
O sistema permite realizar as seguintes operações:

* Cadastrar produtos
* Listar produtos
* Buscar produtos
* Atualizar produtos
* Remover produtos
* Limpar todos os registros

O **principal foco do projeto** foi a implementação da **arquitetura em camadas** e o **tratamento de exceções personalizadas**, aplicando boas práticas de organização, separação de responsabilidades e manutenção do código.

O projeto utiliza **Maven** para gerenciamento de dependências e organização do build.

---

## 🧱 Arquitetura em Camadas

O projeto segue o padrão de **separação de responsabilidades**, organizado da seguinte forma:

```
src/main/java/br/ikarodev
│
├── config
│   └── Conexao.java
│
├── dao
│   └── ProdutoDAO.java
│
├── exception
│   ├── EstaVazioException.java
│   ├── IdInvalidoException.java
│   ├── NegociosException.java
│   ├── NomeInvalidoException.java
│   ├── PersistenciaException.java
│   ├── PrecoInvalidoException.java
│   ├── ProdutoJaExisteException.java
│   └── ProdutoNaoEncontradoException.java
│
├── menu
│   └── Menu.java
│
├── model
│   └── Produto.java
│
├── service
│   └── ProdutoService.java
│
├── util
│   └── Utilitarios.java
│
└── Main.java
```

### 🔹 Camadas

* **Model**
  Representa a entidade do sistema (`Produto`).

* **DAO (Data Access Object)**
  Responsável pelo acesso e manipulação dos dados.

* **Service**
  Contém as regras de negócio, validações e centraliza o tratamento de exceções.

* **Exception**
  Exceções personalizadas para controle de erros e mensagens claras ao usuário.

* **Menu**
  Responsável pela interação com o usuário via console.

* **Config**
  Gerencia a conexão com o banco de dados.

---

## ⚙️ Tecnologias Utilizadas

* Java (JDK 8 ou superior)
* Maven
* JDBC
* SQLite
* IDE: Visual Studio Code / IntelliJ IDEA

---

## 💻 Como Executar o Projeto

### 🔧 Pré-requisitos

* Java JDK 8 ou superior

  ```bash
  java -version
  ```
* Maven instalado

  ```bash
  mvn -version
  ```

---

### ▶️ Executando pela IDE (recomendado)

1. Abra o projeto no **Visual Studio Code** ou **IntelliJ IDEA**
2. Aguarde o Maven baixar as dependências automaticamente
3. Localize a classe `Main.java`
4. Execute a aplicação utilizando a opção **Run ▶️**

---

### ▶️ Executando pelo terminal com Maven

No diretório raiz do projeto (onde está o `pom.xml`), execute:

```bash
mvn compile
mvn exec:java
```

O menu do sistema será exibido no console, permitindo a interação com o CRUD de produtos.

---

## 🚨 Tratamento de Exceções

O sistema utiliza **exceções personalizadas** para tratar situações como:

* Produto não encontrado
* Produto já existente
* Nome inválido
* Preço inválido
* Lista vazia
* Erros de persistência

Isso garante maior controle do fluxo da aplicação e melhor experiência para o usuário.

---

## 🎯 Objetivos de Aprendizado

* Aplicar arquitetura em camadas
* Utilizar Maven para organização do projeto
* Implementar regras de negócio na camada Service
* Criar e tratar exceções personalizadas
* Desenvolver um CRUD completo em Java

---

## 👨‍💻 Desenvolvedor

**Ikaro Ferreira Brito**
Estudante de Tecnologia da Informação

Projeto desenvolvido com fins educacionais, com foco no aprendizado de Java, Maven, arquitetura em camadas e tratamento de exceções.

📌 GitHub: [https://github.com/ikaro9](https://github.com/ikaro9)
📌 Linkedln: [https://www.linkedin.com/in/ikaro-ferreira-ti/](https://www.linkedin.com/in/ikaro-ferreira-ti/)

---

⭐ Fique à vontade para explorar, estudar ou contribuir com o projeto!
