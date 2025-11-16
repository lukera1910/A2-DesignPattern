package questao_4;

import java.time.LocalDate;

/**
* Validador 2: Certificado Digital (expiração e revogação) - dummy.
*/
public class ValidadorCertificado implements ValidadorNF {
    private final int timeoutSeconds;
    public ValidadorCertificado(int timeoutSeconds) { this.timeoutSeconds = timeoutSeconds; }

    @Override
    public boolean validar(NFDocumento doc) throws ValidadorException {
        simulateWork(timeoutSeconds);
        // Dummy: documento deve conter atributo "certExpiry" se não => falha
        Object expiry = doc.getAtributos().get("certExpiry");
        if (expiry == null)
            throw new ValidadorException("Certificado ausente");
        if (expiry instanceof LocalDate) {
            if (((LocalDate) expiry).isBefore(LocalDate.now()))
                throw new ValidadorException("Certificado expirado");
        }
        return true;
    }

    @Override
    public String nome() { return "Certificado"; }

    private void simulateWork(int t) {
        try { Thread.sleep(Math.min(400, t * 150)); } catch (InterruptedException ignored) {}
    }
}
