package ObjetosDominioProblema;


import TipoCupom.CupomDescontoValorPedido;
import TipoCupom.CupomDescontoTaxaEntrega;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class Pedido {
     private double taxaEntrega;
     private Cliente cliente;
     private LocalDateTime data;
     private List<Item> itens;
     private String codigoDeCupom;
     private final List<CupomDescontoTaxaEntrega> cuponsDescontoEntrega;
     private final List<CupomDescontoValorPedido> cuponsDescontoValorPedido;
     
     public Pedido(LocalDateTime dataPedido, Cliente cliente, double taxaEntrega, String codigoDeCupom){
        if (taxaEntrega < 0 || dataPedido == null || cliente == null) throw new IllegalArgumentException("Algum dado do pedido informado é inválido.");
        
        this.taxaEntrega = taxaEntrega;
        this.cliente = cliente;
        this.data = dataPedido;
        this.codigoDeCupom = codigoDeCupom;
        itens = new ArrayList<Item>();
        cuponsDescontoEntrega = new ArrayList<CupomDescontoTaxaEntrega>();
        cuponsDescontoValorPedido = new ArrayList<CupomDescontoValorPedido>();
     }
     
     public void adicionarItem(Item item){
       itens.add(item);
     }
     
     public double getValorPedido(){
        double total = 0;
        for(Item item : itens){
            total = total + item.getValorTotal();
        } 
        
        return (total - (total * getDescontoPercentualConcedidoValorPedido())) + (getTaxaEntrega() - (getTaxaEntrega() * getDescontoPercentualConcedidoTaxaEntrega()));
     }
     
     public Cliente getCliente(){
         return cliente;
     }
     
     public List<Item> getItens(){
         return Collections.unmodifiableList(itens);
     }
     
     public double getTaxaEntrega(){
         return taxaEntrega;
     }
     
     public String getCodigoDeCupom(){
         return codigoDeCupom;
     }
     
     public void aplicarDescontoTaxaEntrega(CupomDescontoTaxaEntrega cupomTaxaEntrega){
        if(cupomTaxaEntrega == null) throw new IllegalArgumentException("O cupom de desconto na taxa de entrega informado é inválido.");
        
            cuponsDescontoEntrega.add(cupomTaxaEntrega);
     }
     
    public void aplicarDescontoValorPedido(CupomDescontoValorPedido cupomValorPedido){
        if(cupomValorPedido == null) throw new IllegalArgumentException("O cupom de desconto no valor do pedido informado é inválido.");
        
        cuponsDescontoValorPedido.add(cupomValorPedido); 
     }
     
     public double getDescontoPercentualConcedidoTaxaEntrega(){
        double descontoPercentualConcedido = 0.00;
        for(CupomDescontoTaxaEntrega cupom: getCuponsDescontoEntrega()){
            descontoPercentualConcedido += cupom.getValorDesconto();
        }
        
        if(descontoPercentualConcedido > 1.00){
            return 1.00;
        }
        return descontoPercentualConcedido;
     }
     
     public double getDescontoPercentualConcedidoValorPedido(){
        double descontoPercentualConcedido = 0.00;
        for(CupomDescontoValorPedido cupom: getCuponsDescontoValorPedido()){
            descontoPercentualConcedido += cupom.getValorDesconto();
        }
        
        if(descontoPercentualConcedido > 1.00){
            return 1.00;
        }
        return descontoPercentualConcedido;
     }

     
     public List<CupomDescontoTaxaEntrega> getCuponsDescontoEntrega(){
         return Collections.unmodifiableList(cuponsDescontoEntrega);
     }
     
     public List<CupomDescontoValorPedido> getCuponsDescontoValorPedido(){
         return Collections.unmodifiableList(cuponsDescontoValorPedido);
     }
     
     @Override
     public String toString(){
        return ("Taxa de entrega: " + taxaEntrega + "Cliente: " + cliente + "Itens: " + itens + "Cupons de desconto: " + cuponsDescontoEntrega);
     }
}
