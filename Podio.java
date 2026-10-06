import java.util.ArrayList;
import java.util.List;

public class Podio {

    private final List<String> chegada = new ArrayList<>();

    public void registrarChegada(String nomeCarro) {
        synchronized (this) {
            chegada.add(nomeCarro);
            int posicao = chegada.size();
            System.out.println(">>> " + nomeCarro + " terminou em " + posicao + "º lugar.");
        }
    }

    public synchronized List<String> getChegada() {
        return new ArrayList<>(chegada);
    }

    public void exibir() {
        List<String> ordem = getChegada();
        System.out.println();
        System.out.println("===== PÓDIO =====");
        String[] medalhas = {"1º", "2º", "3º"};
        for (int i = 0; i < ordem.size() && i < 3; i++) {
            System.out.println(medalhas[i] + " lugar: " + ordem.get(i));
        }
        if (ordem.size() > 3) {
            for (int i = 3; i < ordem.size(); i++) {
                System.out.println((i + 1) + "º lugar: " + ordem.get(i));
            }
        }
    }
}
