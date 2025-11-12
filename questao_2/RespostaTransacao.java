package questao_2;

/**
 * Representa a resposta moderna de uma transação
 */

public class RespostaTransacao {
    private final String status;
    private final String codigoAutorizacao;
    private final double valor;
    private final String moeda;
    private final String mensagem;

    public RespostaTransacao(String status, String codigoAutorizacao, double valor, String moeda, String mensagem) {
        this.status = status;
        this.codigoAutorizacao = codigoAutorizacao;
        this.valor = valor;
        this.moeda = moeda;
        this.mensagem = mensagem;
    }

    public String getStatus() { return status; }
    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public double getValor() { return valor; }
    public String getMoeda() { return moeda; }
    public String getMensagem() { return mensagem; }

    @Override
    public String toString() {
        return String.format("Status: %s | Código: %s | Valor: %.2f | %s", status, codigoAutorizacao, valor, moeda, mensagem);
    }
}
