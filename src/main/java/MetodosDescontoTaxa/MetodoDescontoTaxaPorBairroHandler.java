package MetodosDescontoTaxa;


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
public class MetodoDescontoTaxaPorBairroHandler implements IMetodoDescontoTaxaEntregaHandler{
    private final Map<String, Double> descontosPorBairro;
    
    public MetodoDescontoTaxaPorBairroHandler(){
        descontosPorBairro = new HashMap<String, Double>();
        descontosPorBairro.put("Centro", 0.20);
        descontosPorBairro.put("Bela Vista", 0.30);
        descontosPorBairro.put("Cidade Maravilhosa", 0.15);
    }
     
    @Override
    public void calcularDescontoTaxaEntrega(Pedido pedido){
        if(seAplica(pedido)){
            double descontoTotal = 0.00;
            String bairroCliente = pedido.getCliente().getBairro();
            
            descontoTotal = descontosPorBairro.get(bairroCliente); 
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoTaxaEntrega("Desconto por bairro do cliente", descontoTotal));
        }
    }
    
    @Override
    public boolean seAplica(Pedido pedido){
            String bairroCliente = pedido.getCliente().getBairro();
            if (descontosPorBairro.containsKey(bairroCliente)){
                return true;
            }
        
        return false;
    }    
}
