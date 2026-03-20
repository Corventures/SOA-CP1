# FIAP Classes - SOAP Web Service

Este é um projeto universitário (FIAP) de um **Web Service SOAP** desenvolvido em Java. O sistema gerencia um cadastro acadêmico de Aulas, permitindo operações completas de CRUD (Criar, Ler, Atualizar, Excluir) com persistência em banco de dados **Oracle** e utilizando o padrão JAX-WS.

## 🚀 Tecnologias e API

- **Linguagem:** Java (JDK 21 recomendado)
- **Tecnologia/Protocolo:** JAX-WS (Web Services SOAP)
- **Banco de Dados:** Oracle Database
- **Driver JDBC:** `ojdbc11`
- **WSDL:** `http://localhost:8080/aula?wsdl`

## ⚙️ Operações SOAP (`IAulaService`)

- **`listarTodas()`**: Retorna todas as aulas registradas no banco.
- **`buscarPorId(id)`**: Busca uma aula pelo ID.
- **`listarPorDia(dia)`**: Filtra as aulas conforme o dia da semana.
- **`listarPorDisciplina(nome)`**: Busca aulas por nome (suporta termos parciais).
- **`cadastrar(aula)`**: Registra uma nova aula com ID incremental (gerido via `Sequence` do Oracle).
- **`atualizar(aula)`**: Atualiza integralmente os dados de uma aula existente.
- **`excluir(id)`**: Remove uma aula do banco pelo ID.

## 🏗️ Estrutura e Camadas

- **`application.Publicador`**: Provedor do serviço; publica o endpoint SOAP em `http://localhost:8080/aula`.
- **`Main`**: Cliente consumidor; simula o fluxo CRUD e interage com o WSDL do serviço.
- **`service`**: Camada de serviço e interface `@WebService`; atua como fachada, recebendo as requisições SOAP e delegando-as para o DAO.
- **`dao.AulaDAO`**: Camada de persistência; responsável por executar as operações SQL (JDBC) diretamente no Oracle.
- **`factory.ConnectionFactory`**: Fábrica de conexões responsável por abrir e gerenciar a comunicação com o banco de dados.
- **`dto.Aula`**: Objeto de transferência (DTO); define os campos da entidade (Disciplina, Professor, Sala, etc).
- **`enums`**: Tipagens constantes; define modalidades (Presencial/Remoto), dias da semana e status da aula.

## ▶️ Como Executar

### 1. Configurando o Banco de Dados (Oracle)

Antes de rodar o projeto, é necessário preparar o ambiente de dados. Conecte-se ao seu banco Oracle e rode o script [`create_table.sql`](./database/create_table.sql) para criar a tabela e a sequence, além de inserir os dados iniciais (Grade da 3ESPX)

_(Importante: Não se esqueça de criar um arquivo `.env` na raiz do projeto com as credenciais do banco de dados)._

Exemplo de arquivo `.env`:

```env
db.user=rm123456
db.pass=123456
```

---

### 2. Iniciando o Servidor (Provider)

Com o banco configurado, execute a classe principal `br.com.fiap.application.Publicador`. Ela levantará o servidor SOAP localmente. Você verá a seguinte confirmação no console:

```text
Web Service publicado!
```

---

### 3. Documentação da API (Bruno Docs) 📖

Para facilitar o entendimento dos contratos XML e visualizar os exemplos de cada operação, acesse a documentação interativa gerada pelo **Bruno** que está hospedada em:

👉 **[corventures.github.io/SOA-CP1/](https://corventures.github.io/SOA-CP1/)**

Nesta página, você encontrará os detalhes de entrada e saída para cada endpoint (`listarTodas`, `cadastrar`, `atualizar`, etc.), servindo como um guia de referência rápido para os testes.

---

### 4. Executando o Cliente Java (Consumer)

Com o servidor rodando e conectado ao banco, execute a classe `br.com.fiap.Main` para validar o fluxo CRUD completo via código. O console exibirá os logs das operações interagindo agora com o Oracle:

```text
Conexão estabelecida com sucesso!

========================================
           Testando Cadastro
========================================
Aulas cadastradas com sucesso no banco! IDs gerados: 1 e 2
...
```

---
