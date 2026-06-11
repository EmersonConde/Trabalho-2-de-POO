public interface SeresVivos {
    // Métodos de ação exigidos pelo ciclo do habitat
    void reprod(java.util.List<SeresVivos> novosSeres);
    void die();

    // Controlo de nascimento (Regra: não pode agir no mesmo passo em que nasce)
    boolean isBornStep();
    void setBornStep(boolean bornStep);
}