package Services;

import Interfaces.ILog;
import TiposLog.DBLog;
import TiposLog.JSONLog;
import TiposLog.XMLLog;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorTipoLogService {
    private final Map<String, ILog> tiposDeLog;

    public GerenciadorTipoLogService() {
        tiposDeLog = new HashMap<>();
        tiposDeLog.put("DB", new DBLog());
        tiposDeLog.put("JSON", new JSONLog());
        tiposDeLog.put("XML", new XMLLog());
    }

    public ILog selecionarTipoLog(String tipoLog) {
        if (tipoLog == null || !tiposDeLog.containsKey(tipoLog)) {
            throw new IllegalArgumentException("Tipo de log não suportado: " + tipoLog);
        }
        return tiposDeLog.get(tipoLog);
    }
}