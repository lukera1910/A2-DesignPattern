package questao_1;

import java.util.HashMap;
import java.util.Map;

/**
 * Exemplo de uso mostrando troca de algoritmo em tempo de execução.
 * O cliente utiliza apenas RiskProcessor e RiskAlgorithmFactory, sem conhecer
 * detalhes das implementações (princípio de encapsulamento e DIP).
 */
public class Main {
    public static void main(String[] args) {
        // Prepara contexto financeiro (dummy)
        Map<String, Double> market = new HashMap<>();
        market.put("USD/BRL", 5.12);
        market.put("VIX", 22.5);

        Map<String, Double> positions = new HashMap<>();
        positions.put("AAPL", 100.0);
        positions.put("BOVA11", 200.0);

        RiskContext context = new RiskContext.Builder()
                .withMarketData(market)
                .withPositions(positions)
                .withConfidenceLevel(0.99)
                .withPortfolioId("PORT-123")
                .build();

        // Cliente cria processor com algoritmo inicial (obtido pela fábrica)
        var varAlg = RiskAlgorithmFactory.of("VaR");
        var processor = new RiskProcessor(varAlg);

        System.out.println("Algoritmo atual: " + processor.currentAlgorithmName());
        RiskResult r1 = processor.process(context);
        System.out.println(r1);

        // Troca para Expected Shortfall em tempo de execução
        var esAlg = RiskAlgorithmFactory.of("ES");
        processor.setAlgorithm(esAlg);
        System.out.println("Algoritmo atual: " + processor.currentAlgorithmName());
        RiskResult r2 = processor.process(context);
        System.out.println(r2);

        // Troca para Stress Testing
        var stressAlg = RiskAlgorithmFactory.of("Stress");
        processor.setAlgorithm(stressAlg);
        System.out.println("Algoritmo atual: " + processor.currentAlgorithmName());
        RiskResult r3 = processor.process(context);
        System.out.println(r3);
    }
}
