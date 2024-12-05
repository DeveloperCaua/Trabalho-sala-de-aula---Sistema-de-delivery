package TiposLog;

import Interfaces.ILog;
import java.io.*;

public class XMLLog implements ILog {

    @Override
    public void escreverMensagem(String mensagem) {
        //como fazer para criar mais de uma linha de XML sem dar erro?
        try (FileWriter writer = new FileWriter("arquivosDeLogXML/logPedido.xml", true)) {
            writer.write("<logEntry>\n");
            writer.write("  <mensagem>" + mensagem + "</mensagem>");
            writer.write("\n</logEntry>");
            
        } catch (Exception exception) {
            exception.getMessage();
        }
    }
}