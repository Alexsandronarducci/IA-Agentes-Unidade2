import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Etapa1Visual {

    public void executar() {
        Pos inicio = gerarPosicaoInterna();
        List<Pos> caminho = gerarCaminho(inicio);

        JanelaGrid janela = new JanelaGrid(GridUtils.N, 60, "Etapa 1 - Agente Reativo Simples");
        janela.setInicioFim(inicio, null);
        janela.setRobo(inicio);
        janela.setSubtitulo("Norte → Leste → Sul → Oeste");
        janela.setVisible(true);

        new Thread(() -> {
            int passos = 0;
            for (int i = 0; i < caminho.size(); i++) {
                janela.setRobo(caminho.get(i));
                janela.setPassos(passos);
                janela.pausar(300);

                if (i < caminho.size() - 1) {
                    passos++;
                }
            }
        }).start();
    }

    private Pos gerarPosicaoInterna() {
        int x = 1 + GridUtils.random.nextInt(GridUtils.N - 2);
        int y = 1 + GridUtils.random.nextInt(GridUtils.N - 2);
        return new Pos(x, y);
    }

    private List<Pos> gerarCaminho(Pos inicio) {
        List<Pos> caminho = new ArrayList<>();
        Set<Pos> visitados = new HashSet<>();

        Pos atual = new Pos(inicio.x, inicio.y);
        adicionar(caminho, visitados, atual);

        while (atual.x > 0) {
            atual = new Pos(atual.x - 1, atual.y);
            adicionar(caminho, visitados, atual);
        }

        while (atual.y < GridUtils.N - 1) {
            atual = new Pos(atual.x, atual.y + 1);
            adicionar(caminho, visitados, atual);
        }

        while (atual.x < GridUtils.N - 1) {
            atual = new Pos(atual.x + 1, atual.y);
            adicionar(caminho, visitados, atual);
        }

        while (atual.y > 0) {
            atual = new Pos(atual.x, atual.y - 1);
            adicionar(caminho, visitados, atual);
        }

        return caminho;
    }

    private void adicionar(List<Pos> caminho, Set<Pos> visitados, Pos p) {
        Pos copia = new Pos(p.x, p.y);
        if (!visitados.contains(copia)) {
            caminho.add(copia);
            visitados.add(copia);
        }
    }
}