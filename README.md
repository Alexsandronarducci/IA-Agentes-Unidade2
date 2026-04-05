# IA-Agentes-Unidade2

Documentação agentes código

Alunos: Alexsandro N, Cauan V.de Souza, Claudio S. Junior, Lucas C. Belletti, Rodrigo Amaral


# Atividade Avaliativa: Agentes Inteligentes (Unidade 2)

## 1. Descrição do Problema (Espaço de Estados)
O problema consiste em um agente situado em um grid $10 \times 10$.
* **Estado Inicial**: Posição $(x, y)$ aleatória.
* **Estado Objetivo**: Varia entre alcançar fronteiras (Etapa 1), explorar o mapa (Etapa 2) ou atingir um ponto $f$ (Etapas 3 e 4).
* **Ações**: {Norte, Sul, Leste, Oeste}.
* **Espaço de Estados**: Todas as coordenadas válidas no grid $[0, 9]$.

## 2. Framework PEAS
| Atributo | Descrição |
| :--- | :--- |
| **Performance** | Eficiência na exploração e minimização de custos de terreno. |
| **Ambiente** | Grid $10 \times 10$, inicialmente vazio, evoluindo para com obstáculos e custos. |
| **Atuadores** | Movimentação cardeal (N, S, L, O). |
| **Sensores** | Detector de posição atual, limites do grid e custos das células adjacentes. |

## 3. Descrição das Etapas
1. **Agente Reativo Simples**: Decisão baseada apenas na posição atual para tocar as 4 bordas.
2. **Agente Baseado em Modelo**: Possui memória (matriz) para evitar células visitadas e desviar de obstáculos.
3. **Agente Baseado em Objetivos**: Busca um caminho direto para uma coordenada final $f$.
4. **Agente Baseado em Utilidade**: Escolhe o caminho de menor custo (Dijkstra/Busca de Custo Uniforme).
