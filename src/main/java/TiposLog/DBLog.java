package TiposLog;

import Interfaces.ILog;
import ObjetosDominioProblema.Pedido;
import Services.UsuarioLogadoService;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBLog implements ILog {

    private static final String DB_PATH = "arquivosDeLogDB";
    private static final String DB_FILE = DB_PATH + "/log.db";  // Caminho completo do arquivo de banco de dados
    private static final String DB_URL = "jdbc:sqlite:" + DB_FILE;  // URL de conexão SQLite
    private static final String TABELA_LOGS = "logs";  // Nome da tabela

    public DBLog() {
        criarDiretorioSeNecessario();  // Cria o diretório, se necessário
        criarTabela();  // Cria a tabela no banco de dados
    }

    @Override
    public void escreverMensagem(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido informado é inválido.");
        }

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String sql = "INSERT INTO " + TABELA_LOGS
                        + " (nome_usuario, data, hora, codigo_pedido, nome_operacao, nome_cliente) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    // Preenchendo os valores dos parâmetros
                    String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
                    Date dataAtual = new Date();
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
                    String hora = new SimpleDateFormat("HH:mm:ss").format(dataAtual);
                    String codigoPedido = "123";
                    String nomeOperacao = "Calculo do valor total do pedido (Generico)";
                    String nomeCliente = pedido.getCliente().getNome(); // Supondo que Cliente possui o método getNome()

                    pstmt.setString(1, nomeUsuario); // Nome do usuário
                    pstmt.setString(2, data); // Data
                    pstmt.setString(3, hora); // Hora
                    pstmt.setString(4, codigoPedido); // Código do pedido
                    pstmt.setString(5, nomeOperacao); // Nome da operação
                    pstmt.setString(6, nomeCliente); // Nome do cliente

                    pstmt.executeUpdate();
                    System.out.println("Mensagem salva no banco de dados.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar mensagem no banco de dados: " + e.getMessage());
        }
    }

    private void criarDiretorioSeNecessario() {
        File diretorio = new File(DB_PATH);
        if (!diretorio.exists()) {
            if (diretorio.mkdirs()) {
                System.out.println("Diretório criado: " + DB_PATH);
            } else {
                System.err.println("Falha ao criar o diretório: " + DB_PATH);
            }
        }
    }

    private void criarTabela() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABELA_LOGS + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nome_usuario TEXT NOT NULL, "
                        + "data TEXT NOT NULL, "
                        + "hora TEXT NOT NULL, "
                        + "codigo_pedido TEXT NOT NULL, "
                        + "nome_operacao TEXT NOT NULL, "
                        + "nome_cliente TEXT NOT NULL"
                        + ");";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                    System.out.println("Tabela de logs criada (se não existia).");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela no banco de dados: " + e.getMessage());
        }
    }
}