package questao_2;

public class Main {
    public static void main(String[] args) {
        SistemaBancarioLegado legado = new SistemaBancarioLegado();
        ProcessadorTransacoes processador = new SistemaBancarioAdapter(legado);

        // Executa uma transação moderna, que é traduzida para o formato legado
        RespostaTransacao resposta = processador.autorizar("4111-2222-3333-4444", 1500.75, "BRL");

        System.out.println("=== Resposta da Transação ===");
        System.out.println(resposta);
    }
}
