import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Etapa3AgenteBaseadoEmObjetivos {

    public void executar() {
        System.out.println("=== ETAPA 3: AGENTE BASEADO EM OBJETIVOS ===");

        boolean[][] obstaculos = new boolean[GridUtils.N][GridUtils.N];
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
        obstaculos[6][5] = true;
        obstaculos[7][5] = true;
        obstaculos[8][5] = true;

        Pos inicio = GridUtils.posicaoAleatoriaLivre(obstaculos);
        Pos fim = GridUtils.posicaoAleatoriaLivre(obstaculos);

        while (fim.equals(inicio)) {
            fim = GridUtils.posicaoAleatoriaLivre(obstaculos);
        }

        System.out.println("Início: " + inicio);
        System.out.println("Fim: " + fim);

        List<Pos> caminho = bfs(inicio, fim, obstaculos);

        if (caminho == null) {
            System.out.println("Não existe caminho válido.\n");
        } else {
            System.out.println("Caminho encontrado:");
            for (Pos p : caminho) {
                System.out.print(p + " ");
            }
            System.out.println("\nTamanho do caminho: " + (caminho.size() - 1) + "\n");
        }
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
                List<Pos> caminho = new ArrayList<>();
                Pos p = fim;

                while (p != null) {
                    caminho.add(p);
                    p = pai.get(p);
                }

                Collections.reverse(caminho);
                return caminho;
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
}