import java.util.ArrayList;
import java.util.List;

public class Etapa1AgenteReativoSimples {

    public void executar() {
        System.out.println("=== ETAPA 1: AGENTE REATIVO SIMPLES ===");

        Pos robo = new Pos(GridUtils.random.nextInt(GridUtils.N), GridUtils.random.nextInt(GridUtils.N));
        List<Pos> caminho = new ArrayList<>();

        System.out.println("Posição inicial: " + robo);

        caminho.add(new Pos(robo.x, robo.y));

        // Leva o robô até o canto superior esquerdo
        while (robo.x > 0) {
            robo = new Pos(robo.x - 1, robo.y);
            caminho.add(new Pos(robo.x, robo.y));
        }

        while (robo.y > 0) {
            robo = new Pos(robo.x, robo.y - 1);
            caminho.add(new Pos(robo.x, robo.y));
        }

        // Contorno em "círculo" no perímetro do grid
        // Topo: esquerda -> direita
        while (robo.y < GridUtils.N - 1) {
            robo = new Pos(robo.x, robo.y + 1);
            caminho.add(new Pos(robo.x, robo.y));
        }

        // Direita: cima -> baixo
        while (robo.x < GridUtils.N - 1) {
            robo = new Pos(robo.x + 1, robo.y);
            caminho.add(new Pos(robo.x, robo.y));
        }

        // Base: direita -> esquerda
        while (robo.y > 0) {
            robo = new Pos(robo.x, robo.y - 1);
            caminho.add(new Pos(robo.x, robo.y));
        }

        // Esquerda: baixo -> cima
        while (robo.x > 1) { // evita repetir a posição inicial do contorno
            robo = new Pos(robo.x - 1, robo.y);
            caminho.add(new Pos(robo.x, robo.y));
        }

        System.out.println("Caminho percorrido:");
        for (Pos p : caminho) {
            System.out.print(p + " ");
        }

        System.out.println("\nTotal de passos: " + (caminho.size() - 1));
        System.out.println();
    }
}
