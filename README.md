# Echo-Royal-Flush

 Aplicação web desenvolvida para transformar a experiência de feedback em equipes, utilizando gamificação, cards divertidos, retrospectivas dinâmicas e notificações recorrentes. O objetivo é tornar o processo de troca de feedbacks mais engajador, contínuo e eficiente, promovendo a cultura de crescimento e colaboração no ambiente de trabalho.
---

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Banco de Dados](#banco-de-dados)
- [Pré-requisitos](#pré-requisitos)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Ambiente de Desenvolvimento](#ambiente-de-desenvolvimento)
- [Testes](#testes)
- [Deploy](#deploy)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Autores](#autores)
- [Referências](#referências)

---

## Sobre o Projeto

Esta aplicação web tem como objetivo revolucionar a forma como o feedback é realizado dentro de equipes, promovendo uma cultura mais aberta, transparente e constante de desenvolvimento pessoal e profissional.

Problema que resolve:
Muitas empresas ainda enfrentam desafios no engajamento e na frequência dos feedbacks entre colaboradores, o que pode resultar em falhas de comunicação, baixo desempenho e desmotivação. O projeto atua justamente nesse ponto, tornando o processo mais leve, dinâmico e eficiente.

Público-alvo:

Times de empresas de qualquer porte que buscam aprimorar a cultura de feedback

Gestores e líderes preocupados com o desenvolvimento de suas equipes

Profissionais de RH interessados em ferramentas inovadoras para gestão de pessoas

Diferenciais:

Gamificação: Feedbacks são transformados em cards interativos, tornando a experiência mais atrativa e lúdica.

Retrospectivas: Funcionalidade integrada para realização de retrospectivas de equipe, facilitando análises e melhorias contínuas.

Notificações recorrentes: Lembretes automáticos garantem que o feedback aconteça de forma constante e natural.

Foco no engajamento: A plataforma foi pensada para ser intuitiva e estimular a participação ativa de todos os membros da equipe.

---

## Funcionalidades

- [x] Login
- [x] Atribuir carta
- [x] Visualizar cartas
- [x] Coleção de cartas
- [x] Autenticação via JWT
- [x] Notificação no teams
- [x] Fazer feedback para time
- [x] Fazer feedback para serviço
- [x] Deploy na EC2
---

## Tecnologias Utilizadas

- **Frontend:** React, TypeScript
- **Backend:** Java, Springboot
- **Banco de Dados:** PostgreSQL
- **Outros:** Docker

---

## Banco de Dados

![drawSQL-image-export-2025-05-31](https://github.com/user-attachments/assets/456687af-7ff4-454d-9033-2f1f93e66adb)

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- [Node.js](https://nodejs.org/) >= 18.x **e** [npm](https://www.npmjs.com/) **ou** [yarn](https://yarnpkg.com/)  
  _Necessário para rodar o frontend (React + TypeScript)_
- [Java](https://adoptium.net/) >= 17  
  _Necessário para rodar o backend (Spring Boot)_
- [Docker](https://www.docker.com/) (opcional)  
  _Caso queira executar o projeto utilizando containers_
- [PostgreSQL](https://www.postgresql.org/) >= 14.x  
  _Banco de dados utilizado pelo projeto_

---

## Como Rodar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/seuusuario/seuprojeto.git
cd seuprojeto

Claro! Segue o restante do README em bloco único para você copiar e colar:

```
git clone https://github.com/seuusuario/echo-royal-flush.git
cd echo-royal-flush
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` para o backend e, se necessário, para o frontend, com as configurações necessárias de acesso ao banco de dados, autenticação, etc.

### 3. Inicialize o banco de dados

Se estiver usando Docker, suba os containers:

```bash
docker-compose up -d
```

Ou configure manualmente o banco PostgreSQL e atualize as variáveis de ambiente.

### 4. Instale as dependências do frontend

```bash
cd frontend
npm install
# ou
yarn install
```

### 5. Instale as dependências do backend

```bash
cd ../backend
./mvnw clean install
```

### 6. Rode o frontend

```bash
cd ../frontend
npm run dev
# ou
yarn dev
```

### 7. Rode o backend

```bash
cd ../backend
./mvnw spring-boot:run
```

---

## Estrutura do Projeto

```
echo-royal-flush/
├── backend/         # Projeto Java Spring Boot
│   ├── src/
│   └── ...
├── frontend/        # Projeto React + TypeScript
│   ├── src/
│   └── ...
├── docker-compose.yml
└── README.md
```

---

## Ambiente de Desenvolvimento

* **Frontend:**

  * Node.js >= 18.x
  * React 18.x
  * TypeScript
* **Backend:**

  * Java 17
  * Spring Boot
  * Maven/Gradle
* **Banco:**

  * PostgreSQL >= 14
* **DevOps:**

  * Docker (opcional para ambiente integrado)

---

## Testes

### Frontend

```bash
cd frontend
npm test
# ou
yarn test
```

### Backend

```bash
cd backend
./mvnw test
```

---

## Deploy

A aplicação pode ser implantada via Docker ou de forma independente. Para o deploy com Docker:

```bash
docker-compose up --build
```

Certifique-se de atualizar as variáveis de ambiente nos arquivos `.env` antes do deploy.

---

## Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto.
2. Crie uma branch com sua feature ou correção (`git checkout -b minha-feature`).
3. Commit suas mudanças (`git commit -m 'feat: minha nova feature'`).
4. Faça push para a branch (`git push origin minha-feature`).
5. Abra um Pull Request.

Siga as boas práticas de commit, organização de código e, se possível, adicione testes às suas contribuições.

---

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## Autores

* [Matheus Bueno de Oliveira](https://github.com/MatheusBuenodeOliveira)
* \

---

## Referências

* Documentação oficial do [React](https://react.dev/)
* Documentação do [Spring Boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)
* Artigos e materiais sobre gamificação e feedback em equipes

---
