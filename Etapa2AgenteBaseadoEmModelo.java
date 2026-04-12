public class Etapa2AgenteBaseadoEmModelo {

    private int totalPassagens = 0;

    public void executar() {
        System.out.println("=== ETAPA 2: AGENTE REATIVO BASEADO EM MODELO ===");

        boolean[][] obstaculos = criarMapaDeObstaculos();
        boolean[][] descoberto = new boolean[GridUtils.N][GridUtils.N];
        int[][] contadorVisitas = new int[GridUtils.N][GridUtils.N];

        Pos robo = GridUtils.posicaoAleatoriaLivre(obstaculos);
        System.out.println("Posição inicial: " + robo);

        explorar(robo, obstaculos, descoberto, contadorVisitas);

        System.out.println("\nTotal de passagens do agente: " + totalPassagens);
        System.out.println("Contador de visitas por célula:");
        imprimirContador(contadorVisitas);
        System.out.println();
    }

    private void explorar(Pos atual, boolean[][] obstaculos, boolean[][] descoberto, int[][] contadorVisitas) {
        contadorVisitas[atual.x][atual.y]++;
        totalPassagens++;

        System.out.println("Agente em: " + atual + " | Visitas nesta célula: " + contadorVisitas[atual.x][atual.y]);

        descoberto[atual.x][atual.y] = true;

        for (Pos vizinho : GridUtils.vizinhos(atual)) {
            if (!obstaculos[vizinho.x][vizinho.y] && !descoberto[vizinho.x][vizinho.y]) {
                explorar(vizinho, obstaculos, descoberto, contadorVisitas);

                // Simula o retorno físico do agente para a célula atual
                contadorVisitas[atual.x][atual.y]++;
                totalPassagens++;
                System.out.println("Retornando para: " + atual + " | Visitas nesta célula: " + contadorVisitas[atual.x][atual.y]);
            }
        }
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

    private void imprimirContador(int[][] contadorVisitas) {
        for (int i = 0; i < GridUtils.N; i++) {
            for (int j = 0; j < GridUtils.N; j++) {
                System.out.printf("%3d", contadorVisitas[i][j]);
            }
            System.out.println();
        }
    }
}
