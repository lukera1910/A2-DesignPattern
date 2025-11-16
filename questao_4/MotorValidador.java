package questao_4;

import java.util.*;
import java.util.concurrent.*;

/**
* Motor de execução da cadeia de validadores.
* Recursos e decisões de design:
* - Chain of Responsibility configurável (ordem e regras condicionais).
* - Circuit Breaker: interrompe a cadeia após N falhas consecutivas (3 por requisito).
* - Rollback: mantém lista de validadores que modificaram o documento e executa rollback se necessário.
* - Timeout: cada validador é executado em thread separada com limite individual.
* - Condicionais: suporta regra de pular um validador caso outro falhe (configurável).
*/
public class MotorValidador {
    private final List<ValidadorNF> cadeia;
    private final Map<String, Set<String>> regrasPular; // se chave falhar, pula conjunto de nomes
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final int circuitBreakerLimite;
    
    public MotorValidador(List<ValidadorNF> cadeia, Map<String, Set<String>> regrasPular, int circuitBreakerLimite) {
        this.cadeia = cadeia;
        this.regrasPular = regrasPular;
        this.circuitBreakerLimite = circuitBreakerLimite;
    }

    public boolean executar(NFDocumento doc, Map<String, Integer> timeoutsPorValidador) {
        int falhasConsecutivas = 0;
        Set<String> pulados = new HashSet<>();
        List<ValidadorNF> modificadores = new ArrayList<>();

        for (ValidadorNF val : cadeia) {
            if (pulados.contains(val.nome())) {
                System.out.println("[INFO] Pulando validador por regra condicional: " + val.nome());
                continue;
            }

            int timeout = timeoutsPorValidador.getOrDefault(val.nome(), 5);
            Future<Boolean> futuro = executor.submit(() -> {
                try { return val.validar(doc); } catch (ValidadorException e) { throw new ExecutionException(e); }
            });

        try {
            boolean ok = futuro.get(timeout, TimeUnit.SECONDS);
            if (ok) {
                falhasConsecutivas = 0; // reset on success
                // Se validador alterou documento (detectado via rollback implementado), registramos para rollback futuro
                // Aqui assumimos que validador que implementa rollback coloca atributo específico ou tem rollback não-noop
                // Para simplicidade, iremos registrar todos que sobrescreveram atributos conhecidos (heurística)
                // Melhor abordagem: cada validador poderia expor "isModificador()" - omitido por brevidade
                if (doc.getAtributos().containsKey("impostosCalculados") || doc.getAtributos().containsKey("dbInserted")) {
                    modificadores.add(val);
                }
            } else {
                falhasConsecutivas++;
                aplicarRegrasPular(val.nome(), pulados);
            }
        } catch (TimeoutException te) {
            futuro.cancel(true);
            System.out.println("[TIMEOUT] Validador " + val.nome() + " excedeu " + timeout + "s");
            falhasConsecutivas++;
            aplicarRegrasPular(val.nome(), pulados);
        } catch (ExecutionException ee) {
            System.out.println("[ERRO] Validador " + val.nome() + " falhou: " + ee.getCause().getMessage());
            falhasConsecutivas++;
            aplicarRegrasPular(val.nome(), pulados);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            System.out.println("[INTERRUPTED] Execução interrompida");
            falhasConsecutivas++;
            aplicarRegrasPular(val.nome(), pulados);
        }


        if (falhasConsecutivas >= circuitBreakerLimite) {
            System.out.println("[CIRCUIT BREAKER] Limite de falhas atingido (" + circuitBreakerLimite + ") — interrompendo cadeia.");
            // Executa rollback dos modificadores em ordem reversa
            for (int i = modificadores.size() - 1; i >= 0; i--) {
                try { modificadores.get(i).rollback(doc); } catch (Exception e) { System.out.println("[ROLLBACK ERRO] " + e.getMessage()); }
            }
            return false;
        }
    }

    // Se completou a cadeia com falhas abaixo do limite, verifica se houve alguma
    // falha que exige rollback do DB (validador 4)
        boolean houveFalha = falhasConsecutivas > 0;
        if (houveFalha) {
            for (int i = modificadores.size() - 1; i >= 0; i--) {
                try {
                    modificadores.get(i).rollback(doc);
                } catch (Exception e) {
                    System.out.println("[ROLLBACK ERRO] " + e.getMessage());
                }
            }
            return false;
        }

        return true;
    }

    private void aplicarRegrasPular(String nomeFalha, Set<String> pulados) {
        Set<String> alvo = regrasPular.get(nomeFalha);
        if (alvo != null)
            pulados.addAll(alvo);
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
