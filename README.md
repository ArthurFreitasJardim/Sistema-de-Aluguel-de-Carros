# 🚗 Sistema de Aluguel de Carros

Sistema web desenvolvido em **Java 17 + Micronaut (MVC)** para gerenciamento de pedidos de aluguel de carros.

O projeto foi construído como atividade de laboratório, evoluindo por sprints com foco em:

* arquitetura MVC
* CRUD completo de pedidos
* persistência com Hibernate + H2
* interface web com Thymeleaf
* integração com API FIPE
* boas práticas de manutenção e escalabilidade

---

# 🎯 Objetivo do Projeto

Permitir que clientes realizem pedidos de aluguel de carros de forma simples, escolhendo:

* nome do cliente
* marca do carro
* modelo do carro
* quantidade de dias

Além disso, o sistema permite:

* ✅ criar pedido
* ✏️ editar pedido
* ❌ cancelar pedido
* 📋 listar pedidos
* 🚗 buscar marcas e modelos reais via API FIPE

---

# 🛠️ Tecnologias Utilizadas

## Backend

* **Java 17**
* **Micronaut 4.6.2**
* **Hibernate / JPA**
* **Gradle Kotlin DSL**
* **Lombok**

## Frontend

* **HTML5**
* **CSS3**
* **JavaScript**
* **Thymeleaf**

## Banco de Dados

* **H2 Database**
* persistência em arquivo local

## API Externa

* **API FIPE (Parallelum)**

---

# 🧱 Arquitetura

O sistema segue o padrão **MVC (Model-View-Controller)**.

```text
Controller → recebe requisições HTTP
Service    → regras de negócio
Repository → acesso ao banco
Model      → entidades JPA
View       → páginas HTML Thymeleaf
```

## Estrutura de Pastas

```text
src/main/java/com/example
├── controller
├── service
├── repository
└── model

src/main/resources/views
├── pedido-form.html
├── pedido-editar.html
└── pedido-lista.html
```

---

# ▶️ Como Executar o Projeto

## ✅ Pré-requisitos

Instale:

* **JDK 17**
* Git

Verifique:

```bash
java -version
```

Deve aparecer Java 17.

---

## 📥 Clonar Repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd Sistema-de-Aluguel-de-Carros/demo
```

---

## ▶️ Executar

### Windows

```powershell
.\gradlew.bat run
```

### Linux / Mac

```bash
./gradlew run
```

---

## 🌐 Acessar no Navegador

```text
http://localhost:8080/pedidos/lista
```

---

# 💾 Banco de Dados

O projeto usa **H2 em arquivo local**, configurado em:

```properties
datasources.default.url=jdbc:h2:file:./data/alugueldb
```

Arquivo gerado automaticamente:

```text
data/alugueldb.mv.db
```

## 🧹 Resetar banco para testes

Apague a pasta:

```text
data/
```

Ao executar novamente, o banco será recriado automaticamente.

---

# 🚗 Integração com API FIPE

O formulário consome a API FIPE para preencher:

* marcas
* modelos

Exemplo de fluxo:

1. usuário seleciona a marca
2. sistema busca modelos da marca
3. pedido salva no formato:

```text
Toyota - Corolla
```

Isso permite edição automática de marca e modelo depois.

---

# ✨ Funcionalidades Implementadas

* ✅ CRUD de pedidos
* ✅ validação de dias (mínimo 1)
* ✅ cancelamento por POST
* ✅ integração com API FIPE
* ✅ persistência local
* ✅ interface web estilizada
* ✅ separação em camadas MVC

---

# 🔮 Melhorias Futuras

* módulo empresa
* módulo banco/aprovação
* autenticação de usuários
* PostgreSQL
* deploy em nuvem
* dashboard administrativo
* relatórios

---

# 📌 Histórias de Usuário

## Sprint 1 — Modelagem

* Como analista, quero modelar o domínio do sistema para representar pedidos de aluguel.
* Como equipe, queremos documentar casos de uso, classes e implantação.

## Sprint 2 — Backend CRUD

* Como cliente, quero cadastrar um pedido de aluguel.
* Como cliente, quero listar meus pedidos.
* Como cliente, quero editar um pedido existente.
* Como cliente, quero cancelar um pedido.

## Sprint 3 — Frontend + Integração

* Como cliente, quero uma interface web simples e bonita.
* Como cliente, quero escolher marca e modelo reais via API.
* Como cliente, quero que a edição preserve marca e modelo.

## Sprint 4 — Evolução futura

* Como empresa, quero visualizar pedidos recebidos.
* Como banco/parceiro, quero aprovar ou reprovar pagamentos.

---

# 👨‍💻 Autor

Projeto desenvolvido por **Arthur Freitas Jardim** para a disciplina de laboratório / engenharia de software.
