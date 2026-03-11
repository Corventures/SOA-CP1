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

Dentro de sua IDE, execute a classe principal `br.com.fiap.application.Publicador`.
Ela fará a subida do servidor SOAP. Você verá a mensagem de sucesso no seu console:

```text
Web Service publicado!
```

_Opcional: Você pode acessar e validar a geração do WSDL abrindo no navegador: `http://localhost:8080/aula?wsdl`_

> **Nota:** Descomente o // popularAulasIniciais(); dentro do construtor padrão de `AulaService` para já iniciar o servidor com as aulas do 3ESPX cadastradas, facilitando os testes de leitura e atualização.

### 2. Executando o Cliente (Consumer)

Com o servidor rodando em background, execute a classe principal `br.com.fiap.Main`.
A classe criará uma série de mockups, cadastrará aulas novas, fará uma busca listando todas, outra filtrando pelo Dia da Semana, alterará informações de uma das aulas postadas, e deletará um modelo teste, imprimindo no console:

```text
Conexão estabelecida com sucesso!

========================================
           Testando Cadastro
========================================
Aulas cadastradas com sucesso! IDs gerados: 1 e 2

(...)

Total de aulas: 2
Aula [ID: 1] - Arquitetura Orientada a Serviços (SOA)
  ├─ Prof/Local: Salatiel Luz Marinho | Sala: 105
...
```
