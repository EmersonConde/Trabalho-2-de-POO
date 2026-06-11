import java.util.List;
import java.util.Random;

public class Salvia extends Planta{
    // Construtores
    public Salvia() {
        super(); // Chama o construtor de Planta
    }
    //Parametrizado
    public Salvia(int probReprod, int taxaReprod) {
        super(probReprod, taxaReprod);
    }

    @Override
    public void reprod(List<SeresVivos> novosSeres) {
        Random r = new Random();
        int procriar = r.nextInt(101);
        if (procriar <= probReprod) {
            this.nascerFilhos(novosSeres);
        }
    }
    protected void nascerFilhos(List<SeresVivos> novosSeres) {
        // Aplica a taxa de reprodução: gera o número de filhos definido
        for (int i = 0; i < this.taxaReprod; i++) {
            Pigmeu filhote = new Pigmeu(this.probReprod, this.taxaReprod);
            novosSeres.add(filhote);
        }
        System.out.println("Foram gerados mais " + this.taxaReprod + " coelhos pigmeus");
    }
}
