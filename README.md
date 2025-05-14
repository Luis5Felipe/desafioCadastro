# Sistema de Cadastro de Pets 🐾

![Java Version](https://img.shields.io/badge/Java-8%2B-blue)

Um sistema completo para gerenciamento de cadastros de pets para adoção, desenvolvido como parte do desafio proposto por [Lucas Carrilho De Almeida](https://www.linkedin.com/in/karilho/). O sistema oferece funcionalidades para cadastrar, alterar, deletar e listar pets disponíveis para adoção através de uma interface de linha de comando.

## Readme do Desafio

**[Desafio Do Cadastro](desafio.md)**

## 📋 Funcionalidades

- **Cadastro de Pets**: Adicione novos pets com informações detalhadas
- **Edição de Cadastros**: Altere informações de pets já cadastrados
- **Remoção de Cadastros**: Exclua registros do sistema
- **Listagem Completa**: Visualize todos os pets registrados
- **Busca por Critérios**: Encontre pets específicos com base em diferentes critérios

## 🚀 Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto
- **Orientação a Objetos**: Classes, herança, encapsulamento
- **Java I/O**: Manipulação de arquivos para persistência de dados
- **Enumerações**: Para categorização e classificação dos pets
- **Tratamento de Exceções**: Para lidar com erros e eventos inesperados

## 📦 Estrutura do Projeto

```
entidades/
    └── Pet.java
services/
    ├── ArmazenarDadosEexbirDados.java
    ├── ArquivosPerguntas.java
    ├── BuscarPet.java
    ├── CadastrarPet.java
    ├── CadastroService.java
    ├── DeletarArquivos.java
    ├── ImprimirArquivos.java
    └── MenuDeBusca.java
utils/
    ├── exception/
        ├── IdadeException.java
        └── PesoException.java
    ├── SexoPet.java
    └── TipoAnimal.java
.gitignore
Main.java
README.md
```

## 🛠️ Como Executar

### Pré-requisitos

- Java 8 ou superior
- IDE Java (Eclipse, IntelliJ ou similar) ou linha de comando

### Passos para Execução

1. Configure os caminhos dos arquivos

- ImprimirArquivos
- BuscarPet
- ArmazenarCadastro

Substitua os caminhos fixos pelos caminhos corretos do seu sistema ou utilize caminhos relativos para facilitar a portabilidade.

1. Clone este repositório:
   ```bash
   git clone git@github.com:Luis5Felipe/desafioCadastro.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd desafioCadastro
   ```

3. Compile o projeto:
   ```bash
    javac entidades/*.java services/*.java utils/*.java Main.java
   ```

4. Execute o programa:
   ```bash
    java Main
   ```

## 📝 Exemplo de Uso

Ao iniciar o sistema, você verá um menu de opções como o seguinte:

```
==== SISTEMA DE CADASTRO DE PETS ====
1. Cadastrar novo Pet
2. Alterar cadastro
3. Excluir cadastro
4. Listar todos os Pets
5. Buscar Pet por critério
0. Sair
Escolha uma opção:
```

Para cadastrar um novo pet, selecione a opção 1 e siga as instruções para inserir as informações necessárias como nome, espécie, idade, etc.

## 📊 Diagrama de Classes

```
┌────────────┐          ┌───────────────┐
│    Pet     │          │ PetService    │
├────────────┤          ├───────────────┤
│ - id       │          │ + cadastrar() │
│ - nome     │<─────────│ + alterar()   │
│ - especie  │          │ + excluir()   │
│ - idade    │          │ + listar()    │
│ - porte    │          │ + buscar()    │
└────────────┘          └───────────────┘
```