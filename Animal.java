import java.util.List;
import java.util.Random;
abstract public class Animal implements SeresVivos{
    //Campos protegidos para todos os animais
    protected int probReprod;
    protected int taxaReprod;
    protected boolean bornStep;
    public Animal(){
        probReprod=0;
        taxaReprod=0;
        bornStep=true;
    }
    public Animal( int probReprod, int taxaReprod){
        this.probReprod=probReprod;
        this.taxaReprod=taxaReprod;
        this.bornStep=true;
    }
    //Getters e setters essenciais
    @Override
    public boolean isBornStep() {
        return bornStep;
    }
    @Override
    public int getProbRep() {return this.probReprod;}
    @Override
    public int getTaxRep() {return this.taxaReprod;}
    @Override
    public void setBornStep(boolean bornStep) {
        this.bornStep = bornStep;
    }

    //Métodos que os animais irão herdar
    public abstract void eat(List<SeresVivos> habitatAtual, List<SeresVivos> mortosNestePasso);
    public void reprod(List<SeresVivos> novosSeres) {
        Random r = new Random();
        int procriar = r.nextInt(101); // Permite gerar de 0 a 100 inclusive
        if (procriar <= probReprod) { // Regra exata do enunciado!
            // Como cada espécie gera filhos de si mesma, a instanciação
            // real dos filhos será feita através de um método polimórfico
            // ou subcarregado nas subclasses.
            this.nascerFilhos(novosSeres);
        }
    }
    // Método auxiliar abstrato para que cada animal saiba como criar cópias de si mesmo
    protected abstract void nascerFilhos(List<SeresVivos> novosSeres);
    @Override
    public void die() {
        System.out.println(this.getClass().getSimpleName() + " morreu.");
    }

}
