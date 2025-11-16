package questao_3;

/**
* Estado OPERACAO_NORMAL.
* Transita para ALERTA_AMARELO se temperatura > 300°C.
*/
public class EstadoOperacaoNormal implements EstadoUsina {
    @Override
    public void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao,
            boolean falhaResfriamento, int tempoAltaTempSeg) {
        if (contexto.isModoManutencao()) {
            contexto.setEstado(new EstadoManutencao());
            return;
        }
        if (temperatura > 300) {
            contexto.setEstado(new EstadoAlertaAmarelo());
        }
    }

    @Override
    public String getNome() { return "OPERACAO_NORMAL"; }
}
