import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Classe principal que gere a interação com o utilizador e o ciclo da simulação.
 * Conforme exigido, utiliza tratamento de exceções para garantir a robustez [2].
 */
public class Main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Simulador simulador = new Simulador();

        System.out.println("========== SIMULADOR DE HABITAT ==========");
        // 1. Configuração Inicial: Escolha entre valores predefinidos ou novos [1]
        boolean configurado = false;
        while (!configurado) {
            try {
                System.out.print("Deseja utilizar valores predefinidos (P) ou inserir novos parâmetros (N)? ");
                String opcao = leitor.next().toUpperCase();

                if (opcao.equals("N")) {
                    configurarHabitatManual(simulador, leitor);
                    configurado = true;
                } else if (opcao.equals("P")) {
                    simulador.configurarValoresPredefinidos(); // Carrega os valores internos [1]
                    configurado = true;
                } else {
                    System.out.println("Erro: Opção inválida. Escolha 'P' ou 'N'.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro na configuração. Tente novamente.");
                leitor.nextLine(); // Limpa o buffer
            }
        }

        // 2. Ciclo de Execução da Simulação [2, 3]
        boolean continuarPrograma = true;
        while (continuarPrograma) {
            // Apresenta o estado atual antes de pedir os passos [3]
            simulador.apresentarResultados();

            int passos = pedirInteiro(leitor, "\nQuantos passos deseja executar de seguida (0 para sair): ");

            if (passos <= 0) {
                continuarPrograma = false;
            } else {
                // Executa os passos e verifica se houve interrupção por extinção ou limite [2, 3]
                boolean interrompido = simulador.executarSimulacao(passos);

                if (interrompido) {
                    System.out.println("\n--- SIMULAÇÃO INTERROMPIDA POR CONDIÇÕES DO HABITAT ---");
                    simulador.apresentarResultados();
                    continuarPrograma = false;
                } else {
                    // Após concluir os passos, pergunta se deseja continuar ou sair [2]
                    System.out.print("\nDeseja executar mais passos da simulação (S/N)? ");
                    String resposta = leitor.next().toUpperCase();
                    if (resposta.equals("N")) {
                        continuarPrograma = false;
                    }
                }
            }
        }

        System.out.println("\nSimulação terminada.");
        simulador.apresentarResultados(); // Resultado final [3]
        leitor.close();
    }

    /**
     * Método para configurar manualmente os parâmetros de cada uma das 6 espécies [1, 4].
     */
    private static void configurarHabitatManual(Simulador simulador, Scanner leitor) {
        String[] especies = {
                "Sálvia (Planta)", "Rosmaninho (Planta)",
                "Coelho Pigmeu", "Coelho Europeu",
                "Raposa Vermelha", "Raposa Veloz"
        };

        for (String nome : especies) {
            System.out.println("\n--- Parâmetros para: " + nome + " ---");
            int qtd = pedirInteiro(leitor, "Número inicial de exemplares: ");
            int prob = pedirIntervalo(leitor, "Probabilidade de reprodução (0-100): ", 0, 100);
            int taxa = pedirInteiro(leitor, "Taxa de reprodução (filhos por geração): ");

            simulador.adicionarEspecie(nome, qtd, prob, taxa); // Método no Simulador para criar as instâncias [1]
        }
    }

    /**
     * Auxiliar para garantir que a entrada seja um número inteiro válido (Tratamento de Exceções) [2].
     */
    private static int pedirInteiro(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, insira um número inteiro válido.");
                sc.nextLine(); // Limpa buffer
            }
        }
    }

    /**
     * Auxiliar para validar intervalos, como a probabilidade de 0 a 100% [2, 5].
     */
    private static int pedirIntervalo(Scanner sc, String mensagem, int min, int max) {
        while (true) {
            int valor = pedirInteiro(sc, mensagem);
            if (valor >= min && valor <= max) return valor;
            System.out.println("Erro: O valor deve estar entre " + min + " e " + max + ".");
        }
    }
}