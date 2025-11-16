package questao_4;

/**
 * Validador 5: Serviço SEFAZ (consulta online) — só executa se anteriores
 * passarem.
 */
public class ValidadorSeFaz implements ValidadorNF {
    private final int timeoutSeconds;

    public ValidadorSeFaz(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public boolean validar(NFDocumento doc) throws ValidadorException {
        simulateWork(timeoutSeconds);
        // Dummy: servidor responde ok se atributo "numero" terminar em dígito par
        String numero = (String) doc.getAtributos().get("numero");
        if (numero == null)
            throw new ValidadorException("Número ausente para consulta SEFAZ");
        char last = numero.charAt(numero.length() - 1);
        if ((last - '0') % 2 != 0)
            throw new ValidadorException("SEFAZ rejeitou a NF");
        return true;
    }

    @Override
    public String nome() {
        return "SEFAZ";
    }

    private void simulateWork(int t) {
        try {
            Thread.sleep(Math.min(600, t * 200));
        } catch (InterruptedException ignored) {
        }
    }
}
