public class Main {
    public static void main(String[] args) {
        Etapa1AgenteReativoSimples etapa1 = new Etapa1AgenteReativoSimples();
        Etapa2AgenteBaseadoEmModelo etapa2 = new Etapa2AgenteBaseadoEmModelo();
        Etapa3AgenteBaseadoEmObjetivos etapa3 = new Etapa3AgenteBaseadoEmObjetivos();
        Etapa4AgenteBaseadoEmUtilidade etapa4 = new Etapa4AgenteBaseadoEmUtilidade();

        etapa1.executar();
        etapa2.executar();
        etapa3.executar();
        etapa4.executar();
    }
}