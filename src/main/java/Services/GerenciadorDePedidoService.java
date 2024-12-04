package Services;

import Interfaces.ILog;
import ObjetosDominioProblema.Pedido;

public class GerenciadorDePedidoService {

    public static int codigoPedido = 1;

    public static int incrementarCodigoPedido() {
        return codigoPedido++;
    }

    public double calcularValorTotal(Pedido pedido) {
        double valorPedidoComDesconto = pedido.getValorPedido() - (pedido.getValorPedido() * pedido.getDescontoPercentualConcedidoValorPedido());
        double taxaEntregaComDesconto = pedido.getTaxaEntrega() - (pedido.getTaxaEntrega() * pedido.getDescontoPercentualConcedidoTaxaEntrega());
        double valorTotal = valorPedidoComDesconto + taxaEntregaComDesconto;
        
        return valorTotal;
    }

    public double calcularValorTotalERegistrarLog(Pedido pedido, GerenciadorTipoLogService gerenciadorTipoLogService, String tipoLog) {
        ILog log = gerenciadorTipoLogService.selecionarTipoLog(tipoLog);
        double valorTotal = calcularValorTotal(pedido);
        log.escreverMensagem(pedido, codigoPedido);
        incrementarCodigoPedido();

        return valorTotal;
    }
}
