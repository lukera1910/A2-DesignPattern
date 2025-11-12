package questao_1;

/**
* Implementação dummy de Stress Testing.
*/
public class StressTestingAlgorithm implements RiskAlgorithm {
    @Override
    public RiskResult calculate(RiskContext context) {
        String value = "StressScenarioA=impact: high, loss: 54321.00";
        String details = "Cálculo simplificado de Stress Testing (dummy)";
        return new RiskResult("StressTesting", value, details);
    }


    @Override
    public String name() { return "Stress"; }
}