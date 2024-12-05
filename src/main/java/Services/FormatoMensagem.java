package Services;


import ObjetosDominioProblema.Pedido;
import static Services.GerenciadorDePedidoService.codigoPedido;
import Services.UsuarioLogadoService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class FormatoMensagem {
    private final DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private final String nomeUsuario = UsuarioLogadoService.getNomeUsuario();
    private final String data = LocalDateTime.now().format(DateFormatter);
    private final String hora = LocalDateTime.now().format(HourFormatter);
    private String nomeCliente;
    
    public FormatoMensagem(Pedido pedido){
        if(pedido == null) throw new IllegalArgumentException("Pedido inválido!");
        
        nomeCliente = pedido.getCliente().getNome();
    }
    
    @Override
    public String toString(){
        return "Nome do usuário: " + nomeUsuario +
                " | Data: " + data +
                " | Horário: " + hora +
                " | Código do pedido: " + codigoPedido +
                " | Nome da operação: Calculo do valor total do pedido calcularValorTotal(Pedido pedido)" +
                " | Nome do cliente: " + nomeCliente;
    }
}
