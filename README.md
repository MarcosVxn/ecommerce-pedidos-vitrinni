# Sistema de Gestão de Pedidos — E-commerce

> Projeto integrador da Unidade Curricular **Desenvolvimento Back-end**
> Curso Superior de Tecnologia em Análise e Desenvolvimento de Sistemas — Turma CSTADS601

## Equipe / Squad

| Nome | Papel na Aula 01 |
|---|---|
| *(Marcos Vinicius)* | |
| *(Mikael Levi)* | Responsável do dia |

DIA 01 — 07/08/26

Introdução ao GitHub e organização inicial do projeto. Criação do repositório e configuração da branch main. Configuração de regras de proteção da branch main. Estruturação inicial do projeto e realização do primeiro commit.

---------------------------------//----------------------------------

DIA 02 — 14/08/26

| Nome | Papel na Aula 02 |
|---|---|
| *(Marcos Vinicius)* | Responsável do dia |
| *(Mikael Levi)* | |

DIA 02 — 14/08/26

Introdução ao fluxo de trabalho com branches no Git. Criação e gerenciamento de branches para desenvolvimento. Aprendizado e aplicação do fluxo de Pull Requests. Criação do primeiro Pull Request e revisão das alterações junto ao professor.

---------------------------------//-----------------------------------

DIA 03 — 21/08/26

| Nome | Papel na Aula 03 |
|---|---|
| *(Marcos Vinicius)* | Responsável do dia |
| *(Mikael Levi)* | |

DIA 03 — 21/08/26

Revisão de conceitos básicos da linguagem Java, incluindo bibliotecas, métodos e o uso de `.equals()`. Introdução à criação e organização de classes no projeto. Desenvolvimento da classe Pedido em Java. Implementação dos métodos relacionados à classe Pedido.

---------------------------------//----------------------------------

DIA 04 — 28/08/26

| Nome | Papel na Aula 04 |
|---|---|
| *(Marcos Vinicius)* | |
| *(Mikael Levi)* | Responsável do dia |

Criação das classes Produto, Cliente, ItemPedido e Pedido, aplicando conceitos de POO, encapsulamento, construtores e métodos para representar o sistema de pedidos.

---------------------------------//----------------------------------

DIA 05 — 04/09/26

| Nome | Papel na Aula 05 |
|---|---|
| *(Marcos Vinicius)* | |
| *(Mikael Levi)* | Responsável do dia |

Criação das classes Cliente e Pessoa, aplicando conceitos de encapsulamento e herança.


---------------------------------//----------------------------------


DIA 06 — 11/09/26

| Nome | Papel na Aula 06 |
|---|---|
| *(Marcos Vinicius)* | |
| *(Mikael Levi)* | Responsável do dia |

Hierarquia de pagamentos abstrata (FormaPagamento), filhas concretas (CartaoCredito, Boleto, Pix), complemento de Pessoa (Funcionario), polimorfismo, diagrama draw.io e justificativas de recusa de herança.

---------------------------------//----------------------------------

DIA 07 — 18/09/26

| Nome | Papel na Aula 07 |
|---|---|
| *(Marcos Vinicius)* | |
| *(Mikael Levi)* | Responsável do dia |

Implementação dos relacionamentos entre as classes do sistema, utilizando associação, agregação e composição, além das multiplicidades e validações necessárias para garantir o funcionamento correto dos pedidos.


---------------------------------//----------------------------------



_(Uma equipe de desenvolvimento recebeu a demanda de
construir um sistema de gestão de pedidos para um e-commerce, contemplando cadastro de produtos,
clientes, pedidos e processamento de pagamentos.)_

## Funcionalidades previstas

- [ ] Cadastro e gerenciamento de produtos
- [ ] Cadastro e gerenciamento de clientes
- [ ] Criação e gerenciamento de pedidos
- [ ] Processamento de pagamentos (cartão, boleto, Pix)
- [ ] Testes automatizados (unitários e de integração)
- [ ] Pipeline de CI/CD
- [ ] API REST para consumo por um front-end

## Tecnologias

- Java
- Maven
- Git / GitHub
- _(demais tecnologias serão adicionadas ao longo do semestre: JUnit, Spring Boot, banco de
  dados, GitHub Actions...)_

## Estrutura de pastas

```
ecommerce-pedidos-NOMEDASQUAD/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/senai/ecommerce/
│   │           ├── modelo/
│   │           ├── servico/
│   │           ├── repositorio/
│   │           └── util/
│   └── test/
│       └── java/
│           └── com/senai/ecommerce/
├── pom.xml
├── README.md
└── .gitignore
```

## Como rodar o projeto

_(Preencher a partir das próximas aulas, conforme o projeto evoluir.)_

## Roadmap do projeto (por aula)

| Aula | Entrega |
|---|---|
| 01 | Repositório criado, estruturado, com README e commit inicial | **OK**
| 02 | Fluxo de branches e primeiro Pull Request revisado |**OK**
| 03 | Classe utilitária (Utils) do domínio |
| 04 | Classes de domínio inicial (Produto, Cliente, Pedido, ItemPedido) |
| 05 | Encapsulamento e abstração aplicados |
| 06 | Hierarquia de formas de pagamento (herança) |
| 07 | Relacionamentos entre classes do domínio |
| 08 | Módulo de pagamento polimórfico |
| 09 | Tratamento de exceções |
| 10 | Suíte de testes unitários |
| 11 | Suíte de testes de integração + relatório de cobertura |
| 12 | Persistência: conexão, Create e Read |
| 13 | Persistência: Update, Delete e padrão DAO/Repository |
| 14 | Migração para Spring Boot |
| 15 | API REST + pipeline CI/CD |
| 16 | Entrega final, documentação e apresentação |

## Combinado da equipe (ética e convivência)

-Manter uma comunicação clara e respeitosa entre os integrantes da equipe.
-Criar branches e Pull Requests para organizar e revisar as alterações antes de integrá-las à main.
-Cumprir as tarefas definidas para cada aula e comunicar a equipe sobre eventuais dificuldades ou atrasos.

## Licença

Projeto acadêmico — Faculdade de Tecnologia SENAI "Antonio Adolpho Lobbe".
