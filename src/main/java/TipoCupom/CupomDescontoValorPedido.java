/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipoCupom;

/**
 *
 * @author Cauã
 */
public class CupomDescontoValorPedido{
    private String nomeMetodo;
    private double valorDesconto;
    
    public CupomDescontoValorPedido(String nomeMetodo, double valorDesconto){
        if(nomeMetodo == null || valorDesconto < 0.0) throw new IllegalArgumentException("Dados do cupom de desconto entrega invalidos");
        
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }
    
    public String getNomeMetodo(){
        return nomeMetodo;
    }
    
    public double getValorDesconto(){
        return valorDesconto;
    }
    
    @Override
    public String toString(){
        return "Nome do método: " + nomeMetodo + "Valor percentual de desconto: " + valorDesconto;
    }
}
