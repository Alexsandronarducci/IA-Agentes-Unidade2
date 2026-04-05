# 🤖 Projeto de Agentes Inteligentes em Grid

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk)
![Status](https://img.shields.io/badge/status-conclu%C3%ADdo-success?style=for-the-badge)
![Paradigm](https://img.shields.io/badge/abordagem-simb%C3%B3lica-blue?style=for-the-badge)
![License](https://img.shields.io/badge/license-acad%C3%AAmico-lightgrey?style=for-the-badge)

## 📘 Sobre o projeto

Este projeto apresenta a implementação, em **Java**, de diferentes **tipologias de agentes inteligentes** em um ambiente do tipo **grid n × n**.

A proposta foi desenvolvida para demonstrar, de forma prática, a evolução do comportamento do agente ao longo de quatro etapas, saindo de uma abordagem reativa simples até uma tomada de decisão baseada em utilidade.

---

## 🎯 Objetivo

Simular o comportamento de um robô em um grid, analisando como diferentes arquiteturas de agentes influenciam sua capacidade de:

- perceber o ambiente;
- tomar decisões;
- representar informações internamente;
- alcançar objetivos;
- escolher soluções de menor custo.

---

## 🧠 Etapas implementadas

### 1️⃣ Agente Reativo Simples
O agente toma decisões apenas com base na percepção atual, sem memória do ambiente.

### 2️⃣ Agente Reativo Baseado em Modelo
O agente passa a manter um estado interno, registrando células visitadas e obstáculos.

### 3️⃣ Agente Baseado em Objetivos
O agente busca encontrar um caminho válido entre uma posição inicial e uma posição final.

### 4️⃣ Agente Baseado em Utilidade
O agente escolhe, entre os caminhos possíveis, aquele com **menor custo total**.

---

## 🛠️ Tecnologias e conceitos utilizados

- **Java**
- **Programação orientada a objetos**
- **Busca em largura (BFS)**
- **Algoritmo de Dijkstra**
- **Representação simbólica de estados**
- **Estruturas clássicas de IA**

---

## 📂 Estrutura do projeto

```text
src/
 ├── Main.java
 ├── Pos.java
 ├── Node.java
 ├── GridUtils.java
 ├── Etapa1AgenteReativoSimples.java
 ├── Etapa2AgenteBaseadoEmModelo.java
 ├── Etapa3AgenteBaseadoEmObjetivos.java
 └── Etapa4AgenteBaseadoEmUtilidade.java
