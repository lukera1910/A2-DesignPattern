package questao_1;

/**
 * Implementação dummy do Expected Shortfall (ES).
 */

public class ExpectedFallshortAlgorithm implements RiskAlgorithm {
    @Override
    public RiskResult calculate(RiskContext context) {
        String value = String.format("ES(%.2f)=%.2f", context.getConfidenceLevel(), 23456.78);
        String details = "Cálculo simplificado de Expected Shortfall (dummy)";
        return new RiskResult("ExpectedShortfall", value, details);
    }

    @Override
    public String name() { return "ES";} 
}
