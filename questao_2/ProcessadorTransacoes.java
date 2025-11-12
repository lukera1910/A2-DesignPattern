package questao_2;

public interface ProcessadorTransacoes {
    RespostaTransacao autorizar(String cartao, double valor, String moeda);    
}
