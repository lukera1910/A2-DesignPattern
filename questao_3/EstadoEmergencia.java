package questao_3;

/**
* Estado EMERGENCIA.
* Só pode ser atingido após ALERTA_VERMELHO.
* Não há transição reversa direta — requer reinicialização manual (segurança).
*/
public class EstadoEmergencia implements EstadoUsina {
    @Override
    public void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao,
            boolean falhaResfriamento, int tempoAltaTempSeg) {
        System.out.println("[ALERTA CRÍTICO] Sistema em EMERGÊNCIA. Ação humana necessária.");
    }

    @Override
    public String getNome() { return "EMERGENCIA"; }
}
