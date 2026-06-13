import java.util.Scanner;

public class InputValidation {
    public static int validadeIntBetween(int max, int min, Scanner sc, String mensagem){
        while(true){
            System.out.println(mensagem);
            int in;
            if(sc.hasNextInt()){
                in= sc.nextInt();
                if( in>= min &&  in<= max){
                    return in;
                }
                System.out.println("Selecione uma opcao valida entre"+max+"e"+min);
                sc.nextLine();
            }
            else{
                System.out.println("Valor invalido");
                sc.nextLine();
            }
        }
    }
    public static int validadeIntPos(Scanner sc,String mensagem){
        while(true){
            System.out.println(mensagem);
            int i;
            if(sc.hasNextInt()){
                i= sc.nextInt();
                if(i>=0){
                    return i;
                }
                System.out.println("Valor negativo inserido, deve ser positivo");
                sc.nextLine();
            }
            else {
                System.out.println("Valor Invalido");
                sc.nextLine();
            }
        }
    }
}
