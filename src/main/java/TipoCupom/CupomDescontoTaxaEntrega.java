package TipoCupom;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class CupomDescontoTaxaEntrega{
    private final String nomeMetodo;
    private final double valorDesconto;
    
    public CupomDescontoTaxaEntrega(String nomeMetodo, double valorDesconto){
        if(nomeMetodo == null || valorDesconto < 0.0) throw new IllegalArgumentException("Algum dos dados do cupom são inválidos.");
        
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
