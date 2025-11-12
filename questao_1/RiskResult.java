package questao_1;

/**
 * RiskResult encapsula o resultado de um cálculo de risco
 * Decisão de design:
 * - Classe simples para transportar dados; permite extensão futura
 *   (ex: incluir métricas detalhadas) sem quebrar consumidores.
 */

public final class RiskResult {
    private final String metricName;
    private final String value; // dummy: poderia ser número, JSON, etc.
    private final String details;

    public RiskResult(String metricName, String value, String details) {
        this.metricName = metricName;
        this.value = value;
        this.details = details;
    }


    public String getMetricName() { return metricName; }
    public String getValue() { return value; }
    public String getDetails() { return details; }

    @Override
    public String toString() {
        return String.format("%s: %s -- %s", metricName, value, details);
    }
}
