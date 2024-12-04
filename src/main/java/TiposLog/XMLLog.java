package TiposLog;

import Interfaces.ILog;
import ObjetosDominioProblema.Pedido;
import Services.UsuarioLogadoService;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLLog implements ILog {
    private static final String CAMINHO_ARQUIVO = "arquivosDeLogXML/log.xml";

    @Override
    public void escreverMensagem(Pedido pedido) {
        try {
            // Obtém os dados necessários para o log
            String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
            Date dataAtual = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            // Obtém os dados do pedido
            String codigoPedido = "789";
            String nomeCliente = pedido.getCliente().getNome(); // Supondo que Cliente possui o método getNome()
            String nomeOperacao = "Calculo do valor total do pedido (Generico)";

            // Tenta ler o arquivo XML existente, se não existe, cria um novo
            File arquivo = new File(CAMINHO_ARQUIVO);
            Document doc;

            if (arquivo.exists()) {
                // Lê o conteúdo existente do arquivo
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(arquivo);
            } else {
                // Cria um novo documento XML se o arquivo não existir
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.newDocument();

                // Cria o elemento raiz <log> se o arquivo não existir
                Element root = doc.createElement("log");
                doc.appendChild(root);
            }

            // Cria um novo elemento <registro> com os campos necessários
            Element registro = doc.createElement("registro");

            Element usuario = doc.createElement("nome_usuario");
            usuario.appendChild(doc.createTextNode(nomeUsuario));
            registro.appendChild(usuario);

            Element data = doc.createElement("data");
            data.appendChild(doc.createTextNode(formatoData.format(dataAtual)));
            registro.appendChild(data);

            Element hora = doc.createElement("hora");
            hora.appendChild(doc.createTextNode(formatoHora.format(dataAtual)));
            registro.appendChild(hora);

            Element codigo = doc.createElement("codigo_pedido");
            codigo.appendChild(doc.createTextNode(codigoPedido));
            registro.appendChild(codigo);

            Element operacao = doc.createElement("nome_operacao");
            operacao.appendChild(doc.createTextNode(nomeOperacao));
            registro.appendChild(operacao);

            Element cliente = doc.createElement("nome_cliente");
            cliente.appendChild(doc.createTextNode(nomeCliente));
            registro.appendChild(cliente);

            // Adiciona o novo registro ao elemento raiz <log>
            Node root = doc.getDocumentElement();
            root.appendChild(registro);

            // Escreve o conteúdo de volta ao arquivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // Escreve no arquivo XML
            StreamResult result = new StreamResult(new File(CAMINHO_ARQUIVO));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            System.out.println("Registro salvo no arquivo XML.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}