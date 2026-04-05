import java.util.HashSet;
import java.util.Set;

public class Etapa1AgenteReativoSimples {

    public void executar() {
        System.out.println("=== ETAPA 1: AGENTE REATIVO SIMPLES ===");

        Pos robo = new Pos(GridUtils.random.nextInt(GridUtils.N), GridUtils.random.nextInt(GridUtils.N));
        Set<String> fronteiras = new HashSet<>();

        System.out.println("Posição inicial: " + robo);

        while (fronteiras.size() < 4) {
            if (robo.x == 0) fronteiras.add("NORTE");
            if (robo.x == GridUtils.N - 1) fronteiras.add("SUL");
            if (robo.y == 0) fronteiras.add("OESTE");
            if (robo.y == GridUtils.N - 1) fronteiras.add("LESTE");

            String alvo;
            if (!fronteiras.contains("NORTE")) {
                alvo = "NORTE";
            } else if (!fronteiras.contains("SUL")) {
                alvo = "SUL";
            } else if (!fronteiras.contains("OESTE")) {
                alvo = "OESTE";
            } else {
                alvo = "LESTE";
            }

            if (alvo.equals("NORTE") && robo.x > 0) {
                robo = new Pos(robo.x - 1, robo.y);
            } else if (alvo.equals("SUL") && robo.x < GridUtils.N - 1) {
                robo = new Pos(robo.x + 1, robo.y);
            } else if (alvo.equals("OESTE") && robo.y > 0) {
                robo = new Pos(robo.x, robo.y - 1);
            } else if (alvo.equals("LESTE") && robo.y < GridUtils.N - 1) {
                robo = new Pos(robo.x, robo.y + 1);
            }

            System.out.println("Robô em: " + robo + " | Fronteiras alcançadas: " + fronteiras);
        }

        System.out.println("Todas as fronteiras foram alcançadas.\n");
    }
}