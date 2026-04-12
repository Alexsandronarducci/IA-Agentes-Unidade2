import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROJETO DE AGENTES EM GRID ===");
        System.out.println("1 - Etapa 1 visual");
        System.out.println("2 - Etapa 2 visual");
        System.out.println("3 - Etapa 3 visual");
        System.out.println("4 - Etapa 4 visual");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                new Etapa1Visual().executar();
                break;

            case 2:
                new Etapa2Visual().executar();
                break;

            case 3:
                new Etapa3Visual().executar();
                break;

            case 4:
                System.out.println("Escolha a variação da Etapa 4:");
                System.out.println("1 - Ambiente completamente observável");
                System.out.println("2 - Ambiente parcialmente observável");
                System.out.print("Opção: ");
                int variacao = scanner.nextInt();
                new Etapa4Visual().executar(variacao);
                break;

            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}
