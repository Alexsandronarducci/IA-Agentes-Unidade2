# 🤖 Projeto de Agentes Inteligentes em Grid

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk">
  <img alt="Status" src="https://img.shields.io/badge/status-conclu%C3%ADdo-success?style=for-the-badge">
  <img alt="Paradigma" src="https://img.shields.io/badge/abordagem-simb%C3%B3lica-blue?style=for-the-badge">
  <img alt="Disciplina" src="https://img.shields.io/badge/Intelig%C3%AAncia-Artificial-purple?style=for-the-badge">
</p>

---

## 📚 Sobre o projeto

Este projeto foi desenvolvido para representar, de forma prática e visual, diferentes **tipologias de agentes inteligentes** em um ambiente do tipo **grid n × n**.

A implementação foi feita em **Java**, utilizando uma **abordagem simbólica**, com foco em:

- representação explícita do ambiente;
- tomada de decisão por regras;
- exploração e busca em grid;
- visualização da movimentação do agente em uma janela gráfica.

Diferente de uma versão apenas textual no console, esta versão do projeto permite acompanhar o **robô se movendo passo a passo** no ambiente, o que facilita a análise do comportamento de cada agente.

---

## 🎯 Objetivo

O principal objetivo do projeto é demonstrar como diferentes arquiteturas de agentes alteram o comportamento de um robô em um ambiente discreto.

A proposta busca mostrar a diferença entre:

- um agente que reage apenas ao estado atual;
- um agente que mantém informações sobre o ambiente;
- um agente orientado a objetivos;
- um agente que escolhe ações com base em utilidade e custo.

Além disso, o projeto também busca tornar essa evolução **visualmente compreensível**, exibindo a movimentação do robô diretamente na tela.

---

## 🧠 Etapas implementadas

### 1️⃣ Etapa 1 — Agente Reativo Simples
O robô nasce em uma posição aleatória **interna** do grid, ou seja, sem iniciar nas bordas.

A partir dessa posição, ele segue uma sequência fixa de objetivos:

- Norte
- Leste
- Sul
- Oeste

Nessa etapa, o agente:
- não usa memória do ambiente;
- não possui planejamento complexo;
- não repete células já percorridas no trajeto principal;
- encerra a execução após completar essa sequência.

---

### 2️⃣ Etapa 2 — Agente Reativo Baseado em Modelo
Nesta etapa, o agente passa a explorar o grid fisicamente, célula por célula, sem teleportes.

Ele:
- reconhece obstáculos;
- registra células visitadas;
- mantém um **contador de visitas por célula**;
- faz exploração adjacente;
- para na **última casa efetivamente visitada**, sem retornar artificialmente ao início.

Essa etapa representa um avanço importante, pois o agente já passa a utilizar um modelo interno do ambiente.

---

### 3️⃣ Etapa 3 — Agente Baseado em Objetivos
Na etapa 3, o agente recebe uma posição inicial fixa e uma posição final fixa no grid.

Seu objetivo é encontrar um **caminho válido** entre esses dois pontos, respeitando os obstáculos do ambiente.

Nesta versão visual:
- o robô percorre o caminho encontrado;
- o caminho correto **não é desenhado previamente na tela**;
- o usuário acompanha apenas o movimento do agente até o objetivo.

---

### 4️⃣ Etapa 4 — Agente Baseado em Utilidade
Na etapa 4, o agente precisa encontrar o caminho de **menor custo total** entre o início e o fim.

Cada célula do grid pode possuir um custo diferente, representando terrenos distintos.

Essa etapa possui duas variações:

#### Variação 1 — Ambiente completamente observável
O agente conhece todos os custos do ambiente desde o início.

#### Variação 2 — Ambiente parcialmente observável
O agente vai descobrindo os custos à medida que percorre o grid.

No menu principal, o usuário escolhe qual variação deseja visualizar.

---

## 🖥️ Visualização do projeto

Uma das principais características desta versão do projeto é a **visualização gráfica da execução**.

O sistema abre uma janela que exibe:

- o grid;
- os obstáculos;
- os custos das células, quando aplicável;
- a posição inicial;
- a posição final;
- o robô se movendo em tempo real;
- a mudança de cor das células por onde o robô passou;
- o contador de passos;
- o contador de visitas, na etapa 2.

Essa visualização torna a análise do comportamento dos agentes mais intuitiva e facilita a apresentação do trabalho.

---

## 🎨 Elementos visuais

Durante a execução, o visualizador utiliza elementos gráficos para representar o ambiente:

- **círculo laranja**: posição atual do robô;
- **quadrados azuis escuros**: obstáculos;
- **quadrados verdes**: células de custo 1;
- **quadrados amarelos**: células de custo 2;
- **quadrados vermelhos**: células de custo 3;
- **quadrados azul-claro**: células já percorridas pelo robô;
- **I**: posição inicial;
- **F**: posição final.

---

## 🏗️ Estrutura final do projeto

A estrutura atual do projeto foi organizada com foco nas versões visuais.

```text
src/
 ├── Main.java
 ├── Pos.java
 ├── Node.java
 ├── GridUtils.java
 ├── JanelaGrid.java
 ├── Etapa1Visual.java
 ├── Etapa2Visual.java
 ├── Etapa3Visual.java
 └── Etapa4Visual.java
