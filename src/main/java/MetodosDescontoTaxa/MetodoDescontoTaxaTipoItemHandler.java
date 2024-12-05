package MetodosDescontoTaxa;


import java.util.HashMap;
import java.util.Map;
import TipoCupom.CupomDescontoTaxaEntrega;
import ObjetosDominioProblema.Item;
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
public class MetodoDescontoTaxaTipoItemHandler implements IMetodoDescontoTaxaEntregaHandler{
    private final Map<String, Double> descontosPorTipoItem;
    
    public MetodoDescontoTaxaTipoItemHandler(){
        descontosPorTipoItem = new HashMap<String, Double>();
        descontosPorTipoItem.put("Alimentação", 0.05);
        descontosPorTipoItem.put("Educação", 0.20);
        descontosPorTipoItem.put("Lazer", 0.15);
    }
    
    @Override
    public void calcularDescontoTaxaEntrega(Pedido pedido){
        if(seAplica(pedido)){
            double descontoTotal = 0.00;
            
            for(Item item : pedido.getItens()){
                String tipoItem = item.getTipo();
                descontoTotal += descontosPorTipoItem.get(tipoItem);   
            }
             pedido.aplicarDescontoTaxaEntrega(new CupomDescontoTaxaEntrega("Desconto por tipo de item", descontoTotal));
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
