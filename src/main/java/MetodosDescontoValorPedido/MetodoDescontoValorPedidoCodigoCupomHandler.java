/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MetodosDescontoValorPedido;

import ObjetosDominioProblema.Pedido;
import TipoCupom.CupomDescontoValorPedido;
import java.util.HashMap;
import java.util.Map;
import Interfaces.IMetodoDescontoValorPedidoHandler;

/**
 *
 * @author Cauã
 */
public class MetodoDescontoValorPedidoCodigoCupomHandler implements IMetodoDescontoValorPedidoHandler{
    private final Map<String, Double> descontosPorCodigoCupom;
    
    public MetodoDescontoValorPedidoCodigoCupomHandler(){
        descontosPorCodigoCupom = new HashMap<String, Double>();
        descontosPorCodigoCupom.put("DESC10", 0.10);
        descontosPorCodigoCupom.put("DESC20", 0.20);
        descontosPorCodigoCupom.put("DESC30", 0.30);
    }
    
    @Override
    public void calcularDescontoValorPedido(Pedido pedido){
        if(seAplica(pedido)){
            double descontoTotal = descontosPorCodigoCupom.get(pedido.getCodigoDeCupom());
             pedido.aplicarDescontoValorPedido(new CupomDescontoValorPedido("Desconto no pedido por código cupom", descontoTotal));
        }
    }
    
    @Override
    public boolean seAplica(Pedido pedido){
        String codigoCupom = pedido.getCodigoDeCupom();
        return descontosPorCodigoCupom.containsKey(codigoCupom);
    }
}
