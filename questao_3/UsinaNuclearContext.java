package questao_3;

/**
*
* Decisão de design:
* - Aplicação do padrão State: o comportamento da usina depende do estado atual.
* - Segue o princípio Open/Closed: novos estados podem ser adicionados sem alterar o Context.
* - Segue SRP: o contexto apenas gerencia o estado, não implementa a lógica de transição.
*/
public class UsinaNuclearContext {
    private EstadoUsina estadoAtual;
    private boolean modoManutencao;

    public UsinaNuclearContext(EstadoUsina estadoInicial) {
        this.estadoAtual = estadoInicial;
        this.modoManutencao = false;
    }

    public void setEstado(EstadoUsina novoEstado) {
        System.out.println("[TRANSIÇÃO] " + estadoAtual.getNome() + " → " + novoEstado.getNome());
        this.estadoAtual = novoEstado;
    }

    public EstadoUsina getEstadoAtual() {
        return estadoAtual;
    }

    public boolean isModoManutencao() {
        return modoManutencao;
    }

    public void ativarModoManutencao() {
        System.out.println("[INFO] Modo de manutenção ativado.");
        modoManutencao = true;
    }

    public void desativarModoManutencao() {
        System.out.println("[INFO] Modo de manutenção desativado.");
        modoManutencao = false;
    }

    // Simula uma atualização de sensores (dados do ambiente)
    public void atualizarCondicoes(double temperatura, double pressao, double radiacao, boolean falhaResfriamento,
            int tempoAltaTempSeg) {
        estadoAtual.avaliarTransicao(this, temperatura, pressao, radiacao, falhaResfriamento, tempoAltaTempSeg);
    }
}
