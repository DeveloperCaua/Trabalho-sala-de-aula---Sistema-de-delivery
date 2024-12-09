package tiposlog;

import interfaces.ILog;
import java.io.FileWriter;

public class JSONLog implements ILog {

    @Override
    public void escreverMensagem(String mensagem) {
        try (FileWriter writer = new FileWriter("arquivosDeLogJSON/logPedido.json", true)) {
            // Escapar caracteres especiais na mensagem
            String jsonMensagem = mensagem.replace("\"", "\\\"");

            // Estruturar JSON corretamente
            writer.write("{\n   \"mensagem\": \"" + jsonMensagem + "\"\n}\n");
        } catch (Exception exception) {
            System.err.println("Erro ao escrever no arquivo: " + exception.getMessage());
        }
    }
}