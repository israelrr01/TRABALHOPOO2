import java.util.ArrayList;
import java.util.List;

public class Corrida {

    private static final double DISTANCIA_TOTAL = 1000;
    private static final int NUMERO_DE_CARROS = 5;

    public static void main(String[] args) {
        Podio podio = new Podio();
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= NUMERO_DE_CARROS; i++) {
            Carro carro = new Carro("Carro " + i, DISTANCIA_TOTAL, podio);
            threads.add(new Thread(carro, "thread-carro-" + i));
        }

        System.out.println("=== A corrida vai começar! (" + NUMERO_DE_CARROS
                + " carros, " + (int) DISTANCIA_TOTAL + " metros) ===");

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Corrida interrompida.");
                return;
            }
        }

        System.out.println("=== Fim da corrida! ===");
        podio.exibir();
    }
}
