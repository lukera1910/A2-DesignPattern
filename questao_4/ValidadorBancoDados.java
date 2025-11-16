package questao_4;

/**
* Validador 4: Banco de dados (duplicidade) — insere um registro e permite rollback.
* Simula inserção em DB com transaction manual.
*/
public class ValidadorBancoDados implements ValidadorNF {
    private final InMemoryNFDatabase db;
    private final int timeoutSeconds;

    public ValidadorBancoDados(InMemoryNFDatabase db, int timeoutSeconds) {
        this.db = db;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public boolean validar(NFDocumento doc) throws ValidadorException {
        simulateWork(timeoutSeconds);
        String numero = (String) doc.getAtributos().get("numero");
        if (numero == null)
            throw new ValidadorException("Número da NF ausente");
        if (db.exists(numero))
            throw new ValidadorException("Duplicidade: número já existe");
        // Insere e marca para possível rollback
        db.insert(numero, doc);
        doc.getAtributos().put("dbInserted", numero);
        return true;
    }

    @Override
    public void rollback(NFDocumento doc) {
        Object key = doc.getAtributos().get("dbInserted");
        if (key != null) {
            db.delete((String) key);
            doc.getAtributos().remove("dbInserted");
        }
    }

    @Override
    public String nome() {
        return "BancoDados";
    }

    private void simulateWork(int t) {
        try {
            Thread.sleep(Math.min(500, t * 150));
        } catch (InterruptedException ignored) {
        }
    }
}
