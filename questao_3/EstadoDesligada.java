package questao_3;

/**
* Estado DESLIGADA — ponto inicial da usina.
*
* Decisão de design:
* - Só permite transição para OPERACAO_NORMAL ou modo manutenção.
*/
public class EstadoDesligada implements EstadoUsina {
    @Override
    public void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao,
            boolean falhaResfriamento, int tempoAltaTempSeg) {
        if (contexto.isModoManutencao()) {
            contexto.setEstado(new EstadoManutencao());
        } else {
            contexto.setEstado(new EstadoOperacaoNormal());
        }
    }

    @Override
    public String getNome() { return "DESLIGADA"; }
}
