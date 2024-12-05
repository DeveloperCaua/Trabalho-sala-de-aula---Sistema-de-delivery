package Main;

import ObjetosDominioProblema.Cliente;
import ObjetosDominioProblema.Item;
import ObjetosDominioProblema.Pedido;
import Services.CalculadoraDescontoTaxaService;
import Services.CalculadoraDescontoValorPedidoService;
import Services.GerenciadorDePedidoService;
import Services.GerenciadorTipoLogService;
import TipoCupom.CupomDescontoTaxaEntrega;
import TipoCupom.CupomDescontoValorPedido;
import TiposLog.DBLog;
import TiposLog.JSONLog;
import TiposLog.XMLLog;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Criando itens
        Item item1 = new Item("X-Calango", 1, 16.00, "Alimentação");
        Item item2 = new Item("Lápis", 1, 15.50, "Educação");
        Item item3 = new Item("Bicicleta", 1, 500.00, "Lazer");

        // Criando cliente
        Cliente cliente1 = new Cliente("Cauã", "Prata", 1.00, "Rua Victorio...", "Centro", "Alegre");

        // Criando pedido
        Pedido pedido1 = new Pedido(LocalDateTime.now(), cliente1, 10.00, "DESC10");
        pedido1.adicionarItem(item1);
        pedido1.adicionarItem(item2);
        pedido1.adicionarItem(item3);

        // Instanciando serviços de cálculo de descontos
        CalculadoraDescontoTaxaService calculadoraDescontoTaxa = new CalculadoraDescontoTaxaService();
        CalculadoraDescontoValorPedidoService calculadoraDescontoValor = new CalculadoraDescontoValorPedidoService();

        // Aplicando cálculos de desconto no pedido
        calculadoraDescontoTaxa.calcularDesconto(pedido1);
        calculadoraDescontoValor.calcularDesconto(pedido1);

        // Criando uma instância de DecimalFormat para formatar os valores
        DecimalFormat df = new DecimalFormat("#.00");

        // Exibindo cupons aplicados na taxa de entrega
        System.out.println("\nDescontos aplicados na TAXA DE ENTREGA:");
        for (CupomDescontoTaxaEntrega cupom : pedido1.getCuponsDescontoEntrega()) {
            // Exibindo o desconto em formato percentual com 2 casas decimais
            System.out.println("Método: " + cupom.getNomeMetodo() + " - Desconto: " + df.format(cupom.getValorDesconto() * 100) + "%");
        }

        // Exibindo cupons aplicados no valor do pedido
        System.out.println("\nDescontos aplicados no VALOR DO PEDIDO:");
        for (CupomDescontoValorPedido cupom : pedido1.getCuponsDescontoValorPedido()) {
            // Exibindo o desconto em formato percentual com 2 casas decimais
            System.out.println("Método: " + cupom.getNomeMetodo() + " - Desconto: " + df.format(cupom.getValorDesconto() * 100) + "%");
        }

        // Exibindo descontos totais em porcentagem
        System.out.println("\nDesconto TOTAL na TAXA DE ENTREGA: " 
            + df.format(pedido1.getDescontoPercentualConcedidoTaxaEntrega() * 100) + "%");
        System.out.println("Desconto TOTAL no VALOR DO PEDIDO: " 
            + df.format(pedido1.getDescontoPercentualConcedidoValorPedido() * 100) + "%");

        
        //está correto?
        GerenciadorTipoLogService.getInstance().setTipoLog(new XMLLog());
        
        //t1
        GerenciadorDePedidoService gerenciadorPedido1 = new GerenciadorDePedidoService();
        double valorFinal = gerenciadorPedido1.calcularValorTotal(pedido1);
        
        //t2
        GerenciadorTipoLogService.getInstance().setTipoLog(new JSONLog());
        valorFinal = gerenciadorPedido1.calcularValorTotal(pedido1);
        
        //t3
        GerenciadorTipoLogService.getInstance().setTipoLog(new DBLog());
        valorFinal = gerenciadorPedido1.calcularValorTotal(pedido1);
        
        System.out.println("\nValor final do Pedido com descontos: R$ " + df.format(valorFinal));
    }
}
