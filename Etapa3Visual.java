import java.util.*;

public class Etapa3Visual {

    public void executar() {
        boolean[][] obstaculos = criarMapaDeObstaculos();

        Pos inicio = new Pos(0, 0);
        Pos fim = new Pos(7, 7);

        List<Pos> caminho = bfs(inicio, fim, obstaculos);

        if (caminho == null) {
            System.out.println("Não existe caminho válido.");
            return;
        }

        JanelaGrid janela = new JanelaGrid(GridUtils.N, 60, "Etapa 3 - Agente Baseado em Objetivos");
        janela.setObstaculos(obstaculos);
        janela.setInicioFim(inicio, fim);
        janela.setRobo(inicio);
        janela.setVisible(true);

        new Thread(() -> {
            int passos = 0;
            for (int i = 0; i < caminho.size(); i++) {
                Pos p = caminho.get(i);
                janela.setRobo(p);
                janela.setPassos(passos);
                janela.pausar(350);

                if (i < caminho.size() - 1) {
                    passos++;
                }
            }
        }).start();
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

    private List<Pos> bfs(Pos inicio, Pos fim, boolean[][] obstaculos) {
        Queue<Pos> fila = new LinkedList<>();
        Map<Pos, Pos> pai = new HashMap<>();
        Set<Pos> visitados = new HashSet<>();

        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            Pos atual = fila.poll();

            if (atual.equals(fim)) {
                return reconstruir(fim, pai);
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