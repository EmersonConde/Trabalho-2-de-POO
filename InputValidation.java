import java.util.Scanner;

public class InputValidation {
    public static int validadeIntBetween(int max, int min, Scanner sc, String mensagem) throws ValorInvalidoException {
        System.out.println(mensagem);
        if (!sc.hasNextInt()) {
            sc.nextLine(); // Limpa o buffer de lixo que o utilizador digitou
            throw new ValorInvalidoException("Erro: O valor inserido não é um número inteiro válido!");
        }
        int in = sc.nextInt();
        sc.nextLine();
        if (in < min || in > max) {
            throw new ValorInvalidoException("Erro: O número " + in + " está fora dos limites permitidos [" + min + " e " + max + "]!");
        }
        return in;
    }
}
