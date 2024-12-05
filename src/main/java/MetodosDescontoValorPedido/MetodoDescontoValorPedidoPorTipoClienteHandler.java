/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MetodosDescontoValorPedido;

import ObjetosDominioProblema.Cliente;
import ObjetosDominioProblema.Pedido;
import TipoCupom.CupomDescontoValorPedido;
import java.util.HashMap;
import java.util.Map;
import Interfaces.IMetodoDescontoValorPedidoHandler;

/**
 *
 * @author Cauã
 */
public class MetodoDescontoValorPedidoPorTipoClienteHandler implements IMetodoDescontoValorPedidoHandler{
    private final Map<String, Double> descontosPorTipoCliente;
    
    public MetodoDescontoValorPedidoPorTipoClienteHandler(){
        descontosPorTipoCliente = new HashMap<String, Double>();
        descontosPorTipoCliente.put("Ouro", 0.30);
        descontosPorTipoCliente.put("Prata", 0.20);
        descontosPorTipoCliente.put("Bronze", 0.10);
    }
    
    @Override
    public void calcularDescontoValorPedido(Pedido pedido){
        if(seAplica(pedido)){
            double descontoTotal = 0.0;
            Cliente cliente = pedido.getCliente();
            
            descontoTotal = descontosPorTipoCliente.get(cliente.getTipo()); 
            pedido.aplicarDescontoValorPedido(new CupomDescontoValorPedido("Desconto no pedido por tipo de cliente", descontoTotal));
        }
    }
    
    @Override
    public boolean seAplica(Pedido pedido){
        Cliente cliente = pedido.getCliente();
        return descontosPorTipoCliente.containsKey(cliente.getTipo());
    }
}
