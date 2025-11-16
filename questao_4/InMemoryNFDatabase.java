package questao_4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* Simples banco em memória com operações insert/delete para demonstrar rollback.
*/
public class InMemoryNFDatabase {
    private final Map<String, NFDocumento> storage = new ConcurrentHashMap<>();

    public boolean exists(String numero) {
        return storage.containsKey(numero);
    }

    public void insert(String numero, NFDocumento doc) {
        storage.put(numero, doc);
    }

    public void delete(String numero) {
        storage.remove(numero);
    }
}