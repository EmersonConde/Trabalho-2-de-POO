import java.util.List;

public class Pigmeu extends Animal {

    // Construtores
    public Pigmeu() {
        super(); // Chama o construtor de Animal
    }
    //Parametrizado
    public Pigmeu(int probReprod, int taxaReprod) {
        super(probReprod, taxaReprod);
    }
    @Override
    public void eat(List<SeresVivos> habitatAtual, List<SeresVivos> mortosNestePasso) {
        SeresVivos alimentoEncontrado = null;
        //Procurar por uma Sálvia disponível no habitate
        for (SeresVivos ser : habitatAtual) {
            if (ser instanceof Salvia && !mortosNestePasso.contains(ser)) {
                alimentoEncontrado = ser;
                break; // Encontrou uma planta
            }
        }
        //Aplicar a regra de alimentação
        if (alimentoEncontrado == null) {
            // Se o habitate já não tiver sálvia, este coelho morre
            mortosNestePasso.add(this);
            System.out.println("O coelho pigmeu, morreu à fome");
        } else {
            // Se não, o alimento morre
            mortosNestePasso.add(alimentoEncontrado);
            System.out.println("Um coelho pigmeu comeu uma erva sálvia");
        }
    }
    @Override
    protected void nascerFilhos(List<SeresVivos> novosSeres) {
        // Aplica a taxa de reprodução: gera o número de filhos definido
        for (int i = 0; i < this.taxaReprod; i++) {
            Pigmeu filhote = new Pigmeu(this.probReprod, this.taxaReprod);
            novosSeres.add(filhote);
        }
        System.out.println("Foram gerados mais " + this.taxaReprod + " coelhos pigmeus");
    }

}