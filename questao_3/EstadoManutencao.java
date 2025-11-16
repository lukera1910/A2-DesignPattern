package questao_3;

/**
* Estado de MANUTENÇÃO.
* Sobrepõe regras normais — o operador pode testar ou calibrar sistemas.
* Quando desativado, retorna à operação normal.
*/
public class EstadoManutencao implements EstadoUsina {
    @Override
    public void avaliarTransicao(UsinaNuclearContext contexto, double temperatura, double pressao, double radiacao,
            boolean falhaResfriamento, int tempoAltaTempSeg) {
        if (!contexto.isModoManutencao()) {
            contexto.setEstado(new EstadoOperacaoNormal());
        } else {
            System.out.println("[INFO] Em modo manutenção — transições ignoradas.");
        }
    }

    @Override
    public String getNome() { return "MANUTENCAO"; }
}
