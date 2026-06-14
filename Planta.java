import java.util.List;
import java.util.Random;

abstract public class Planta implements SeresVivos{
    //Campos protegidos para todas as plantinhas
    protected int probReprod;
    protected int taxaReprod;
    protected boolean bornStep;

    public Planta(){
        probReprod=0;
        taxaReprod=0;
        bornStep=true;
    }
    public Planta( int probReprod, int taxaReprod){
        this.probReprod=probReprod;
        this.taxaReprod=taxaReprod;
        this.bornStep=true;
    }

    //Métodos que as plantas irão herdar
    public void reprod(List<SeresVivos> novosSeres) {
        Random r = new Random();
        int procriar = r.nextInt(101);
        if (procriar <= probReprod) {
            this.nascerFilhos(novosSeres);
        }
    }
    protected abstract void nascerFilhos(List<SeresVivos> novosSeres);
    @Override
    public void die() {
        System.out.println(this.getClass().getSimpleName() + " morreu.");
    }
    @Override
    public boolean isBornStep() {
        return bornStep;
    }
    @Override
    public void setBornStep(boolean bornStep) {
        this.bornStep=bornStep;
    }
    @Override
    public int getProbRep() {return this.probReprod;}
    @Override
    public int getTaxRep() {return this.taxaReprod;}
}
