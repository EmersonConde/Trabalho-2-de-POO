import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

ArrayList<SeresVivos> habitat = new ArrayList<>();
int[] numExArray = {5, 5, 3, 3, 1, 1};

void main() {
    Scanner sc = new Scanner(System.in);
    System.out.println("- Darwin Forest -");
    boolean preDef = true;

    int option = -1, steps, probRep = 50, taxRep = 1;

    while (option != 0) {
        int especie;
        especie();
        System.out.println("Pretendes começar com valores predefinidos ou inseri-los? 1-Inserir | 2-Continuar");
        option = InputValidation.validadeIntBetween(2, 1, sc, "Escolha das opcoes acima: ");
        if (option == 1) {
            preDef = false;
        }
        if (!preDef) {
            for (especie = 0; especie < 6; especie++) {
                try {
                    int exemplares = InputValidation.validadeIntBetween(20, 1, sc, "Qual o valor exemplar da especie " + (especie + 1) + "?");
                    int prob = InputValidation.validadeIntBetween(100, 0, sc, "Insira um valor entre 100 e 0 para probabilidade de reproducao para a especie " + (especie + 1));
                    int taxa = InputValidation.validadeIntBetween(10, 1, sc, "Insira uma taxa de reproducao para a especie " + (especie + 1));

                    iniciarNotPreDef(exemplares, habitat, especie, prob, taxa);
                } catch (ValorInvalidoException e) {
                    System.out.println(e.getMessage() + " Reiniciando o valor da especie");
                    especie--;
                }
            }
        } else {
            iniciarPreDef(habitat, probRep, taxRep);
        }
        option = 0;
        mostrarPop(habitat);
    }

    option = -1;
    while (option != 0) {
        menuHabitat();
        try {
            option = InputValidation.validadeIntBetween(2, 0, sc, "Escolha uma opcao: ");
            switch (option) {
                case 1:
                    System.out.println("Quantos passos desejas simular?");
                    steps = InputValidation.validadeIntBetween(100, 1, sc, "Insira o numero de passos que pretendes simular, entre 100 e 1");
                    int passos = 1;
                    while (steps != 0) {
                        simulateStep(habitat, passos);
                        if (extincao(habitat) || superPop(habitat)) {
                            System.out.println("Uma especie foi extinta ou houve super população. Terminando simulacao");
                            option = 0;
                            break;
                        }
                        passos++;
                        steps--;
                    }
                    break;
                case 2:
                    mostrarPop(habitat);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}

void especie() {
    System.out.println("Especie 1. Salvia");
    System.out.println("Especie 2. Rosmaninho");
    System.out.println("Especie 3. Coelho pigmeu");
    System.out.println("Especie 4. Coelho europeu");
    System.out.println("Especie 5. Raposas vermelhas");
    System.out.println("Especie 6. Raposas velozes");
}

void menuHabitat() {
    System.out.println("1-Simular");
    System.out.println("2-Mostrar Estado do Habitat");
    System.out.println("0 - Terminar a simulação");
}

void mostrarPop(List<SeresVivos> habitat) {
    int[] contagem = contarEspecies(habitat);
    SeresVivos s1 = new Salvia(), s2 = new Rosmaninho(), s3 = new Pigmeu(), s4 = new Europeu(), s5 = new RaposasVerm(), s6 = new RaposaVeloz();

    for (SeresVivos ser : habitat) {
        if (ser instanceof Salvia) s1 = ser;
        else if (ser instanceof Rosmaninho) s2 = ser;
        else if (ser instanceof Pigmeu) s3 = ser;
        else if (ser instanceof Europeu) s4 = ser;
        else if (ser instanceof RaposasVerm) s5 = ser;
        else if (ser instanceof RaposaVeloz) s6 = ser;
    }

    System.out.println("Ervas sálvia: " + contagem[0] + "( " + s1.getProbRep() + "," + s1.getTaxRep() + " )");
    System.out.println("Rosmaninhos: " + contagem[1] + "( " + s2.getProbRep() + "," + s2.getTaxRep() + " )");
    System.out.println("Coelho pigmeu: " + contagem[2] + "( " + s3.getProbRep() + "," + s3.getTaxRep() + " )");
    System.out.println("Coelho Europeu: " + contagem[3] + "( " + s4.getProbRep() + "," + s4.getTaxRep() + " )");
    System.out.println("Raposa Vermelha: " + contagem[4] + "( " + s5.getProbRep() + "," + s5.getTaxRep() + " )");
    System.out.println("Raposa Veloz: " + contagem[5] + "( " + s6.getProbRep() + "," + s6.getTaxRep() + " )");
}


int[] contarEspecies(List<SeresVivos> habitat) {
    int[] contagem = new int[6];
    for (SeresVivos ser : habitat) {
        if (ser instanceof Salvia) contagem[0]++;
        else if (ser instanceof Rosmaninho) contagem[1]++;
        else if (ser instanceof Pigmeu) contagem[2]++;
        else if (ser instanceof Europeu) contagem[3]++;
        else if (ser instanceof RaposasVerm) contagem[4]++;
        else if (ser instanceof RaposaVeloz) contagem[5]++;
    }
    return contagem;
}

boolean extincao(List<SeresVivos> habitat) {
    int[] contagem = contarEspecies(habitat);
    return contagem[0] == 0 || contagem[1] == 0 || contagem[2] == 0 || contagem[3] == 0 || contagem[4] == 0 || contagem[5] == 0;
}


void simulateStep(List<SeresVivos> habitat, int passos) {
    System.out.println("Passo " + passos);
    ArrayList<SeresVivos> mortosNestePasso = new ArrayList<>();
    ArrayList<SeresVivos> novosSeres = new ArrayList<>();


    for (SeresVivos ser : habitat) {
        if (!(ser.isBornStep()) && !(ser instanceof Planta)) {
            Animal animal = (Animal) ser;
            animal.eat(habitat, mortosNestePasso);
        }
    }


    for (SeresVivos ser : habitat) {
        if (mortosNestePasso.contains(ser))
            ser.die();
    }

    habitat.removeAll(mortosNestePasso);

    for (SeresVivos ser : habitat) {
        if (!(ser.isBornStep()))
            ser.reprod(novosSeres);
    }
    habitat.addAll(novosSeres);

    for (SeresVivos ser : habitat) {
        ser.setBornStep(false);
    }
}

void iniciarPreDef(List<SeresVivos> habitat, int probRep, int taxRep) {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < numExArray[i]; j++) {
            SeresVivos p;
            switch (i) {
                case 0 -> p = new Salvia(probRep, taxRep);
                case 1 -> p = new Rosmaninho(probRep, taxRep);
                case 2 -> p = new Pigmeu(probRep, taxRep);
                case 3 -> p = new Europeu(probRep, taxRep);
                case 4 -> p = new RaposasVerm(probRep, taxRep);
                default -> p = new RaposaVeloz(probRep, taxRep);
            }
            habitat.add(p);
            p.setBornStep(false);
        }
    }
}

void iniciarNotPreDef(int numEx, List<SeresVivos> habitat, int especie, int probRep, int taxRep) {
    if (especie >= 0 && especie < 6) {
        numExArray[especie] = numEx;
    }

    for (int j = 0; j < numEx; j++) {
        SeresVivos p;
        switch (especie) {
            case 0 -> p = new Salvia(probRep, taxRep);
            case 1 -> p = new Rosmaninho(probRep, taxRep);
            case 2 -> p = new Pigmeu(probRep, taxRep);
            case 3 -> p = new Europeu(probRep, taxRep);
            case 4 -> p = new RaposasVerm(probRep, taxRep);
            default -> p = new RaposaVeloz(probRep, taxRep);
        }
        habitat.add(p);
        p.setBornStep(false);
    }
}

boolean superPop(List<SeresVivos> habitat) {
    int[] contagem = contarEspecies(habitat);
    return contagem[0] > numExArray[0] * 2
            || contagem[1] > numExArray[1] * 2
            || contagem[2] > numExArray[2] * 2
            || contagem[3] > numExArray[3] * 2
            || contagem[4] > numExArray[4] * 3
            || contagem[5] > numExArray[5] * 3;
}