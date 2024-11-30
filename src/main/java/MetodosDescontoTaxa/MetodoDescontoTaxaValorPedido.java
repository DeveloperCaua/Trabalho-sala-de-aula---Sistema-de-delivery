package MetodosDescontoTaxa;

import Interfaces.IMetodoDescontoTaxaEntrega;
import TipoCupom.CupomDescontoTaxaEntrega;
import ObjetosDominioProblema.Pedido;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class MetodoDescontoTaxaValorPedido implements IMetodoDescontoTaxaEntrega{
    private final double limiteValorPedido;
    private static final double VALOR_DE_DESCONTO = 0.15;
    
    public MetodoDescontoTaxaValorPedido(double limiteValorPedido){
        this.limiteValorPedido = limiteValorPedido;
    }
    
    @Override
    public void calcularDescontoTaxaEntrega(Pedido pedido){
        if(seAplica(pedido)){
            pedido.aplicarDescontoTaxaEntrega(new CupomDescontoTaxaEntrega("Desconto por valor do pedido", VALOR_DE_DESCONTO));
        }
    }
    
    @Override
    public boolean seAplica(Pedido pedido){
        double pedidoValorTotal = pedido.getValorPedido();
        double pedidoTaxaDeEntrega = pedido.getTaxaEntrega();
        if(pedidoValorTotal - pedidoTaxaDeEntrega > limiteValorPedido){
            return true;
        }
        
        return false;
    }   
}
