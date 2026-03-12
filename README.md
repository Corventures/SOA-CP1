# FIAP Classes - SOAP Web Service

Este é um projeto universitário (FIAP) de um **Web Service SOAP** desenvolvido em Java. O sistema gerencia um cadastro acadêmico de Aulas, permitindo operações completas de CRUD (Criar, Ler, Atualizar, Excluir) em memória e utilizando o padrão JAX-WS.

## 🚀 Tecnologias e API

- **Linguagem:** Java (JDK 8 ou superior)
- **Tecnologia/Protocolo:** JAX-WS (Web Services SOAP)
- **WSDL:** `http://localhost:8080/aula?wsdl`


## ⚙️ Operações SOAP (`IAulaService`)

* **`listarTodas()`**: Retorna todas as aulas registradas.
* **`buscarPorId(id)`**: Busca uma aula pelo ID
* **`listarPorDia(dia)`**: Filtra as aulas conforme o dia da semana.
* **`listarPorDisciplina(nome)`**: Busca aulas por nome (suporta termos parciais).
* **`cadastrar(aula)`**: Registra uma nova aula com ID incremental.
* **`atualizar(aula)`**: Atualiza integralmente os dados de uma aula existente.
* **`excluir(id)`**: Remove uma aula pelo ID.

## 🏗️ Estrutura e Camadas

* **`application.Publicador`**: Provedor do serviço; publica o endpoint SOAP em `http://localhost:8080/aula`.
* **`Main`**: Cliente consumidor; simula o fluxo CRUD e interage com o WSDL do serviço.
* **`service`**: Camada de negócio e interface `@WebService`; gerencia dados em memória (`List` e `AtomicInteger`).
* **`dto.Aula`**: Objeto de transferência (DTO); define os campos da entidade (Disciplina, Professor, Sala, etc).
* **`enums`**: Tipagens constantes; define modalidades (Presencial/Remoto), dias da semana e status da aula.

## ▶️ Como Executar

### 1. Iniciando o Servidor (Provider)

Execute a classe principal `br.com.fiap.application.Publicador`. Ela levantará o servidor SOAP localmente. Você verá a seguinte confirmação no console:

```text
Web Service publicado!

```

> **Dica:** Para testar com dados pré-existentes, descomente a linha `// popularAulasIniciais();` no construtor da classe `AulaService`.

---

### 2. Documentação da API (Bruno Docs) 📖

Para facilitar o entendimento dos contratos XML e visualizar os exemplos de cada operação, acesse a documentação interativa gerada pelo **Bruno** que está hospedada em:

👉 **[corventures.github.io/SOA-CP1/](https://corventures.github.io/SOA-CP1/)**

Nesta página, você encontrará os detalhes de entrada e saída para cada endpoint (`listarTodas`, `cadastrar`, `atualizar`, etc.), servindo como um guia de referência rápido para os testes.

---

### 3. Executando o Cliente Java (Consumer)

Com o servidor rodando, execute a classe `br.com.fiap.Main` para validar o fluxo CRUD completo via código. O console exibirá os logs das operações:

```text
Conexão estabelecida com sucesso!

========================================
           Testando Cadastro
========================================
Aulas cadastradas com sucesso! IDs gerados: 1 e 2
...

```
