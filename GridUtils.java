import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridUtils {
    public static final int N = 10;
    public static final Random random = new Random();

    public static boolean dentroDoGrid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static List<Pos> vizinhos(Pos p) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Pos> lista = new ArrayList<>();

        for (int[] d : dirs) {
            int nx = p.x + d[0];
            int ny = p.y + d[1];
            if (dentroDoGrid(nx, ny)) {
                lista.add(new Pos(nx, ny));
            }
        }

        return lista;
    }

    public static Pos posicaoAleatoriaLivre(boolean[][] obstaculos) {
        while (true) {
            int x = random.nextInt(N);
            int y = random.nextInt(N);

            if (obstaculos == null || !obstaculos[x][y]) {
                return new Pos(x, y);
            }
        }
    }

    public static void imprimirGrid(Pos robo, Pos inicio, Pos fim, boolean[][] obstaculos, int[][] custos) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (robo != null && robo.x == i && robo.y == j) {
                    System.out.print(" R ");
                } else if (inicio != null && inicio.x == i && inicio.y == j) {
                    System.out.print(" I ");
                } else if (fim != null && fim.x == i && fim.y == j) {
                    System.out.print(" F ");
                } else if (obstaculos != null && obstaculos[i][j]) {
                    System.out.print(" X ");
                } else if (custos != null) {
                    System.out.print(" " + custos[i][j] + " ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}