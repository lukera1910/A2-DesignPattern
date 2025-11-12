package questao_2;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter que converte chamadas da interface moderna para o sistema legado e vice-versa
 * 
 * Decisões de design:
 * - Aplicação do padrão Adapter, permitindo compatibilidade bidirecional
 * - Implementa ProcessadorTransacoes para se comportar como a interface moderna
 * - Internamente converte parâmetros modernos para o formato HashMap exigido pelo legado
 * - Realiza tratamento de campos obrigatórios exigidos pelo legado
 * - Segue SOLID:
 *   - SRP: converte apenas formatos de dados
 *   - OCP: pode adicionar novas conversões de moedas sem alterar o legado
 *   - DIP: depende de abstração, injetável
 */

public class SistemaBancarioAdapter implements ProcessadorTransacoes {
    private final SistemaBancarioLegado sistemaLegado;

    public SistemaBancarioAdapter(SistemaBancarioLegado sistemaLegado) {
        this.sistemaLegado = sistemaLegado;
    }

    @Override
    public RespostaTransacao autorizar(String cartao, double valor, String moeda) {
        // Converte a requisição moderna em formato legado
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cartao", cartao);
        parametros.put("valor", valor);
        parametros.put("moedaCodigo", mapearMoedaParaCodigo(moeda));

        // Campo obrigatório exigido pelo legado, mas não presente na interface moderna
        parametros.put("codigoBanco", "001"); // Exemplo: Banco padrão exigido pelo legado

        // Chama o sistema legado
        Map<String, Object> respostaLegado = sistemaLegado.processarTransacao(parametros);

        // Converte a resposta para o formato moderno
        return converterResposta(respostaLegado);
    }

    private RespostaTransacao converterResposta(Map<String, Object> respostaLegado) {
        String status = (String) respostaLegado.getOrDefault("status", "ERRO");
        String codigo = (String) respostaLegado.getOrDefault("codigoAutorizacao", "N/A");
        double valor = ((Number) respostaLegado.getOrDefault("valorProcessado", 0)).doubleValue();
        String moeda = mapearCodigoParaMoeda((int) respostaLegado.getOrDefault("moedaCodigo", 0));
        String msg = (String) respostaLegado.getOrDefault("mensagem", "Sem mensagem");

        return new RespostaTransacao(status, codigo, valor, moeda, msg);
    }

    private int mapearMoedaParaCodigo(String moeda) {
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: throw new IllegalArgumentException("Moeda não suportada: " + moeda); 
        }
    }

    private String mapearCodigoParaMoeda(int codigo) {
        switch (codigo) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "BRL";
            default: return "DESCONHECIDA";
        }
    }
}
