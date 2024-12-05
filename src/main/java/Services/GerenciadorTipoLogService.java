package Services;

import Interfaces.ILog;

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
    
    public ILog getILog(){
        return tipoLog;
    }
}