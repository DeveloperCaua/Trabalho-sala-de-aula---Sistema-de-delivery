package MetodosDescontoTaxa;


import ObjetosDominioProblema.Cliente;
import java.util.HashMap;
import java.util.Map;
import TipoCupom.CupomDescontoTaxaEntrega;
import ObjetosDominioProblema.Pedido;
import Interfaces.IMetodoDescontoTaxaEntregaHandler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class MetodoDescontoTaxaPorTipoClienteHandler implements IMetodoDescontoTaxaEntregaHandler{
    private final Map<String, Double> descontosPorTipoCliente;
    
    public MetodoDescontoTaxaPorTipoClienteHandler(){
        descontosPorTipoCliente = new HashMap<String, Double>();
        descontosPorTipoCliente.put("Ouro", 0.30);
        descontosPorTipoCliente.put("Prata", 0.20);
        descontosPorTipoCliente.put("Bronze", 0.10);
    }
    
    @Override
    public void calcularDescontoTaxaEntrega(Pedido pedido){
        if(seAplica(pedido)){
            double descontoTotal = 0.0;
            Cliente cliente = pedido.getCliente();
            
            descontoTotal = descontosPorTipoCliente.get(cliente.getTipo()); 
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoTaxaEntrega("Desconto por tipo de cliente", descontoTotal));
        }
    }
    
    @Override
    public boolean seAplica(Pedido pedido){
        Cliente cliente = pedido.getCliente();
            if (descontosPorTipoCliente.containsKey(cliente.getTipo())){
                return true;
            }
        
        return false;
    }
}
