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
    private static final String DB_FILE = DB_PATH + "/log.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_FILE;
    private static final String TABELA_LOGS = "logs";

    public DBLog() {
        criarDiretorioSeNecessario();
        criarTabela();
    }

    @Override
    public void escreverMensagem(Pedido pedido, int codigoPedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("\nPedido informado é inválido.");
        }

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String sql = "INSERT INTO " + TABELA_LOGS
                        + " (nome_usuario, data, hora, codigo_pedido, nome_operacao, nome_cliente) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
                    Date dataAtual = new Date();
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
                    String hora = new SimpleDateFormat("HH:mm:ss").format(dataAtual);
                    String codigo = String.valueOf(codigoPedido);
                    String nomeOperacao = "Calculo do valor total do pedido - método utilizado: calcularValorTotalERegistrarLog()";
                    String nomeCliente = pedido.getCliente().getNome();

                    pstmt.setString(1, nomeUsuario);
                    pstmt.setString(2, data);
                    pstmt.setString(3, hora);
                    pstmt.setString(4, codigo);
                    pstmt.setString(5, nomeOperacao);
                    pstmt.setString(6, nomeCliente);

                    pstmt.executeUpdate();
                    System.out.println("\nMensagem salva no banco de dados.");
                }
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao salvar mensagem no banco de dados: " + e.getMessage());
        }
    }

    private void criarDiretorioSeNecessario() {
        File diretorio = new File(DB_PATH);
        if (!diretorio.exists()) {
            if (diretorio.mkdirs()) {
                System.out.println("\nDiretório criado: " + DB_PATH);
            } else {
                System.err.println("\nFalha ao criar o diretório: " + DB_PATH);
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
                }
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao criar a tabela no banco de dados: " + e.getMessage());
        }
    }
}