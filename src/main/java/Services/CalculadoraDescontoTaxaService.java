package Services;

import java.util.ArrayList;
import java.util.List;
import ObjetosDominioProblema.Pedido;
import Interfaces.IMetodoDescontoTaxaEntrega;
import MetodosDescontoTaxa.MetodoDescontoTaxaPorBairro;
import MetodosDescontoTaxa.MetodoDescontoTaxaPorTipoCliente;
import MetodosDescontoTaxa.MetodoDescontoTaxaTipoItem;
import MetodosDescontoTaxa.MetodoDescontoTaxaValorPedido;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class CalculadoraDescontoTaxaService {
    private final List<IMetodoDescontoTaxaEntrega> metodosDeDescontoTaxaEntrega;
    
    public CalculadoraDescontoTaxaService(){
        metodosDeDescontoTaxaEntrega = new ArrayList<IMetodoDescontoTaxaEntrega>();
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaTipoItem());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaValorPedido(2000.00));
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorBairro());
        metodosDeDescontoTaxaEntrega.add(new MetodoDescontoTaxaPorTipoCliente());
    }
    
    public void calcularDesconto(Pedido pedido){
        if(pedido == null) throw new IllegalArgumentException("O pedido é inválido.");
        for(IMetodoDescontoTaxaEntrega metodoDescontoTaxaEntrega : metodosDeDescontoTaxaEntrega){
            metodoDescontoTaxaEntrega.calcularDescontoTaxaEntrega(pedido);
        }
    }
}
