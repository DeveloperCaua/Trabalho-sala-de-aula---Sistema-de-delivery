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

    private static final String CAMINHO_ARQUIVO = "arquivosDeLogJSON/log.json";  // Caminho do arquivo de log JSON

    @Override
    public void escreverMensagem(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido informado é inválido.");
        }

        // Obtém o nome do usuário logado
        String nomeUsuario = UsuarioLogadoService.getNomeUsuario();

        // Obtém a data e hora atuais
        Date dataAtual = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        // Obtém os dados do pedido
        String codigoPedido = "456";
        String nomeCliente = pedido.getCliente().getNome(); // Supondo que Cliente possui o método getNome()
        String nomeOperacao = "Calculo do valor total do pedido (Generico)";

        // Cria a estrutura de mensagem em JSON
        JSONObject jsonMensagem = new JSONObject();
        jsonMensagem.put("nome_usuario", nomeUsuario);
        jsonMensagem.put("data", formatoData.format(dataAtual));
        jsonMensagem.put("hora", formatoHora.format(dataAtual));
        jsonMensagem.put("codigo_pedido", codigoPedido);
        jsonMensagem.put("nome_operacao", nomeOperacao);
        jsonMensagem.put("nome_cliente", nomeCliente);

        // Verifica se a pasta "arquivosDeLogJSON" existe, caso contrário, cria-a
        try {
            Files.createDirectories(Paths.get("arquivosDeLogJSON"));  // Cria a pasta se não existir
        } catch (IOException e) {
            System.err.println("Erro ao criar a pasta: " + e.getMessage());
            return;
        }

        // Manipula o arquivo JSON
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (arquivo.exists()) {
                // Arquivo existe, adiciona a nova mensagem no arquivo JSON
                adicionarMensagemAoArquivo(jsonMensagem);
            } else {
                // Arquivo não existe, cria o arquivo com a estrutura inicial
                criarNovoArquivoComEstrutura(jsonMensagem);
            }
        } catch (IOException e) {
            System.err.println("Erro ao manipular o arquivo de log: " + e.getMessage());
        }
    }

    private void adicionarMensagemAoArquivo(JSONObject jsonMensagem) throws IOException {
        // Lê o arquivo existente e adiciona a nova mensagem
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            // Lê o conteúdo atual do arquivo JSON
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha);
            }

            // Converte o conteúdo do arquivo para um JSONArray existente
            JSONArray jsonArray = new JSONArray(sb.toString());
            jsonArray.put(jsonMensagem);  // Adiciona a nova mensagem ao array existente

            // Escreve novamente no arquivo JSON com a lista de mensagens atualizada
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
                writer.write(jsonArray.toString(4));  // Escreve com identação para facilitar leitura
                System.out.println("Mensagem salva no arquivo JSON.");
            }
        }
    }

    private void criarNovoArquivoComEstrutura(JSONObject jsonMensagem) throws IOException {
        // Cria um novo arquivo com a estrutura JSON inicial
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonMensagem);  // Adiciona a primeira mensagem no array

        // Escreve o arquivo JSON com a estrutura inicial
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            writer.write(jsonArray.toString(4));  // Escreve com identação para facilitar leitura
            System.out.println("Arquivo JSON criado e mensagem salva.");
        }
    }
}