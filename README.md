# 🎭 Sistema de Reservas – Teatro Mack

Sistema de reservas de ingressos para um teatro, desenvolvido em **Java** com interface de **linha de comando (console)**. O projeto foi criado para a disciplina de **Estrutura de Dados** e tem como ponto central a implementação de uma **estrutura de dados própria (vetor genérico)**, utilizada para armazenar espetáculos e reservas, sem recorrer a `ArrayList` ou outras coleções prontas do Java.

---

## Sumário

- [Sobre o projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Arquitetura e classes](#-arquitetura-e-classes)
- [Estruturas de dados aplicadas](#-estruturas-de-dados-aplicadas)
- [Arquivo de dados](#-arquivo-de-dados)
- [Como executar](#-como-executar)
- [Exemplo de uso](#-exemplo-de-uso)
- [Regras de negócio e validações](#-regras-de-negócio-e-validações)
- [Autores](#-autores)

---

## Sobre o projeto

O **Teatro Mack** é um sistema de console que permite gerenciar a venda de ingressos para uma programação de espetáculos. O usuário carrega a programação a partir de um arquivo de texto, consulta os espetáculos disponíveis, escolhe assentos em um mapa visual, realiza reservas e consulta estatísticas de venda.

O foco acadêmico do projeto está na **aplicação prática de Estrutura de Dados**: em vez de usar as coleções prontas da linguagem, foi implementada a classe genérica `Vetor<T>`, um vetor dinâmico com operações de inserção, remoção, busca e acesso por posição, controlando manualmente capacidade e tamanho.

---

## Funcionalidades

O sistema é controlado por um menu interativo com as seguintes opções:

| # | Opção | Descrição |
|---|-------|-----------|
| 1 | **Carregar espetáculos** | Lê o arquivo `dadosEspetaculos.txt` e carrega a programação na memória. |
| 2 | **Exibir espetáculos** | Lista todos os espetáculos com código, nome, data, horário e preço. |
| 3 | **Fazer reserva** | Reserva de 1 a 4 ingressos para um espetáculo, com escolha de assentos. |
| 4 | **Consultar mapa de assentos** | Exibe a matriz de assentos (livres e ocupados) de um espetáculo. |
| 5 | **Consultar reserva** | Busca reservas pelo CPF do cliente. |
| 6 | **Estatísticas** | Mostra a média de ingressos por reserva em cada espetáculo e o espetáculo de maior faturamento. |
| 7 | **Sair** | Encerra o sistema. |

> ⚠️ As opções 2 a 6 só ficam disponíveis **após** carregar os espetáculos (opção 1).

---

## Tecnologias utilizadas

- **Linguagem:** Java (100% do código)
- **Paradigma:** Programação Orientada a Objetos (encapsulamento, classes, composição)
- **Recursos da linguagem:**
  - Generics (`Vetor<T>`)
  - Tratamento de exceções (`throws Exception`)
  - Leitura de arquivos com `FileReader` e `BufferedReader`
  - Entrada de dados com `Scanner`
  - Matrizes bidimensionais (`char[][]`)
  - Manipulação de `String` (`split`, `toUpperCase`, `charAt`)
- **Persistência de dados:** arquivo de texto (`.txt`) com campos separados por `;`
- **Interface:** console / terminal
- **Dependências externas:** nenhuma (apenas a biblioteca padrão do Java)

---

## Estrutura do projeto

```
ProjetoEstruturaDadosReservasTeatro/
├── Main.java               # Ponto de entrada da aplicação
├── Teatro.java             # Lógica principal: menu, reservas, consultas e estatísticas
├── Espetaculo.java         # Modelo de um espetáculo (com mapa de assentos)
├── Reserva.java            # Modelo de uma reserva de ingressos
├── Vetor.java              # Estrutura de dados: vetor genérico implementado manualmente
├── dadosEspetaculos.txt    # Base de dados com a programação do teatro
└── README.md
```

---

##  Arquitetura e classes

### `Main`
Ponto de entrada. Cria uma instância de `Teatro` e chama `exibirMenu()`.

### `Teatro`
Classe central do sistema. Mantém dois vetores:

- `Vetor<Espetaculo> espetaculos` – capacidade para **20** espetáculos
- `Vetor<Reserva> reservas` – capacidade para **800** reservas

Principais métodos:

| Método | Responsabilidade |
|--------|------------------|
| `exibirMenu()` | Loop principal do menu, com validação de entrada. |
| `carregaTXT()` | Lê o arquivo de espetáculos e popula o vetor. |
| `exibirEspetaculos()` | Imprime todos os espetáculos carregados. |
| `fazerReserva()` | Solicita o código do espetáculo e inicia o fluxo de reserva. |
| `buscarEspetaculos(int)` / `buscarPosicaoEspetaculo(int)` | Busca sequencial por código do espetáculo. |
| `solicitarReserva(Espetaculo)` | Coleta CPF, nome, quantidade e assentos; valida tudo e cria a `Reserva`. |
| `contarAssentosDisponiveis(Espetaculo)` | Conta assentos livres (`'L'`) no mapa. |
| `consultarMapaAssentos()` / `exibirAssentos(Espetaculo)` | Exibe o mapa de assentos. |
| `consultarReserva()` | Busca reservas por CPF. |
| `estatisticas()` | Chama os cálculos de média de ingressos e maior faturamento. |
| `calcularMediaIngressos(int)` | Média de ingressos por reserva de um espetáculo. |
| `calcularFaturamento(Espetaculo)` | Soma o preço de cada assento ocupado. |
| `maiorFaturamento()` | Encontra o espetáculo com maior faturamento. |

### `Espetaculo`
Representa um espetáculo da programação.

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `codigo` | `int` | Identificador único (ex.: 101) |
| `nome` | `String` | Nome do espetáculo |
| `data` | `String` | Data da apresentação |
| `horario` | `String` | Horário da apresentação |
| `preco` | `double` | Preço por ingresso |
| `assentos` | `char[5][8]` | Mapa de assentos: `'L'` = livre, `'X'` = ocupado |

Ao ser criado, todos os 40 assentos (5 fileiras × 8 colunas) começam como livres.

### `Reserva`
Representa uma reserva feita por um cliente.

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `CPF` | `String` | CPF do cliente (11 dígitos) |
| `nome` | `String` | Nome do cliente |
| `espetaculo` | `Espetaculo` | Espetáculo reservado |
| `qtdeIngressos` | `int` | Quantidade de ingressos (1 a 4) |
| `assentos` | `String[]` | Assentos escolhidos (ex.: `A1`, `B2`) |

### `Vetor<T>`
Implementação manual de um vetor genérico. Veja a próxima seção.

---

## Estruturas de dados aplicadas

### Vetor genérico (`Vetor<T>`)

Estrutura de dados implementada do zero, com um array interno `T[] A`, além de `capacity` (capacidade) e `size` (quantidade de elementos).

| Método | Descrição | Complexidade |
|--------|-----------|--------------|
| `isEmpty()` | Verifica se o vetor está vazio | O(1) |
| `size()` | Retorna a quantidade de elementos | O(1) |
| `get(i)` | Retorna o elemento da posição `i` | O(1) |
| `set(i, n)` | Substitui o elemento da posição `i` | O(1) |
| `add(i, n)` | Insere `n` na posição `i`, deslocando os elementos à direita | O(n) |
| `remove(i)` | Remove o elemento da posição `i`, deslocando os elementos à esquerda | O(n) |
| `search(n)` | Busca sequencial por um elemento (retorna a posição ou `-1`) | O(n) |
| `mostraLista()` | Imprime todos os elementos | O(n) |

O vetor lança exceções para casos como **posição inexistente**, **lista vazia** e **lista cheia**.

### Matriz de assentos (`char[][]`)

Cada espetáculo possui uma matriz **5 × 8** (fileiras **A–E** e colunas **1–8**), em que `'L'` representa assento livre e `'X'` assento ocupado. A conversão do código do assento para índices é feita por aritmética de caracteres:

```java
int linha  = assento.charAt(0) - 'A';  // 'A' → 0, 'B' → 1, ...
int coluna = assento.charAt(1) - '1';  // '1' → 0, '2' → 1, ...
```

### Busca sequencial
Usada para localizar espetáculos por código e reservas por CPF.

---

## Arquivo de dados

O arquivo `dadosEspetaculos.txt` armazena um espetáculo por linha, com os campos separados por ponto e vírgula:

```
codigo;nome;data;horario;preco
```

Conteúdo atual:

```
101;O Fantasma da Opera;10/09/2026;20:00;45.00
102;Mamma Mia;12/09/2026;21:00;55.00
103;O Rei Leao;15/09/2026;19:30;70.00
104;Noite de Comedia;18/09/2026;20:30;35.00
105;Concerto de Primavera;20/09/2026;18:00;40.00
106;Festival de Danca;23/09/2026;19:00;50.00
107;Romeu e Julieta;25/09/2026;20:00;45.00
108;Tributo ao Rock Nacional;27/09/2026;21:30;60.00
109;A Bela e a Fera;30/09/2026;19:30;65.00
110;Show Acustico;03/10/2026;20:30;50.00
```

Para adicionar novos espetáculos, basta incluir novas linhas seguindo o mesmo formato (limite de **20** espetáculos).

---

## Como executar

### Pré-requisitos

- **JDK** (Java Development Kit) instalado. Verifique com:
  ```bash
  java -version
  javac -version
  ```

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/MoabeGuedes/ProjetoEstruturaDadosReservasTeatro.git
   cd ProjetoEstruturaDadosReservasTeatro
   ```

2. **Compile os arquivos:**
   ```bash
   javac *.java
   ```

3. **Execute o programa** (na mesma pasta onde está o `dadosEspetaculos.txt`):
   ```bash
   java Main
   ```

> 💡 O arquivo `dadosEspetaculos.txt` é lido por caminho relativo, por isso o programa deve ser executado a partir da pasta raiz do projeto.

Também é possível abrir o projeto em uma IDE como **IntelliJ IDEA**, **Eclipse**, **NetBeans** ou **VS Code** e executar a classe `Main`.

---

## Exemplo de uso

**Menu principal:**

```
=== Teatro Mack ===
1. Carregar espetáculos
2. Exibir espetáculos
3. Fazer reserva
4. Consultar mapa de assentos
5. Consultar reserva
6. Estatísticas
7. Sair
========================================

Selecione uma opção:
```

**Mapa de assentos** (`X` = ocupado, `L` = livre):

```
  1 2 3 4 5 6 7 8
A X L L L L L L L
B L X L L L L L L
C L L L L L L L L
D L L L L L L L L
E L L L L L L L L
```

**Fluxo de uma reserva:**

1. Escolha a opção `1` para carregar os espetáculos.
2. Escolha a opção `3` e informe o código do espetáculo (ex.: `101`).
3. Informe o **CPF** (11 dígitos), o **nome** e a **quantidade de ingressos**.
4. Escolha os assentos, um de cada vez (ex.: `A1`, `B2`).
5. O sistema exibe o resumo da reserva com o valor total.

---

## ✅ Regras de negócio e validações

- **CPF:** deve conter exatamente **11 caracteres**.
- **Nome:** deve ter pelo menos **3 caracteres**.
- **Quantidade de ingressos:** mínimo de **1** e máximo de **4** por reserva, limitada também aos assentos disponíveis.
- **Assentos:** devem estar no formato letra + número, com letras de **A a E** e números de **1 a 8** (não diferencia maiúsculas de minúsculas).
- **Assento ocupado:** o sistema impede escolher um assento já reservado.
- **Sessão lotada:** se não houver assentos livres, a reserva é bloqueada.
- **Entradas inválidas:** o menu e os campos numéricos tratam entradas não numéricas sem encerrar o programa.
- **Ordem de uso:** as opções 2 a 6 exigem que os espetáculos tenham sido carregados antes.
- **Estatísticas:**
  - *Média de ingressos por reserva* = total de ingressos vendidos ÷ número de reservas do espetáculo.
  - *Faturamento* = número de assentos ocupados × preço do ingresso.

##  Autores

| Nome | RA |
|------|----|
| **Guilherme Gomes Pinho** | 10755529 |
| **Moabe Guedes** | 10748053 |

Projeto desenvolvido para fins acadêmicos na disciplina de **Estrutura de Dados**.
