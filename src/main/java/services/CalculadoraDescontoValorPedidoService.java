/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import metodosdescontovalorpedido.MetodoDescontoValorPedidoCodigoCupomHandler;
import metodosdescontovalorpedido.MetodoDescontoValorPedidoPorTipoClienteHandler;
import metodosdescontovalorpedido.MetodoDescontoValorPedidoTipoItemHandler;
import model.Pedido;
import tipocupom.CupomDescontoValorPedido;
import java.util.ArrayList;
import java.util.List;
import interfaces.IMetodoDescontoValorPedidoHandler;

/**
 *
 * @author Cauã
 */
public class CalculadoraDescontoValorPedidoService {
    private final List<IMetodoDescontoValorPedidoHandler> metodosDeDescontoValorPedido;

    public CalculadoraDescontoValorPedidoService(){
        metodosDeDescontoValorPedido = new ArrayList<IMetodoDescontoValorPedidoHandler>();
        metodosDeDescontoValorPedido.add(new MetodoDescontoValorPedidoCodigoCupomHandler());
        metodosDeDescontoValorPedido.add(new MetodoDescontoValorPedidoPorTipoClienteHandler());
        metodosDeDescontoValorPedido.add(new MetodoDescontoValorPedidoTipoItemHandler());
    }
    
        
    public void calcularDesconto(Pedido pedido) {
        if(pedido == null) throw new IllegalArgumentException("O pedido é inválido.");
        
        for (IMetodoDescontoValorPedidoHandler metodoDescontoValorPedido : metodosDeDescontoValorPedido) {
            if (!(metodoDescontoValorPedido instanceof MetodoDescontoValorPedidoTipoItemHandler)) {
                metodoDescontoValorPedido.calcularDescontoValorPedido(pedido);
                
                boolean temCodidoOuTipoCliente = temCodidoOuTipoCliente(pedido);
                if (!temCodidoOuTipoCliente && metodoDescontoValorPedido instanceof MetodoDescontoValorPedidoTipoItemHandler) {
                        metodoDescontoValorPedido.calcularDescontoValorPedido(pedido);
                }
                
            } else if((metodoDescontoValorPedido instanceof MetodoDescontoValorPedidoTipoItemHandler) && pedido.getCuponsDescontoValorPedido().isEmpty()){
                metodoDescontoValorPedido.calcularDescontoValorPedido(pedido);
            }

        }
    }

    private boolean temCodidoOuTipoCliente(Pedido pedido) {
        if(pedido == null) throw new IllegalArgumentException("O pedido é inválido.");
        
        for (CupomDescontoValorPedido cupomDescontoPedido : pedido.getCuponsDescontoValorPedido()) {
            String nomeMetodo = cupomDescontoPedido.getNomeMetodo();
            if (nomeMetodo.equals("Desconto no pedido por código cupom") || nomeMetodo.equals("Desconto no pedido pelo tipo de cliente")) {
                return true;
            }
        }
        return false;
    }
}
