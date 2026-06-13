import java.util.List;

public class RaposaVeloz extends Animal{
    public RaposaVeloz(){
        super();
    }
    public RaposaVeloz(int probReprod, int taxaReprod){
        super(probReprod,taxaReprod);
    }
    @Override
    public void eat(List<SeresVivos> habitatAtual, List<SeresVivos> mortosNestePasso) {
        SeresVivos alimentoEncontrado = null;
        for (SeresVivos ser : habitatAtual) {
            if (ser instanceof Europeu && !mortosNestePasso.contains(ser)) {
                alimentoEncontrado = ser;
                break;
            }
        }
        if (alimentoEncontrado == null) {
            mortosNestePasso.add(this);
            System.out.println("O coelho pigmeu, morreu à fome");
        } else {
            mortosNestePasso.add(alimentoEncontrado);
            System.out.println("Um coelho pigmeu comeu uma erva sálvia");
        }
    }
    @Override
    protected void nascerFilhos(List<SeresVivos> novosSeres) {
        for (int i = 0; i < this.taxaReprod; i++) {
            RaposaVeloz filhote = new RaposaVeloz(this.probReprod, this.taxaReprod);
            novosSeres.add(filhote);
        }
        System.out.println("Foram gerados mais " + this.taxaReprod + " coelhos pigmeus");
    }
}
