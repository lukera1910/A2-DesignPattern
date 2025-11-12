package questao_1;

/**
* RiskProcessor é o componente usado pelo cliente para executar cálculos.
* Ele mantém uma estratégia (RiskAlgorithm) que pode ser trocada em tempo de execução.
*
* Decisão de design:
* - Aplica o padrão Strategy: RiskProcessor delega o cálculo para a estratégia atual.
* - Possui método setAlgorithm para permitir troca dinâmica (cumprindo requisito do cliente).
* - Segue princípio de responsabilidade única: coordena execução, não realiza cálculos.
*/
public class RiskProcessor {
    private RiskAlgorithm algorithm;

    public RiskProcessor(RiskAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setAlgorithm(RiskAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public RiskResult process(RiskContext context) {
        if (algorithm == null)
            throw new IllegalStateException("Nenhum algoritmo definido");
        return algorithm.calculate(context);
    }

    public String currentAlgorithmName() {
        return (algorithm == null) ? "<nenhum>" : algorithm.name();
    }
}
