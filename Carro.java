import java.util.Random;

public class Carro implements Runnable {

    private final String nome;
    private final double distanciaTotalCorrida;
    private double distanciaPercorrida = 0;
    private boolean fezPitStop = false;

    private final Podio podio;
    private final Random random = new Random();

    public Carro(String nome, double distanciaTotalCorrida, Podio podio) {
        this.nome = nome;
        this.distanciaTotalCorrida = distanciaTotalCorrida;
        this.podio = podio;
    }

    public String getNome() {
        return nome;
    }

    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    @Override
    public void run() {
        while (distanciaPercorrida < distanciaTotalCorrida) {
            int avanco = random.nextInt(20) + 1;
            distanciaPercorrida += avanco;
            double exibida = Math.min(distanciaPercorrida, distanciaTotalCorrida);

            System.out.println(nome + " andou " + avanco + " metros e já percorreu "
                    + (int) exibida + " de " + (int) distanciaTotalCorrida + " metros.");

            if (!fezPitStop && distanciaPercorrida >= distanciaTotalCorrida / 2
                    && distanciaPercorrida < distanciaTotalCorrida) {
                fezPitStop = true;
                if (!pitStop()) {
                    return;
                }
            }

            try {
                Thread.sleep(100 + random.nextInt(401));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nome + " foi interrompido.");
                return;
            }
        }

        System.out.println("[CHEGADA] O " + nome + " cruzou a linha de chegada!");
        podio.registrarChegada(nome);
    }

    private boolean pitStop() {
        System.out.println("[PIT STOP] " + nome + " parou nos boxes para trocar os pneus...");
        try {
            Thread.sleep(1000 + random.nextInt(1001));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        System.out.println("[PIT STOP] " + nome + " voltou para a pista!");
        return true;
    }
}
