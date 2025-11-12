package questao_1;

/**
* Interface que define um algoritmo de risco. Aplicação do padrão
* Strategy: permite trocar a implementação em tempo de execução.
*
* Decisão de design:
* - Interface simples (segregada) cumpre o princípio ISP (Interface Segregation).
* - Clientes dependem da abstração (Dependency Inversion).
*/
public interface RiskAlgorithm {
    /**
     * Executa o cálculo de risco usando o contexto fornecido.
     * 
     * @param context contexto financeiro com parâmetros
     * @return resultado do cálculo
     */
    RiskResult calculate(RiskContext context);

    /**
     * Nome do algoritmo (útil para fábricas/registro)
     */
    String name();
}