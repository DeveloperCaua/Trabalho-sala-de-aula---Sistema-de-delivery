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
    public void escreverMensagem(Pedido pedido, int codigoPedido) {
        try {
            String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
            Date dataAtual = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            String codigo = String.valueOf(codigoPedido);
            String nomeCliente = pedido.getCliente().getNome();
            String nomeOperacao = "Calculo do valor total do pedido - método utilizado: calcularValorTotalERegistrarLog()";

            File arquivo = new File(CAMINHO_ARQUIVO);
            Document doc;

            if (arquivo.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(arquivo);
            } else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.newDocument();

                Element root = doc.createElement("log");
                doc.appendChild(root);
            }

            Element registro = doc.createElement("registro");

            Element usuarioTabela = doc.createElement("nome_usuario");
            usuarioTabela.appendChild(doc.createTextNode(nomeUsuario));
            registro.appendChild(usuarioTabela);

            Element dataTabela = doc.createElement("data");
            dataTabela.appendChild(doc.createTextNode(formatoData.format(dataAtual)));
            registro.appendChild(dataTabela);

            Element hora = doc.createElement("hora");
            hora.appendChild(doc.createTextNode(formatoHora.format(dataAtual)));
            registro.appendChild(hora);

            Element codigoTabela = doc.createElement("codigo_pedido");
            codigoTabela.appendChild(doc.createTextNode(codigo));
            registro.appendChild(codigoTabela);

            Element operacaoTabela = doc.createElement("nome_operacao");
            operacaoTabela.appendChild(doc.createTextNode(nomeOperacao));
            registro.appendChild(operacaoTabela);

            Element clienteTabela = doc.createElement("nome_cliente");
            clienteTabela.appendChild(doc.createTextNode(nomeCliente));
            registro.appendChild(clienteTabela);

            Node root = doc.getDocumentElement();
            root.appendChild(registro);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            StreamResult result = new StreamResult(new File(CAMINHO_ARQUIVO));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}