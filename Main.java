import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    static ArrayList<SeresVivos> habitat=new ArrayList<>();
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("- Darwin Forest -");
        boolean preDef=true;

        int option=-1,numEx=3,probRep=50,taxRep=1;
        while(option!=0){
            int especie=0;
            System.out.println("Pretendes começar com valores predefinidos ou inseri-los? 1-Inserir | 2-Continuar");
            option=InputValidation.validadeIntBetween(2,1,sc,"Escolha das opcoes acima: ");
            if(option==1){preDef=false;}
            else{preDef=true;}
            if(!preDef){
                    for(especie=0;especie<6;especie++)
                        try{
                        iniciarNotPreDef(
                                InputValidation.validadeIntBetween(20,1,sc,"Qual o valor exemplar da especie"+especie+"?")
                                , habitat
                                , especie
                                ,InputValidation.validadeIntBetween(100,0,sc,"Insira um valor entre 100 e 0 para probabilidade de reproducao para a especie"+especie)
                                ,InputValidation.validadeIntBetween(10,1,sc,"Insira uma taxa de reproducao para a especie"+especie)
                        );
                        option=0;
                        }catch (ValorInvalidoException e) {
                            System.out.println(e.getMessage()+" Reinciciando o valor da especie");
                            especie--;
                        }
                }
            else{
                iniciarPreDef(numEx, habitat,probRep,taxRep);
                option=0;
            }
        }
        option=-1;
        while(option!=0){
            menuHabitat();
            option=InputValidation.validadeIntBetween(2,1,sc,"Escolha uma opcao: ");
            switch (option){
                case 1:
                    simulateStep(habitat);
                    break;
                case 2:
                    mostrarPop(habitat);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
    }
    public static void menuHabitat(){
        System.out.println("1-Avançar 1 Geração");
        System.out.println("2-Mostrar Estado do Habitat");
        System.out.println("0 - Terminar a simulação");
    }
    public static void mostrarPop(List<SeresVivos> habitat){
        int salvia=0, rosmaninho=0, pigmeu=0,europeu=0,raposaVeloz=0,raposaVerm=0;
        for(SeresVivos ser : habitat){
            if(ser instanceof Salvia){
                salvia++;
            }
            if(ser instanceof Rosmaninho){
                rosmaninho++;
            }
            if(ser instanceof Pigmeu){
                pigmeu++;
            }
            if(ser instanceof Europeu){
                europeu++;
            }
            if(ser instanceof RaposasVerm){
                raposaVerm++;
            }
            if(ser instanceof RaposaVeloz){
                raposaVeloz++;
            }
        }
        System.out.println("Existem "+salvia+" salvias");
        System.out.println("Existem "+rosmaninho+" Rosmaninhos");
        System.out.println("Existem "+pigmeu+" Coelhos pigmeus");
        System.out.println("Existem "+europeu+" Coelhos europeus");
        System.out.println("Existem "+raposaVerm+" Raposas vermelhas");
        System.out.println("Existem "+raposaVeloz+" Raposas velozes");
    }
    public static void simulateStep(List<SeresVivos> habitat){
        ArrayList<SeresVivos> mortosNestePasso = new ArrayList<>();
        ArrayList<SeresVivos> novosSeres = new ArrayList<>();
        for(SeresVivos ser:habitat){
            if(!(ser.isBornStep()) && !(ser instanceof Planta)){
                Animal animal= (Animal) ser;
                animal.eat(habitat,mortosNestePasso);
            }
        }
        habitat.removeAll(mortosNestePasso);
        for(SeresVivos ser:habitat){
            ser.reprod(novosSeres);
        }
        habitat.addAll(novosSeres);
    }
    public static void iniciarPreDef(int numEx, List<SeresVivos> habitat, int probRep,int taxRep) {
        SeresVivos p = null;
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                for (int j = 0; j < numEx; j++) {
                    p = new Salvia(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (i == 1) {
                for (int j = 0; j < numEx; j++) {
                    p = new Rosmaninho(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (i == 2) {
                for (int j = 0; j < numEx; j++) {
                    p = new Pigmeu(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (i == 3) {
                for (int j = 0; j < numEx; j++) {
                    p = new Europeu(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (i == 4) {
                for (int j = 0; j < numEx; j++) {
                    p = new RaposasVerm(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (i == 5) {
                for (int j = 0; j < numEx; j++) {
                    p = new RaposaVeloz(probRep,taxRep);
                    habitat.add(p);
                }
            }
        }
    }
    public static void iniciarNotPreDef(int numEx, List<SeresVivos> habitat,int especie,int probRep,int taxRep) {
        SeresVivos p = null;
            if (especie == 0) {
                for (int j = 0; j < numEx; j++) {
                    p = new Salvia(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (especie == 1) {
                for (int j = 0; j < numEx; j++) {
                    p = new Rosmaninho(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (especie == 2) {
                for (int j = 0; j < numEx; j++) {
                    p = new Pigmeu(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (especie == 3) {
                for (int j = 0; j < numEx; j++) {
                    p = new Europeu(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (especie == 4) {
                for (int j = 0; j < numEx; j++) {
                    p = new RaposasVerm(probRep,taxRep);
                    habitat.add(p);
                }
            }
            if (especie == 5) {
                for (int j = 0; j < numEx; j++) {
                    p = new RaposaVeloz(probRep,taxRep);
                    habitat.add(p);
                }
            }
    }
}