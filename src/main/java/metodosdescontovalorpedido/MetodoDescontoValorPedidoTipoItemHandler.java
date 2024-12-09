/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodosdescontovalorpedido;

import model.Item;
import model.Pedido;
import tipocupom.CupomDescontoValorPedido;
import java.util.HashMap;
import java.util.Map;
import interfaces.IMetodoDescontoValorPedidoHandler;

/**
 *
 * @author Cauã
 */
public class MetodoDescontoValorPedidoTipoItemHandler  implements IMetodoDescontoValorPedidoHandler{
    private final Map<String, Double> descontosPorTipoItem;
    
    public MetodoDescontoValorPedidoTipoItemHandler(){
        descontosPorTipoItem = new HashMap<String, Double>();
        descontosPorTipoItem.put("Alimentação", 0.05);
        descontosPorTipoItem.put("Educação", 0.20);
        descontosPorTipoItem.put("Lazer", 0.15);
    }
    
    @Override
    public void calcularDescontoValorPedido(Pedido pedido){
        if(seAplica(pedido)){
            double descontoTotal = 0.00;
            
            for(Item item : pedido.getItens()){
                String tipoItem = item.getTipo();
                descontoTotal += descontosPorTipoItem.get(tipoItem);   
            }
             pedido.aplicarDescontoValorPedido(new CupomDescontoValorPedido("Desconto no pedido por tipo de itens", descontoTotal));
        }
    }
    
    @Override
    public boolean seAplica(Pedido pedido){
        for(Item item : pedido.getItens()){
            String tipoItem = item.getTipo();
            if (descontosPorTipoItem.containsKey(tipoItem)){
                return true;
            }
        }
        
        return false;
    }
}
