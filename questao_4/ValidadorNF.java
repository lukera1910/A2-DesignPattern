package questao_4;

/**
* Interface de validador genérico.
* Decisões de design:
* - Aplicação do padrão Chain of Responsibility para encadear validadores.
* - Cada validador é responsável por uma única verificação (SRP).
* - Uso de Future + ExecutorService para implementar timeout por validador.
*/
public interface ValidadorNF {
    /**
     * Executa validação. Retorna true se sucesso, false se falhar.
     * Pode lançar ValidadorException em casos falhos que exigem abortar.
     */
    boolean validar(NFDocumento doc) throws ValidadorException;

    /**
     * Se o validador modificou o documento, implementa rollback aqui.
     */
    default void rollback(NFDocumento doc) {
        /* noop por padrão */ }

    /**
     * Nome do validador (útil para logs e regras condicionais).
     */
    String nome();
}
