package questao_3;

public class Main {
    public static void main(String[] args) {
        UsinaNuclearContext usina = new UsinaNuclearContext(new EstadoDesligada());

        // Inicia operação normal
        usina.atualizarCondicoes(100, 1.0, 0.1, false, 0);

        // Aumenta temperatura → alerta amarelo
        usina.atualizarCondicoes(320, 1.1, 0.2, false, 0);

        // Mantém alta temperatura por 35 segundos → alerta vermelho
        usina.atualizarCondicoes(410, 1.2, 0.3, false, 35);

        // Falha no sistema de resfriamento → emergência
        usina.atualizarCondicoes(420, 1.3, 0.4, true, 40);

        // Ativa modo manutenção (sobrescreve)
        usina.ativarModoManutencao();
        usina.atualizarCondicoes(100, 1.0, 0.1, false, 0);

        // Desativa manutenção e volta para operação normal
        usina.desativarModoManutencao();
        usina.atualizarCondicoes(200, 1.0, 0.1, false, 0);
    }
}
