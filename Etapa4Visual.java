import java.util.*;

public class Etapa4Visual {

    public void executar(int variacao) {
        int[][] custos = criarMapaDeCustos();
        boolean[][] obstaculos = new boolean[GridUtils.N][GridUtils.N];

        Pos inicio = new Pos(0, 5);
        Pos fim = new Pos(9, 5);

        if (variacao == 1) {
            executarVariacao1(inicio, fim, custos, obstaculos);
        } else if (variacao == 2) {
            executarVariacao2(inicio, fim, custos, obstaculos);
        } else {
            System.out.println("Variação inválida.");
        }
    }

    private void executarVariacao1(Pos inicio, Pos fim, int[][] custos, boolean[][] obstaculos) {
        List<Pos> caminho = dijkstra(inicio, fim, custos, obstaculos);

        if (caminho == null) {
            System.out.println("Variação 1: não foi encontrado caminho.");
            return;
        }

        JanelaGrid janela = new JanelaGrid(GridUtils.N, 55, "Etapa 4 - Agente Baseado em Utilidade");
        janela.setCustos(custos);
        janela.setObstaculos(obstaculos);
        janela.setInicioFim(inicio, fim);
        janela.setRobo(inicio);
        janela.setSubtitulo("Variação 1: completamente observável");
        janela.setVisible(true);

        new Thread(() -> {
            int passos = 0;
            for (int i = 0; i < caminho.size(); i++) {
                janela.setRobo(caminho.get(i));
                janela.setPassos(passos);
                janela.pausar(350);

                if (i < caminho.size() - 1) {
                    passos++;
                }
            }
        }).start();
    }

    private void executarVariacao2(Pos inicio, Pos fim, int[][] custosReais, boolean[][] obstaculos) {
        JanelaGrid janela = new JanelaGrid(GridUtils.N, 55, "Etapa 4 - Agente Baseado em Utilidade");
        janela.setObstaculos(obstaculos);
        janela.setInicioFim(inicio, fim);
        janela.setRobo(inicio);
        janela.setSubtitulo("Variação 2: parcialmente observável");
        janela.setVisible(true);

        new Thread(() -> {
            int[][] custosConhecidos = new int[GridUtils.N][GridUtils.N];
            for (int i = 0; i < GridUtils.N; i++) {
                Arrays.fill(custosConhecidos[i], 1);
            }

            Pos atual = inicio;
            int passos = 0;

            while (!atual.equals(fim)) {
                revelarCustos(atual, custosReais, custosConhecidos);
                janela.setCustos(custosConhecidos);

                List<Pos> plano = dijkstra(atual, fim, custosConhecidos, obstaculos);
                if (plano == null || plano.size() < 2) {
                    System.out.println("Variação 2: não foi possível continuar.");
                    return;
                }

                atual = plano.get(1);
                janela.setRobo(atual);
                janela.setPassos(passos);
                janela.pausar(350);
                passos++;
            }
        }).start();
    }

    private int[][] criarMapaDeCustos() {
        int[][] custos = new int[GridUtils.N][GridUtils.N];

        for (int i = 0; i < GridUtils.N; i++) {
            for (int j = 0; j < GridUtils.N; j++) {
                custos[i][j] = 1;
            }
        }

        custos[0][5] = 3;
        custos[1][5] = 3;
        custos[2][5] = 3;
        custos[3][5] = 3;
        custos[4][5] = 3;
        custos[5][5] = 3;

        custos[2][4] = 3;
        custos[2][6] = 3;
        custos[2][7] = 3;
        custos[3][7] = 3;
        custos[4][4] = 3;
        custos[4][6] = 3;
        custos[5][6] = 3;

        custos[2][2] = 2;
        custos[2][3] = 2;
        custos[3][2] = 2;
        custos[4][2] = 2;
        custos[4][3] = 2;
        custos[2][8] = 2;
        custos[3][8] = 2;
        custos[4][7] = 2;
        custos[4][8] = 2;
        custos[4][9] = 2;
        custos[5][7] = 2;
        custos[3][9] = 2;

        return custos;
    }

    private void revelarCustos(Pos atual, int[][] custosReais, int[][] custosConhecidos) {
        custosConhecidos[atual.x][atual.y] = custosReais[atual.x][atual.y];
        for (Pos vizinho : GridUtils.vizinhos(atual)) {
            custosConhecidos[vizinho.x][vizinho.y] = custosReais[vizinho.x][vizinho.y];
        }
    }

    private List<Pos> dijkstra(Pos inicio, Pos fim, int[][] custos, boolean[][] obstaculos) {
        PriorityQueue<Node> fila = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Map<Pos, Integer> dist = new HashMap<>();
        Map<Pos, Pos> pai = new HashMap<>();

        dist.put(inicio, 0);
        fila.add(new Node(inicio, 0));

        while (!fila.isEmpty()) {
            Node atual = fila.poll();

            if (atual.cost > dist.getOrDefault(atual.pos, Integer.MAX_VALUE)) {
                continue;
            }

            if (atual.pos.equals(fim)) {
                return reconstruir(fim, pai);
            }

            for (Pos vizinho : GridUtils.vizinhos(atual.pos)) {
                if (obstaculos[vizinho.x][vizinho.y]) {
                    continue;
                }

                int novoCusto = dist.get(atual.pos) + custos[vizinho.x][vizinho.y];

                if (novoCusto < dist.getOrDefault(vizinho, Integer.MAX_VALUE)) {
                    dist.put(vizinho, novoCusto);
                    pai.put(vizinho, atual.pos);
                    fila.add(new Node(vizinho, novoCusto));
                }
            }
        }

        return null;
    }

    private List<Pos> reconstruir(Pos fim, Map<Pos, Pos> pai) {
        List<Pos> caminho = new ArrayList<>();
        Pos atual = fim;

        while (atual != null) {
            caminho.add(atual);
            atual = pai.get(atual);
        }

        Collections.reverse(caminho);
        return caminho;
    }
}