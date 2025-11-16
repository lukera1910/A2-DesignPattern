package questao_4;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        NFDocumento doc = new NFDocumento("<NFe>...</NFe>");
        doc.getAtributos().put("certExpiry", LocalDate.now().plusDays(10));
        doc.getAtributos().put("numero", "1234562"); // termina com dígito par -> SEFAZ aceita

        InMemoryNFDatabase db = new InMemoryNFDatabase();

        List list = Arrays.asList(
                new ValidadorSchema(2),
                new ValidadorCertificado(2),
                new ValidadorRegrasFiscais(3),
                new ValidadorBancoDados(db, 2),
                new ValidadorSeFaz(4));

        // Regras condicionais: se Schema falhar, pula SEFAZ; se Certificado falhar pula
        // RegrasFiscais
        Map<String, Set<String>> regrasPular = new HashMap<>();
        regrasPular.put("Schema", new HashSet<>(Arrays.asList("SEFAZ")));
        regrasPular.put("Certificado", new HashSet<>(Arrays.asList("RegrasFiscais")));

        MotorValidador engine = new MotorValidador(list, regrasPular, 3);

        Map<String, Integer> timeouts = new HashMap<>();
        timeouts.put("Schema", 2);
        timeouts.put("Certificado", 2);
        timeouts.put("RegrasFiscais", 3);
        timeouts.put("BancoDados", 2);
        timeouts.put("SEFAZ", 4);

        boolean resultado = engine.executar(doc, timeouts);
        System.out.println("Resultado da validação: " + resultado);

        engine.shutdown();
    }
}
