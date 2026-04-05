import java.util.Stack;

public class Etapa2AgenteBaseadoEmModelo {

    public void executar() {
        System.out.println("=== ETAPA 2: AGENTE REATIVO BASEADO EM MODELO ===");

        boolean[][] obstaculos = new boolean[GridUtils.N][GridUtils.N];
        obstaculos[1][3] = true;
        obstaculos[2][3] = true;
        obstaculos[3][5] = true;
        obstaculos[4][1] = true;
        obstaculos[5][6] = true;
        obstaculos[6][6] = true;
        obstaculos[7][6] = true;

        boolean[][] visitado = new boolean[GridUtils.N][GridUtils.N];
        Pos robo = GridUtils.posicaoAleatoriaLivre(obstaculos);

        int visitadas = 0;
        Stack<Pos> pilha = new Stack<>();
        pilha.push(robo);

        while (!pilha.isEmpty()) {
            Pos atual = pilha.pop();

            if (visitado[atual.x][atual.y]) {
                continue;
            }

            visitado[atual.x][atual.y] = true;
            visitadas++;

            System.out.println("Visitando: " + atual);

            for (Pos vizinho : GridUtils.vizinhos(atual)) {
                if (!obstaculos[vizinho.x][vizinho.y] && !visitado[vizinho.x][vizinho.y]) {
                    pilha.push(vizinho);
                }
            }
        }

        System.out.println("Total de células visitadas: " + visitadas + "\n");
    }
}