import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Etapa3AgenteBaseadoEmObjetivos {

    public void executar() {
        System.out.println("=== ETAPA 3: AGENTE BASEADO EM OBJETIVOS ===");

        boolean[][] obstaculos = criarMapaDeObstaculos();

        Pos inicio = new Pos(0, 0); // posição fixa da imagem (i)
        Pos fim = new Pos(7, 7);    // posição fixa da imagem (f)

        System.out.println("Início fixo: " + inicio);
        System.out.println("Fim fixo: " + fim);

        List<Pos> caminhoBFS = bfs(inicio, fim, obstaculos);
        List<Pos> caminhoDFS = dfs(inicio, fim, obstaculos);

        System.out.println("\nBusca em Largura (BFS):");
        imprimirCaminho(caminhoBFS);

        System.out.println("\nBusca em Profundidade (DFS):");
        imprimirCaminho(caminhoDFS);

        System.out.println();
    }

    private boolean[][] criarMapaDeObstaculos() {
        boolean[][] obstaculos = new boolean[GridUtils.N][GridUtils.N];

        obstaculos[0][4] = true;
        obstaculos[1][0] = true;
        obstaculos[1][3] = true;
        obstaculos[2][2] = true;
        obstaculos[3][2] = true;
        obstaculos[3][5] = true;
        obstaculos[4][1] = true;
        obstaculos[4][6] = true;
        obstaculos[5][3] = true;
        obstaculos[5][5] = true;
        obstaculos[5][6] = true;
        obstaculos[5][7] = true;
        obstaculos[5][8] = true;
        obstaculos[6][5] = true;
        obstaculos[6][8] = true;
        obstaculos[7][5] = true;
        obstaculos[7][8] = true;
        obstaculos[8][5] = true;
        obstaculos[8][7] = true;
        obstaculos[8][8] = true;
        obstaculos[9][5] = true;

        return obstaculos;
    }

    private List<Pos> bfs(Pos inicio, Pos fim, boolean[][] obstaculos) {
        Queue<Pos> fila = new LinkedList<>();
        Map<Pos, Pos> pai = new HashMap<>();
        Set<Pos> visitados = new HashSet<>();

        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            Pos atual = fila.poll();

            if (atual.equals(fim)) {
                return reconstruirCaminho(fim, pai);
            }

            for (Pos vizinho : GridUtils.vizinhos(atual)) {
                if (!obstaculos[vizinho.x][vizinho.y] && !visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    pai.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        return null;
    }

    private List<Pos> dfs(Pos inicio, Pos fim, boolean[][] obstaculos) {
        Stack<Pos> pilha = new Stack<>();
        Map<Pos, Pos> pai = new HashMap<>();
        Set<Pos> visitados = new HashSet<>();

        pilha.push(inicio);
        visitados.add(inicio);

        while (!pilha.isEmpty()) {
            Pos atual = pilha.pop();

            if (atual.equals(fim)) {
                return reconstruirCaminho(fim, pai);
            }

            for (Pos vizinho : GridUtils.vizinhos(atual)) {
                if (!obstaculos[vizinho.x][vizinho.y] && !visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    pai.put(vizinho, atual);
                    pilha.push(vizinho);
                }
            }
        }

        return null;
    }

    private List<Pos> reconstruirCaminho(Pos fim, Map<Pos, Pos> pai) {
        List<Pos> caminho = new ArrayList<>();
        Pos atual = fim;

        while (atual != null) {
            caminho.add(atual);
            atual = pai.get(atual);
        }

        Collections.reverse(caminho);
        return caminho;
    }

    private void imprimirCaminho(List<Pos> caminho) {
        if (caminho == null) {
            System.out.println("Não existe caminho válido.");
            return;
        }

        System.out.println("Caminho encontrado:");
        for (Pos p : caminho) {
            System.out.print(p + " ");
        }
        System.out.println("\nTamanho do caminho: " + (caminho.size() - 1));
    }
}
