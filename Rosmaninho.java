import java.util.List;
import java.util.Random;

public class Rosmaninho extends Planta{

    public Rosmaninho() {
        super();
    }
    public Rosmaninho(int probReprod, int taxaReprod) {
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
    @Override
    protected void nascerFilhos(List<SeresVivos> novosSeres) {
        for (int i = 0; i < this.taxaReprod; i++) {
            Rosmaninho filhote = new Rosmaninho(this.probReprod, this.taxaReprod);
            novosSeres.add(filhote);
        }
        System.out.println("Foram gerados mais " + this.taxaReprod + " coelhos pigmeus");
    }
}
