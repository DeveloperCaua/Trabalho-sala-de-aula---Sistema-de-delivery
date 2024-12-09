package tiposlog;

import interfaces.ILog;
import java.sql.*;

public class DBLog implements ILog {

    private static final String DB_URL = "jdbc:sqlite:arquivosDeLogDB/log.db";
    private static final String TABELA_LOGS = "logs";

    public DBLog() {
        criarTabela();
    }

    @Override
    public void escreverMensagem(String mensagem) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO " + TABELA_LOGS + " (mensagem) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, mensagem);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar mensagem no banco de dados: " + e.getMessage());
        }
    }

    private void criarTabela() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_LOGS + " ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "mensagem TEXT NOT NULL)";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela no banco de dados: " + e.getMessage());
        }
    }
}