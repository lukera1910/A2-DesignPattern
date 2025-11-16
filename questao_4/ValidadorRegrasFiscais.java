package questao_4;

/**
* Validador 3: Regras Fiscais (impostos) — só executa se 1 e 2 passaram.
* Pode modificar o documento (ex.: adicionar campo "impostosCalculados").
*/
public class ValidadorRegrasFiscais implements ValidadorNF {
    private final int timeoutSeconds;

    public ValidadorRegrasFiscais(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public boolean validar(NFDocumento doc) throws ValidadorException {
        simulateWork(timeoutSeconds);
        // Dummy: calcula imposto e grava atributo
        double imposto = 100.0; // fixo apenas para exemplo
        doc.getAtributos().put("impostosCalculados", imposto);
        return true;
    }

    @Override
    public void rollback(NFDocumento doc) {
        // Remove o campo que foi adicionado
        doc.getAtributos().remove("impostosCalculados");
    }   

    @Override
    public String nome() { return "RegrasFiscais"; }

    private void simulateWork(int t) {
        try { Thread.sleep(Math.min(700, t * 200)); } catch (InterruptedException ignored) {}
    }
}
