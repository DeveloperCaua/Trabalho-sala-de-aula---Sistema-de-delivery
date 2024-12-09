package services;

import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import metodosdescontotaxa.MetodoDescontoTaxaPorBairroHandler;
import metodosdescontotaxa.MetodoDescontoTaxaPorTipoClienteHandler;
import metodosdescontotaxa.MetodoDescontoTaxaTipoItemHandler;
import metodosdescontotaxa.MetodoDescontoTaxaValorPedidoHandler;
import interfaces.IMetodoDescontoTaxaEntregaHandler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class CalculadoraDescontoTaxaService {
    private final List<IMetodoDescontoTaxaEntregaHandler> metodosDeDescontoTaxaEntrega;
    
    public CalculadoraDescontoTaxaService(){
        metodosDeDescontoTaxaEntrega = new ArrayList<IMetodoDescontoTaxaEntregaHandler>();
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaTipoItemHandler());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaValorPedidoHandler(2000.00));
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorBairroHandler());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorTipoClienteHandler());
    }
    
    public void calcularDesconto(Pedido pedido){
        if(pedido == null) throw new IllegalArgumentException("O pedido é inválido.");
        for(IMetodoDescontoTaxaEntregaHandler metodoDescontoTaxaEntrega : metodosDeDescontoTaxaEntrega){
            metodoDescontoTaxaEntrega.calcularDescontoTaxaEntrega(pedido);
        }
    }
}
