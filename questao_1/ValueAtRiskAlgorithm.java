package questao_1;

/**
* Implementação dummy de Value at Risk.
* Decisão de design: mantém responsabilidade única (cálculo de VaR)
* e pode ser estendida sem alterar a interface.
*/
public class ValueAtRiskAlgorithm implements RiskAlgorithm {
    @Override
    public RiskResult calculate(RiskContext context) {
        // Cálculo dummy: apenas uma mensagem simulando um número
        String value = String.format("VaR(%.2f)=%.2f", context.getConfidenceLevel(), 12345.67);
        String details = "Cálculo simplificado de Value at Risk (dummy)";
        return new RiskResult("ValueAtRisk", value, details);
    }

    @Override
    public String name() { return "VaR"; }
}