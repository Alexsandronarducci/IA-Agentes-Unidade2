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
        int[][] dirs = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

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
}