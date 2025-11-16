package questao_4;

import java.util.HashMap;
import java.util.Map;

/**
* Representação simplificada de uma NF-e.
* - Contém campos mutáveis que validadores podem alterar (ex.: inserção de ID).
*/
public class NFDocumento {
    private final String xml; // conteúdo original
    private final Map<String, Object> atributos = new HashMap<>();

    public NFDocumento(String xml) { this.xml = xml; }

    public String getXml() { return xml; }
    public Map<String, Object> getAtributos() { return atributos; }
}
