import java.util.List;

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
    //Métodos que os animais irão herdar
    public abstract void eat(List<SeresVivos> habitatAtual, List<SeresVivos> mortosNestePasso);
    public void reprod(){
        System.out.println("Reproduziu");
    };
    public void die(){
        System.out.println("Morreu");
    };
}
