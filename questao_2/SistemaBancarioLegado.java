package questao_2;

import java.util.HashMap;
import java.util.Map;

public class SistemaBancarioLegado {
    public Map<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        // Simulação de processamento com base nos parâmetros legados
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", "OK");
        resposta.put("codigoAutorizacao", "LEG-" + Math.round(Math.random() * 1000));
        resposta.put("valorProcessado", parametros.get("valor"));
        resposta.put("moedaCodigo", parametros.get("moedaCodigo"));
        resposta.put("mensagem", "Transação processada pelo sistema legado.");
        return resposta;
    }
}
