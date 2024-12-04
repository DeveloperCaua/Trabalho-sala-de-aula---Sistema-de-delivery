/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Interfaces.ILog;
import ObjetosDominioProblema.Pedido;
import TiposLog.DBLog;
import TiposLog.JSONLog;
import TiposLog.XMLLog;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cauã
 */
public class SelecionadorTipoLogService {
    private String tipoLog;
    private final Map<String, ILog> tiposDeLog;
    
    public SelecionadorTipoLogService(String tipoLog){
        if(tipoLog == null) throw new IllegalArgumentException("Tipo de log inválido.");
        
        this.tipoLog = tipoLog;
        tiposDeLog = new HashMap<String, ILog>();
        tiposDeLog.put("DB", new DBLog());
        tiposDeLog.put("JSON", new JSONLog());
        tiposDeLog.put("XML", new XMLLog());
    }
    
    public void setTipoLog(String tipoLog){
        if (tipoLog == null) throw new IllegalArgumentException("Tipo de log inválido.");
        
        this.tipoLog = tipoLog;
    }
    
    public void selecionarTipoLog(Pedido pedido, int codigoPedido){
        ILog logEncontrado = tiposDeLog.get(tipoLog);
        
        if (logEncontrado == null) {
            throw new IllegalArgumentException("Tipo de log não suportado: " + tipoLog);
        }
        
        logEncontrado.escreverMensagem(pedido, codigoPedido);
    }
}
