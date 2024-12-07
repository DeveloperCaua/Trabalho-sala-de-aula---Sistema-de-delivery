package Services;

import ObjetosDominioProblema.Pedido;

public class GerenciadorDePedidoService {
    public static int codigoPedido = 1;

    public double calcularValorTotal(Pedido pedido) {
        double valorPedidoComDesconto = pedido.getValorPedido() - (pedido.getValorPedido() * pedido.getDescontoPercentualConcedidoValorPedido());
        double taxaEntregaComDesconto = pedido.getTaxaEntrega() - (pedido.getTaxaEntrega() * pedido.getDescontoPercentualConcedidoTaxaEntrega());
        double valorTotal = valorPedidoComDesconto + taxaEntregaComDesconto;

        GerenciadorTipoLogService.getInstance().criarMensagemLog(gerarDadosLog(pedido));
        codigoPedido++;
        return valorTotal;
    }
    
    private String gerarDadosLog(Pedido pedido){
        String nomeCliente = pedido.getCliente().getNome();
        return " | Código do pedido: " + codigoPedido + 
                " | Nome da operação: Calculo do valor total do pedido - calcularValorTotal(Pedido pedido) " +
                "| Nome do cliente: " + nomeCliente;
    }
}
