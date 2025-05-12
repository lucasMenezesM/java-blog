# **Nexus Blog**

Um projeto de blog simples desenvolvido com **Java** e **Spring Boot**, utilizando **HTML**, **CSS**, **Tailwind** para o front-end e **MySQL** como banco de dados. Este projeto permite criar, visualizar, editar e excluir postagens de blog.

---

## **Índice**

1. [Sobre o Projeto](#sobre-o-projeto)
2. [Funcionalidades](#funcionalidades)
3. [Tecnologias Usadas](#tecnologias-usadas)
4. [Pré-requisitos](#pré-requisitos)
5. [Como Instalar e Executar](#como-instalar-e-executar)
6. [Como Usar](#como-usar)
7. [Contribuindo](#contribuindo)
8. [Contato](#contato)

---

## **Sobre o Projeto**

O Nexus Blog é um sistema básico para gerenciamento de postagens. Ele foi criado para explorar o desenvolvimento full stack utilizando **Spring Boot** no back-end e um front-end moderno com **HTML**, **CSS** e **Tailwind CSS**.

---

## **Funcionalidades**

- Criar novas postagens de blog.
- Listar todas as postagens.
- Visualizar uma postagem específica.
- Editar e excluir postagens existentes.

---

## **Tecnologias Usadas**

- **Java** (Spring Boot)
- **HTML**, **CSS** e **Tailwind**
- **MySQL**
- **Thymeleaf** (Motor de templates para o front-end)

---

## **Pré-requisitos**

Antes de começar, certifique-se de ter os seguintes itens instalados:

- **Java JDK 17+**
- **Maven**
- **MySQL Server**
- Um editor de texto ou IDE, como **IntelliJ IDEA** ou **Eclipse**

---

## **Como Instalar e Executar**

### **1. Clone o repositório**

```bash
git clone https://github.com/seu-usuario/blog-simples.git
```

### **2. Configure o banco de dados**

1. Crie um banco de dados no MySQL chamado `blog`.
2. Configure as credenciais no arquivo `application.properties` ou `application.yml`:

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### **3. Instale as dependências**

Certifique-se de estar no diretório raiz do projeto e execute:

```bash
mvn clean install
```

### **4. Execute o projeto**

Use o Maven para iniciar o servidor Spring Boot:

```bash
mvn spring-boot:run
```

### **5. Acesse o sistema**

Abra o navegador e acesse:

```bash
http://localhost:8080
```

## **Como Usar**

### **Criação de Postagens**

1. Clique no botão **"Nova Postagem"**.
2. Preencha os campos com o título e o conteúdo da postagem.
3. Clique em **"Salvar"** para adicionar a postagem ao blog.

### **Visualização de Postagens**

- A página inicial lista todas as postagens criadas.
- Clique no título de uma postagem para visualizá-la em detalhes.

### **Edição e Exclusão**

- Na lista de postagens, clique em **"Editar"** para modificar uma postagem existente.
- Clique em **"Excluir"** para remover uma postagem.

## **Contribuindo**

Se quiser contribuir, siga os passos abaixo:

1. Faça um fork do projeto.
2. Crie uma branch com sua funcionalidade:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das mudanças:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Envie as alterações:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

## **Contato**

- **GitHub**: [LucasMenezesM](https://github.com/lucasMenezesM)
- **Email**: livedolux@gmail.com
- **LinkedIn**: [linkedin.com/in/lucas-menezes-023600279](https://www.linkedin.com/in/lucas-menezes-023600279/)
