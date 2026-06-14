import java.util.List;

public class RaposasVerm extends Animal{

    public RaposasVerm(){
        super();
    }
    public RaposasVerm(int probReprod,int taxaReprod){
        super(probReprod,taxaReprod);
    }

    @Override
    public void eat(List<SeresVivos> habitatAtual, List<SeresVivos> mortosNestePasso) {
        SeresVivos alimentoEncontrado = null;
        for (SeresVivos ser : habitatAtual) {
            if (ser instanceof Pigmeu && !mortosNestePasso.contains(ser)) {
                alimentoEncontrado = ser;
                break;
            }
        }
        if (alimentoEncontrado == null) {
            mortosNestePasso.add(this);
            System.out.println("Uma raposa vermelha, morreu à fome");
        } else {
            mortosNestePasso.add(alimentoEncontrado);
            System.out.println("Uma Raposa vermelha comeu um coelho pigmeu ");
        }
    }
    @Override
    protected void nascerFilhos(List<SeresVivos> novosSeres) {
        for (int i = 0; i < this.taxaReprod; i++) {
            RaposasVerm filhote = new RaposasVerm(this.probReprod, this.taxaReprod);
            novosSeres.add(filhote);
        }
        System.out.println("Foram gerados mais " + this.taxaReprod + " raposas vermelhas");
    }
}
