import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Etapa4AgenteBaseadoEmUtilidade {

    public void executar() {
        System.out.println("=== ETAPA 4: AGENTE BASEADO EM UTILIDADE ===");

        int[][] custos = new int[GridUtils.N][GridUtils.N];
        boolean[][] obstaculos = new boolean[GridUtils.N][GridUtils.N];

        for (int i = 0; i < GridUtils.N; i++) {
            for (int j = 0; j < GridUtils.N; j++) {
                custos[i][j] = 1;
            }
        }

        for (int i = 2; i <= 5; i++) {
            custos[i][4] = 3;
            custos[i][5] = 3;
        }

        custos[3][2] = 2;
        custos[3][3] = 2;
        custos[4][2] = 2;
        custos[4][3] = 2;

        Pos inicio = new Pos(0, 5);
        Pos fim = new Pos(9, 5);

        System.out.println("Início: " + inicio);
        System.out.println("Fim: " + fim);
        System.out.println("Mapa de custos:");
        GridUtils.imprimirGrid(null, inicio, fim, null, custos);

        List<Pos> caminho = dijkstra(inicio, fim, custos, obstaculos);

        if (caminho == null) {
            System.out.println("Não foi encontrado caminho.\n");
        } else {
            System.out.println("Melhor caminho encontrado:");
            for (Pos p : caminho) {
                System.out.print(p + " ");
            }
            System.out.println("\n");
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

            if (atual.pos.equals(fim)) {
                break;
            }

            for (Pos vizinho : GridUtils.vizinhos(atual.pos)) {
                if (obstaculos[vizinho.x][vizinho.y]) {
                    continue;
                }

                int novoCusto = dist.get(atual.pos) + custos[vizinho.x][vizinho.y];

                if (!dist.containsKey(vizinho) || novoCusto < dist.get(vizinho)) {
                    dist.put(vizinho, novoCusto);
                    pai.put(vizinho, atual.pos);
                    fila.add(new Node(vizinho, novoCusto));
                }
            }
        }

        if (!dist.containsKey(fim)) {
            return null;
        }

        System.out.println("Custo total: " + dist.get(fim));

        List<Pos> caminho = new ArrayList<>();
        Pos p = fim;

        while (p != null) {
            caminho.add(p);
            p = pai.get(p);
        }

        Collections.reverse(caminho);
        return caminho;
    }
}