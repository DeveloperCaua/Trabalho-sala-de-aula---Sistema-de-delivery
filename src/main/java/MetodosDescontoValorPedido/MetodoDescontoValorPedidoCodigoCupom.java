/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MetodosDescontoValorPedido;

import Interfaces.IMetodoDescontoValorPedido;
import ObjetosDominioProblema.Pedido;
import TipoCupom.CupomDescontoValorPedido;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cauã
 */
public class MetodoDescontoValorPedidoCodigoCupom implements IMetodoDescontoValorPedido{
    private final Map<String, Double> descontosPorCodigoCupom;
    
    public MetodoDescontoValorPedidoCodigoCupom(){
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
