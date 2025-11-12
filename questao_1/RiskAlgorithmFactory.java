package questao_1;

/**
* Fábrica simples para obter instâncias de algoritmos de risco por nome.
* Decisão de design:
* - Padrão Factory encapsula o conhecimento de construção das implementações.
* - Permite que o cliente peça um algoritmo por nome sem conhecer a classe concreta.
* - Se novas implementações surgirem, adicionamos mapeamento aqui (Open/Closed: extensão sem mudar clientes).
*/
public final class RiskAlgorithmFactory {
    private RiskAlgorithmFactory() { }

    public static RiskAlgorithm of(String name) {
        switch ((name == null) ? "" : name.toLowerCase()) {
            case "var":
            case "vaR":
                return new ValueAtRiskAlgorithm();
            case "es":
            case "expectedshortfall":
                return new ExpectedFallshortAlgorithm();
            case "stress":
            case "stresstesting":
                return new StressTestingAlgorithm();
            default:
                throw new IllegalArgumentException("Algoritmo desconhecido: " + name);
        }
    }
}