public class Planta implements SeresVivos{
    //Campos protegidos para todas as plantinhas
    protected int probReprod;
    protected int taxaReprod;
    protected boolean bornStep;

    //Métodos que as plantas irão herdar
    public void reprod(){
        System.out.println("Polinizou");
    };
    public void die(){
        System.out.println("Morreu");
    };
}
