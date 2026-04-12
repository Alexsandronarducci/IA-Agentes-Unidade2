import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class JanelaGrid extends JFrame {

    private final int n;
    private final int tamanhoCelula;

    private boolean[][] obstaculos;
    private int[][] custos;
    private int[][] contadorVisitas;

    private Pos inicio;
    private Pos fim;
    private Pos robo;

    private int passos = 0;
    private String subtitulo = "";

    private final Set<Pos> casasPercorridas = new HashSet<>();

    public JanelaGrid(int n, int tamanhoCelula, String titulo) {
        this.n = n;
        this.tamanhoCelula = tamanhoCelula;

        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(n * tamanhoCelula + 40, n * tamanhoCelula + 100);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new PainelGrid());
    }

    public void setObstaculos(boolean[][] obstaculos) {
        this.obstaculos = obstaculos;
        repaint();
    }

    public void setCustos(int[][] custos) {
        this.custos = custos;
        repaint();
    }

    public void setContadorVisitas(int[][] contadorVisitas) {
        this.contadorVisitas = contadorVisitas;
        repaint();
    }

    public void setInicioFim(Pos inicio, Pos fim) {
        this.inicio = inicio;
        this.fim = fim;
        repaint();
    }

    public void setRobo(Pos robo) {
        this.robo = robo;
        if (robo != null) {
            casasPercorridas.add(new Pos(robo.x, robo.y));
        }
        repaint();
    }

    public void setPassos(int passos) {
        this.passos = passos;
        repaint();
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
        repaint();
    }

    public void limparPercurso() {
        casasPercorridas.clear();
        repaint();
    }

    public void pausar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class PainelGrid extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int x = 10 + j * tamanhoCelula;
                    int y = 10 + i * tamanhoCelula;

                    Color cor = Color.WHITE;

                    if (custos != null) {
                        int custo = custos[i][j];
                        if (custo == 1) cor = new Color(110, 220, 120);
                        else if (custo == 2) cor = new Color(255, 235, 120);
                        else if (custo >= 3) cor = new Color(255, 120, 120);
                    }

                    if (casasPercorridas.contains(new Pos(i, j))) {
                        cor = new Color(180, 220, 255);
                    }

                    if (obstaculos != null && obstaculos[i][j]) {
                        cor = new Color(50, 90, 160);
                    }

                    g2.setColor(cor);
                    g2.fillRect(x, y, tamanhoCelula, tamanhoCelula);

                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, tamanhoCelula, tamanhoCelula);

                    if (custos != null && (obstaculos == null || !obstaculos[i][j])) {
                        g2.setFont(new Font("Arial", Font.PLAIN, 13));
                        g2.drawString(String.valueOf(custos[i][j]), x + 5, y + 15);
                    }

                    if (contadorVisitas != null && contadorVisitas[i][j] > 0) {
                        g2.setFont(new Font("Arial", Font.BOLD, 16));
                        String txt = String.valueOf(contadorVisitas[i][j]);
                        g2.drawString(txt, x + tamanhoCelula / 2 - 5, y + tamanhoCelula / 2 + 5);
                    }

                    if (inicio != null && inicio.x == i && inicio.y == j) {
                        g2.setFont(new Font("Arial", Font.BOLD, 18));
                        g2.drawString("I", x + tamanhoCelula - 20, y + 20);
                    }

                    if (fim != null && fim.x == i && fim.y == j) {
                        g2.setFont(new Font("Arial", Font.BOLD, 18));
                        g2.drawString("F", x + tamanhoCelula - 20, y + 20);
                    }
                }
            }

            if (robo != null) {
                int x = 10 + robo.y * tamanhoCelula;
                int y = 10 + robo.x * tamanhoCelula;

                g2.setColor(Color.ORANGE);
                g2.fillOval(x + 10, y + 10, tamanhoCelula - 20, tamanhoCelula - 20);
                g2.setColor(Color.BLACK);
                g2.drawOval(x + 10, y + 10, tamanhoCelula - 20, tamanhoCelula - 20);
            }

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString("Passos: " + passos, 10, n * tamanhoCelula + 35);

            if (subtitulo != null && !subtitulo.isEmpty()) {
                g2.drawString(subtitulo, 150, n * tamanhoCelula + 35);
            }
        }
    }
}