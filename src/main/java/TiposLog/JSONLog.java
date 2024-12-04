package TiposLog;

import Interfaces.ILog;
import ObjetosDominioProblema.Pedido;
import Services.UsuarioLogadoService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONLog implements ILog {

    private static final String CAMINHO_ARQUIVO = "arquivosDeLogJSON/log.json";

    @Override
    public void escreverMensagem(Pedido pedido, int codigoPedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("\nPedido informado é inválido.");
        }

        String nomeUsuario = UsuarioLogadoService.getNomeUsuario();

        Date dataAtual = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        String nomeCliente = pedido.getCliente().getNome();
        String nomeOperacao = "Calculo do valor total do pedido - método utilizado: calcularValorTotalERegistrarLog()";

        JSONObject jsonMensagem = new JSONObject();
        jsonMensagem.put("nome_usuario", nomeUsuario);
        jsonMensagem.put("data", formatoData.format(dataAtual));
        jsonMensagem.put("hora", formatoHora.format(dataAtual));
        jsonMensagem.put("codigo_pedido", codigoPedido);
        jsonMensagem.put("nome_operacao", nomeOperacao);
        jsonMensagem.put("nome_cliente", nomeCliente);

        try {
            Files.createDirectories(Paths.get("arquivosDeLogJSON"));
        } catch (IOException e) {
            System.err.println("\nErro ao criar a pasta: " + e.getMessage());
            return;
        }

        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                adicionarMensagemAoArquivo(jsonMensagem);
            } else {
                criarNovoArquivoComEstrutura(jsonMensagem);
            }
        } catch (IOException e) {
            System.err.println("\nErro ao manipular o arquivo de log: " + e.getMessage());
        }
    }

    private void adicionarMensagemAoArquivo(JSONObject jsonMensagem) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            jsonArray.put(jsonMensagem);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
                writer.write(jsonArray.toString(4));
            }
        }
    }

    private void criarNovoArquivoComEstrutura(JSONObject jsonMensagem) throws IOException {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonMensagem);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            writer.write(jsonArray.toString(4));  // Escreve com identação para facilitar leitura
        }
    }
}