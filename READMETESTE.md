# Projeto de Agentes Inteligentes em Grid

## Sobre o projeto

Este projeto foi desenvolvido com o objetivo de representar, de forma prática, a evolução de diferentes **tipologias de agentes inteligentes** em um ambiente simulado do tipo **grid n × n**.

A implementação foi realizada em **Java**, com foco em uma **abordagem simbólica**, priorizando regras explícitas de decisão, representação do ambiente e algoritmos clássicos de busca.

Ao longo da execução, o agente evolui em quatro etapas:

- **Agente Reativo Simples**
- **Agente Reativo Baseado em Modelo**
- **Agente Baseado em Objetivos**
- **Agente Baseado em Utilidade**

Cada etapa representa um nível mais sofisticado de percepção, tomada de decisão e interação com o ambiente.

---

## Objetivo

O principal objetivo deste projeto é demonstrar como diferentes arquiteturas de agentes influenciam o comportamento de um robô em um ambiente discreto.

A proposta busca evidenciar a diferença entre:

- agir apenas com base na percepção atual;
- agir com apoio de memória e representação interna;
- agir orientado a um objetivo explícito;
- agir escolhendo a melhor alternativa com base em custo.

---

## Conceitos trabalhados

Este projeto aplica conceitos fundamentais de Inteligência Artificial, como:

- agentes inteligentes;
- ambiente discreto em grid;
- percepção e ação;
- estado interno;
- objetivos;
- utilidade;
- espaço de estados;
- busca em largura (**BFS**);
- caminho de menor custo com **Dijkstra**;
- modelagem simbólica de problemas.

---

## Estrutura do projeto

A organização do código foi feita em **classes separadas**, buscando melhorar a legibilidade, a manutenção e a compreensão da responsabilidade de cada parte do sistema.

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

=== ETAPA 1: AGENTE REATIVO SIMPLES ===
Posição inicial: (4,3)
Robô em: (3,3)
Robô em: (2,3)
...
Todas as fronteiras foram alcançadas.

=== ETAPA 2: AGENTE REATIVO BASEADO EM MODELO ===
Visitando: (1,1)
Visitando: (1,2)
...
Total de células visitadas: 93

=== ETAPA 3: AGENTE BASEADO EM OBJETIVOS ===
Início: (0,2)
Fim: (8,7)
Caminho encontrado:
(0,2) (0,3) (1,3) ...

=== ETAPA 4: AGENTE BASEADO EM UTILIDADE ===
Início: (0,5)
Fim: (9,5)
Custo total: 13
Melhor caminho encontrado:
(0,5) (1,5) (1,4) ...
