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
import Services.GerenciadorDePedidoService;
import Services.GerenciadorTipoLogService;
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

        Cliente cliente1 = new Cliente("Cauã", "Prata", 1.00, "Rua Victorio...", "Centro", "Alegre");
        Cliente cliente2 = new Cliente("Gabriel", "Ouro", 1.00, "Rua Principal...", "Bela Vista", "Alegre");

        Pedido pedido1 = new Pedido(LocalDateTime.now(), cliente1, 10.00, "DESC10");
        pedido1.adicionarItem(item1);
        pedido1.adicionarItem(item2);
        pedido1.adicionarItem(item3);
        
        Pedido pedido2 = new Pedido(LocalDateTime.now(), cliente2, 10.00, "DESC20");
        pedido1.adicionarItem(item4);
        pedido1.adicionarItem(item5);

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
                    
        calculadoraDeDescontoTaxaPedido.calcularDesconto(pedido2);
        calculadoraDeDescontoValorPedido.calcularDesconto(pedido2);
        
        System.out.println("\nDesconto TOTAL concedido na TAXA DE ENTREGA: R$ " + pedido1.getDescontoPercentualConcedidoTaxaEntrega());
        System.out.println("Desconto TOTAL concedido no VALOR DO PEDIDO: R$ " + pedido1.getDescontoPercentualConcedidoValorPedido());
        
        System.out.println("\nDescontos aplicados na TAXA DE ENTREGA:");
        for (CupomDescontoTaxaEntrega cupom : pedido2.getCuponsDescontoEntrega()) {
            System.out.println("Método: " + cupom.getNomeMetodo() + " - Valor percentual de desconto: " + cupom.getValorDesconto());
        }
        
        System.out.println("\nDescontos aplicados no VALOR DO PEDIDO:");
        for (CupomDescontoValorPedido cupom : pedido2.getCuponsDescontoValorPedido()) {
            System.out.println("Método: " + cupom.getNomeMetodo() + " - Valor percentual de desconto: " + cupom.getValorDesconto());
        }
        
        System.out.println("\nDesconto TOTAL concedido na TAXA DE ENTREGA: R$ " + pedido2.getDescontoPercentualConcedidoTaxaEntrega());
        System.out.println("Desconto TOTAL concedido no VALOR DO PEDIDO: R$ " + pedido2.getDescontoPercentualConcedidoValorPedido());

        
        
        GerenciadorTipoLogService gerenciadorTipoLogService = new GerenciadorTipoLogService();
        GerenciadorDePedidoService gerenciadorDePedidoService = new GerenciadorDePedidoService();

        System.out.println("\nValor final do pedido com descontos: R$ " + gerenciadorDePedidoService.calcularValorTotalERegistrarLog(pedido1, gerenciadorTipoLogService, "DB"));
        System.out.println("\nValor final do pedido com descontos: R$ " + gerenciadorDePedidoService.calcularValorTotalERegistrarLog(pedido2, gerenciadorTipoLogService, "DB"));
    }      
}