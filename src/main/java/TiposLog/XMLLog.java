package TiposLog;

import Interfaces.ILog;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLLog implements ILog {

    @Override
    public void escreverMensagem(String mensagem) {
        File file = new File("arquivosDeLogXML/logPedido.xml");
        List<String> linhas = new ArrayList<>();

        try {
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        linhas.add(linha);
                    }
                }
            } else {
                linhas.add("<logs>");
                linhas.add("</logs>");
            }

            int posicaoParaInserir = linhas.size() - 1;
<<<<<<< HEAD
            linhas.add(posicaoParaInserir, "    <logEntry>");
=======
            linhas.add(posicaoParaInserir, "  <logEntry>");
>>>>>>> 10a47fb313af4e8a2df5e9e4b7129d78ef4d691f
            linhas.add(posicaoParaInserir + 1, "    <mensagem>" + mensagem + "</mensagem>");
            linhas.add(posicaoParaInserir + 2, "  </logEntry>");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String linha : linhas) {
                    writer.write(linha);
                    writer.newLine();
                }
            }
        } catch (Exception exception) {
            exception.getMessage();
        }
    }
}
