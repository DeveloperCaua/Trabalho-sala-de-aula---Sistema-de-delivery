package Services;

import ObjetosDominioProblema.Pedido;

public class GerenciadorDePedidoService {
    public static int codigoPedido = 1;

    public double calcularValorTotal(Pedido pedido) {
        double valorPedidoComDesconto = pedido.getValorPedido() - (pedido.getValorPedido() * pedido.getDescontoPercentualConcedidoValorPedido());
        double taxaEntregaComDesconto = pedido.getTaxaEntrega() - (pedido.getTaxaEntrega() * pedido.getDescontoPercentualConcedidoTaxaEntrega());
        double valorTotal = valorPedidoComDesconto + taxaEntregaComDesconto;

        //isso está correto?
        GerenciadorTipoLogService.getInstance().getILog().escreverMensagem(new FormatoMensagem(pedido).toString());
        codigoPedido++;
        return valorTotal;
    }
}