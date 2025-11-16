package questao_4;

/**
* Validador 1: Schema XML contra XSD (dummy).
*/
public class ValidadorSchema implements ValidadorNF {
    private final int timeoutSeconds;
    public ValidadorSchema(int timeoutSeconds) { this.timeoutSeconds = timeoutSeconds; }

    @Override
    public boolean validar(NFDocumento doc) throws ValidadorException {
        // Dummy: aceita se xml contem <NFe>
        simulateWork(timeoutSeconds);
        boolean ok = doc.getXml() != null && doc.getXml().contains("<NFe>");
        if (!ok) throw new ValidadorException("Schema inválido");
        return true;
    }

    @Override
    public String nome() { return "Schema"; }

    private void simulateWork(int t) {
        try { Thread.sleep(Math.min(500, t * 200)); } catch (InterruptedException ignored) {}
    }
}
