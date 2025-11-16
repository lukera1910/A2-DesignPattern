package questao_3;

/**
* Estado ALERTA_VERMELHO.
* Transita para EMERGENCIA se falha de resfriamento ocorrer.
* Pode voltar para ALERTA_AMARELO se temperatura cair significativamente.
*/
public class EstadoAlertaVermelho implements EstadoUsina {
    @Override
    public void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao,
            boolean falhaResfriamento, int tempoAltaTempSeg) {
        if (contexto.isModoManutencao()) {
            contexto.setEstado(new EstadoManutencao());
            return;
        }
        if (falhaResfriamento) {
            contexto.setEstado(new EstadoEmergencia());
        } else if (temperatura < 350) {
            contexto.setEstado(new EstadoAlertaAmarelo());
        }
    }

    @Override
    public String getNome() { return "ALERTA_VERMELHO"; }
}
