package Interfaces;

import ObjetosDominioProblema.Pedido;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public interface IMetodoDescontoTaxaEntrega {
    public void calcularDescontoTaxaEntrega(Pedido pedido);
    public boolean seAplica(Pedido pedido);
}