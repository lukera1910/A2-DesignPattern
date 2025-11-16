package questao_3;

/**
* Interface base para os estados da usina.
* Cada estado conhece suas próprias regras de transição.
*/
public interface EstadoUsina {
    void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao, boolean falhaResfriamento, int tempoAltaTempSeg);
    String getNome();
}