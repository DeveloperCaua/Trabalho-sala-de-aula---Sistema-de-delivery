package Services;

import Interfaces.ILog;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerenciadorTipoLogService {
    private ILog tipoLog;
    private static GerenciadorTipoLogService instancia = null;

    private GerenciadorTipoLogService() {
    }

    public static GerenciadorTipoLogService getInstance(){
        if(instancia == null) 
            instancia = new GerenciadorTipoLogService();

        return instancia;
    }
    
    public void setTipoLog(ILog tipoLog) {
        if(tipoLog == null) {
            throw new IllegalArgumentException("Argumento invalido!");
        }
        this.tipoLog = tipoLog;
    }
    
    public void criarMensagemLog(String parteIncomum){
        String parteComum = montandoParteComumString(parteIncomum);
        tipoLog.escreverMensagem(parteComum);
    }

    private String montandoParteComumString(String parteIncomum){
        DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
        String data = LocalDateTime.now().format(DateFormatter);
        String hora = LocalDateTime.now().format(HourFormatter);
        
        return "Nome do usuário logado: " + UsuarioLogadoService.getNomeUsuario() + " | Data: " + data + " | Hora: " + hora + " " + parteIncomum;
    }
}
