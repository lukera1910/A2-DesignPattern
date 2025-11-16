package questao_3;

/**
* Estado ALERTA_AMARELO.
* Transita para ALERTA_VERMELHO se temperatura > 400°C por mais de 30 segundos.
* Pode voltar para OPERACAO_NORMAL se temperatura < 300°C (transição bidirecional).
*/
public class EstadoAlertaAmarelo implements EstadoUsina {
    @Override
    public void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao,
            boolean falhaResfriamento, int tempoAltaTempSeg) {
        if (contexto.isModoManutencao()) {
            contexto.setEstado(new EstadoManutencao());
            return;
        }
        if (temperatura < 300) {
            contexto.setEstado(new EstadoOperacaoNormal());
        } else if (temperatura > 400 && tempoAltaTempSeg > 30) {
            contexto.setEstado(new EstadoAlertaVermelho());
        }
    }

    @Override
    public String getNome() { return "ALERTA_AMARELO"; }
}
