/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Interfaces.IMetodoDescontoValorPedido;
import MetodosDescontoValorPedido.MetodoDescontoValorPedidoCodigoCupom;
import MetodosDescontoValorPedido.MetodoDescontoValorPedidoPorTipoCliente;
import MetodosDescontoValorPedido.MetodoDescontoValorPedidoTipoItem;
import ObjetosDominioProblema.Pedido;
import TipoCupom.CupomDescontoValorPedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cauã
 */
public class CalculadoraDescontoValorPedidoService {
    private final List<IMetodoDescontoValorPedido> metodosDeDescontoValorPedido;

    public CalculadoraDescontoValorPedidoService(){
        metodosDeDescontoValorPedido = new ArrayList<IMetodoDescontoValorPedido>();
        metodosDeDescontoValorPedido.add(new MetodoDescontoValorPedidoCodigoCupom());
        metodosDeDescontoValorPedido.add(new MetodoDescontoValorPedidoPorTipoCliente());
        metodosDeDescontoValorPedido.add(new MetodoDescontoValorPedidoTipoItem());
    }
    
        
    public void calcularDesconto(Pedido pedido) {
        if(pedido == null) throw new IllegalArgumentException("O pedido é inválido.");
        
        for (IMetodoDescontoValorPedido metodoDescontoValorPedido : metodosDeDescontoValorPedido) {
            if (!(metodoDescontoValorPedido instanceof MetodoDescontoValorPedidoTipoItem)) {
                metodoDescontoValorPedido.calcularDescontoValorPedido(pedido);
                
                boolean temCodidoOuTipoCliente = temCodidoOuTipoCliente(pedido);
                if (!temCodidoOuTipoCliente && metodoDescontoValorPedido instanceof MetodoDescontoValorPedidoTipoItem) {
                        metodoDescontoValorPedido.calcularDescontoValorPedido(pedido);
                }
                
            } else if((metodoDescontoValorPedido instanceof MetodoDescontoValorPedidoTipoItem) && pedido.getCuponsDescontoValorPedido().isEmpty()){
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
