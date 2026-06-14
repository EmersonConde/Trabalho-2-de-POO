import java.util.List;

public class Europeu extends Animal{
    public Europeu() {
        super();
    }
    public Europeu(int probReprod, int taxaReprod) {
        super(probReprod, taxaReprod);
    }
    @Override
    public void eat(List<SeresVivos> habitatAtual, List<SeresVivos> mortosNestePasso) {
        SeresVivos alimentoEncontrado = null;
        //Procurar por um rosmaninho disponível no habitate
        for (SeresVivos ser : habitatAtual) {
            if (ser instanceof Rosmaninho && !mortosNestePasso.contains(ser)) {
                alimentoEncontrado = ser;
                break; // Encontrou uma planta
            }
        }
        if (alimentoEncontrado == null) {
            mortosNestePasso.add(this);
            System.out.println("Um coelho europeu, morreu à fome");
        } else {
            mortosNestePasso.add(alimentoEncontrado);
            System.out.println("Um coelho europeu comeu um rosmaninho");
        }
    }
    @Override
    protected void nascerFilhos(List<SeresVivos> novosSeres) {
        for (int i = 0; i < this.taxaReprod; i++) {
            Europeu filhote = new Europeu(this.probReprod, this.taxaReprod);
            novosSeres.add(filhote);
        }
        System.out.println("Foram gerados mais " + this.taxaReprod + " coelhos europeus");
    }
}
