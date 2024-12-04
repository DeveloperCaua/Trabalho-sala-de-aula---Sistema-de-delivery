/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Services.CalculadoraDescontoTaxaService;
import java.time.LocalDateTime;
import ObjetosDominioProblema.Cliente;
import TipoCupom.CupomDescontoTaxaEntrega;
import ObjetosDominioProblema.Item;
import ObjetosDominioProblema.Pedido;
import Services.CalculadoraDescontoValorPedidoService;
import Services.SelecionadorTipoLogService;
import TipoCupom.CupomDescontoValorPedido;

/**
 *
 * @author Cauã
 */
public class Main {
    public static void main(String[] args){
        
        Item item1 = new Item("X-Calango", 1, 16.00, "Alimentação");
        Item item2 = new Item("Lápis", 1, 15.50, "Educação");
        Item item3 = new Item("Bicicleta", 1, 500.00, "Lazer");
        Item item4 = new Item("Caneta", 1, 20.50, "Educação");
        Item item5 = new Item("Caneta", 1, 20.50, "Educação");

        Cliente cliente = new Cliente("Cauã", "Prata", 1.00, "Rua Victorio...", "Centro", "Alegre");

        Pedido pedido1 = new Pedido(LocalDateTime.now(), cliente, 10.00, "DESC10");
        pedido1.adicionarItem(item1);
        pedido1.adicionarItem(item2);
        pedido1.adicionarItem(item3);


        CalculadoraDescontoTaxaService calculadoraDeDescontoTaxaPedido = new CalculadoraDescontoTaxaService();
        CalculadoraDescontoValorPedidoService calculadoraDeDescontoValorPedido = new CalculadoraDescontoValorPedidoService();
        
        calculadoraDeDescontoTaxaPedido.calcularDesconto(pedido1);
        calculadoraDeDescontoValorPedido.calcularDesconto(pedido1);

        System.out.println("\nDescontos aplicados na TAXA DE ENTREGA:");
        for (CupomDescontoTaxaEntrega cupom : pedido1.getCuponsDescontoEntrega()) {
            System.out.println("Método: " + cupom.getNomeMetodo() + " - Valor percentual de desconto: " + cupom.getValorDesconto());
        }
        
        System.out.println("\nDescontos aplicados no VALOR DO PEDIDO:");
        for (CupomDescontoValorPedido cupom : pedido1.getCuponsDescontoValorPedido()) {
            System.out.println("Método: " + cupom.getNomeMetodo() + " - Valor percentual de desconto: " + cupom.getValorDesconto());
        }
        
        System.out.println("\nDesconto TOTAL concedido na TAXA DE ENTREGA: R$ " + pedido1.getDescontoPercentualConcedidoTaxaEntrega());
        System.out.println("Desconto TOTAL concedido no VALOR DO PEDIDO: R$ " + pedido1.getDescontoPercentualConcedidoValorPedido());

        System.out.println("\nValor final do pedido com descontos: R$ " + pedido1.getValorPedido());
        
        
        
        SelecionadorTipoLogService logService = new SelecionadorTipoLogService("XML");
        logService.setTipoLog("JSON");
                
        logService.selecionarTipoLog(pedido1);
        logService.selecionarTipoLog(pedido1);
        
        logService.setTipoLog("XML");
        logService.selecionarTipoLog(pedido1);
        logService.selecionarTipoLog(pedido1);
        
        logService.setTipoLog("DB");
        logService.selecionarTipoLog(pedido1);
        logService.selecionarTipoLog(pedido1);
    }      
}