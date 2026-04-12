public class Etapa2Visual {

    private boolean[][] obstaculos;
    private boolean[][] visitado;
    private int[][] contadorVisitas;
    private JanelaGrid janela;
    private int passos = 0;

    public void executar() {
        obstaculos = criarMapaDeObstaculos();
        visitado = new boolean[GridUtils.N][GridUtils.N];
        contadorVisitas = new int[GridUtils.N][GridUtils.N];

        Pos inicio = GridUtils.posicaoAleatoriaLivre(obstaculos);

        janela = new JanelaGrid(GridUtils.N, 60, "Etapa 2 - Agente Baseado em Modelo");
        janela.setObstaculos(obstaculos);
        janela.setContadorVisitas(contadorVisitas);
        janela.setInicioFim(inicio, null);
        janela.setRobo(inicio);
        janela.setSubtitulo("Exploração física até a última casa");
        janela.setVisible(true);

        new Thread(() -> explorar(inicio, true)).start();
    }

    private void explorar(Pos atual, boolean chamadaInicial) {
        visitar(atual);
        visitado[atual.x][atual.y] = true;

        for (Pos vizinho : GridUtils.vizinhos(atual)) {
            if (!obstaculos[vizinho.x][vizinho.y] && !visitado[vizinho.x][vizinho.y]) {
                passos++;
                explorar(vizinho, false);

                // Só volta se ainda houver trabalho após retornar
                if (!exploracaoFinalizada()) {
                    passos++;
                    visitar(atual);
                }
            }
        }
    }

    private boolean exploracaoFinalizada() {
        for (int i = 0; i < GridUtils.N; i++) {
            for (int j = 0; j < GridUtils.N; j++) {
                if (!obstaculos[i][j] && !visitado[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void visitar(Pos pos) {
        contadorVisitas[pos.x][pos.y]++;
        janela.setContadorVisitas(contadorVisitas);
        janela.setRobo(pos);
        janela.setPassos(passos);
        janela.pausar(250);
    }

    private boolean[][] criarMapaDeObstaculos() {
        boolean[][] o = new boolean[GridUtils.N][GridUtils.N];

        o[0][4] = true;
        o[1][0] = true;
        o[1][3] = true;
        o[2][2] = true;
        o[3][2] = true;
        o[3][5] = true;
        o[4][1] = true;
        o[4][6] = true;
        o[5][3] = true;
        o[5][5] = true;
        o[5][6] = true;
        o[5][7] = true;
        o[5][8] = true;
        o[6][5] = true;
        o[6][8] = true;
        o[7][5] = true;
        o[7][8] = true;
        o[8][5] = true;
        o[8][7] = true;
        o[8][8] = true;
        o[9][5] = true;

        return o;
    }
}