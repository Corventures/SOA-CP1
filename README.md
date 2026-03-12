# FIAP Classes - SOAP Web Service

Este é um projeto universitário (FIAP) de um **Web Service SOAP** desenvolvido em Java. O sistema gerencia um cadastro acadêmico de Aulas, permitindo operações completas de CRUD (Criar, Ler, Atualizar, Excluir) em memória e utilizando o padrão JAX-WS.

## 🚀 Tecnologias e API

- **Linguagem:** Java (JDK 8 ou superior)
- **Tecnologia/Protocolo:** JAX-WS (Web Services SOAP)
- **WSDL:** `http://localhost:8080/aula?wsdl`

## ⚙️ Funcionalidades (Operações SOAP)

A interface `IAulaService` (_Endpoint Interface_) expõe as seguintes operações web (`@WebMethod`):

- `listarTodas()`: Retorna a lista contendo todas as aulas cadastradas no sistema.
- `buscarPorId(Long id)`: Busca os detalhes de uma aula passando o seu ID único.
- `listarPorDia(DiaDaSemana dia)`: Filtra iterativamente as aulas pelo dia da semana (ex: `SEGUNDA`, `QUARTA`).
- `listarPorDisciplina(String disciplina)`: Efetua uma busca parcial de aulas pelo nome da disciplina.
- `cadastrar(Aula aula)`: Registra uma nova aula, gerando um ID incremental automático.
- `atualizar(Aula aula)`: Sobrescreve e atualiza os dados completos de uma aula já existente.
- `excluir(Long id)`: Remove do sistema uma aula a partir do seu ID.

## 🏗️ Estrutura e Camadas

- **`br.com.fiap.application.Publicador`**: Classe executável (Provider) responsável por levantar o servidor web embutido usando `Endpoint.publish` no endereço `http://localhost:8080/aula`.
- **`br.com.fiap.Main`**: Classe responsável por simular o Cliente/Consumidor do serviço SOAP usando `Service.create(url, qname)`. Conecta-se ao WSDL, executa o fluxo completo (CRUD) no console com saída bem formatada visualmente.
- **`br.com.fiap.service.*`**: Interfaces e serviço centralizado expondo seus métodos (`@WebService`). O banco de dados no momento é figurado em memória por um `List<Aula>` e ID Generator (`AtomicInteger`).
- **`br.com.fiap.dto.Aula`**: Representação/Entidade transferida dos dados entre cliente e servidor com campos como Disciplina, Professor, Sala, Dia, e Horários.
- **`br.com.fiap.enums.*`**: Enumeradores para tipagem da aula (`PRESENCIAL`, `REMOTO`), os dias da semana e os status das aulas (`CONFIRMADA`, `CANCELADA`, etc).

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
