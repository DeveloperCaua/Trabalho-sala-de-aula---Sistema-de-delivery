/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import ObjetosDominioProblema.Pedido;

/**
 *
 * @author Cauã
 */
public interface IMetodoDescontoValorPedidoHandler {
    public void calcularDescontoValorPedido(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}