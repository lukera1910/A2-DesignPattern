package questao_1;

import java.util.Map;

/**
 * Risk Context representa um contexto complexo de parâmetros financeiros
 * usado pelos algoritmos de risco.
 * 
 * Decisão de design:
 * - Usei o padrão Builder para construir um objeto imutável RiskContext.
 *   Isso segue o princípio SOLID de Single Responsibility (a contrução 
 *   fica encapsulada no Builder) e facilita a extensão (Open/Closed)
 *   caso mais parâmetros sejam adicionados.
 * - Tornar Risk Context imutável evita efeitos colaterais durante cálculos concorrentes.
 */

public final class RiskContext {
    private final Map<String, Double> marketData; // ex: taxas, volatilidades
    private final Map<String, Double> positions; // ex: posição por ativo
    private final double confidenceLevel;
    private final String portfolioId;

    private RiskContext(Builder b) {
        this.marketData = b.marketData;
        this.positions = b.positions;
        this.confidenceLevel = b.confidenceLevel;
        this.portfolioId = b.portfolioId;
    }

    public Map<String, Double> getMarketData() { return marketData; }
    public Map<String, Double> getPositions() { return positions; }
    public double getConfidenceLevel() { return confidenceLevel; }
    public String getPortfolioId() { return portfolioId; }

    public static class Builder {
        private Map<String, Double> marketData;
        private Map<String, Double> positions;
        private double confidenceLevel = 0.95; // default
        private String portfolioId = "default";


        public Builder withMarketData(Map<String, Double> marketData) { this.marketData = marketData; return this; }
        public Builder withPositions(Map<String, Double> positions) { this.positions = positions; return this; }
        public Builder withConfidenceLevel(double confidenceLevel) { this.confidenceLevel = confidenceLevel; return this; }
        public Builder withPortfolioId(String portfolioId) { this.portfolioId = portfolioId; return this; }
        public RiskContext build() { return new RiskContext(this); }
    }
}
