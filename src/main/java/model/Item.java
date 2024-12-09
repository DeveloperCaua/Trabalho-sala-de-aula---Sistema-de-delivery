package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class Item {
    private String nome;
    private int quantidade;
    private double valorUnitario;
    private String tipo;
    
    public Item(String nome, int quantidade, double valorUnitario, String tipo){
        if(nome == null || quantidade < 0 || valorUnitario < 0.0 || tipo == null) throw new IllegalArgumentException("Algum dado do item informado é inválido.");

        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }
    
    public double getValorTotal(){
        return quantidade * valorUnitario;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    @Override
    public String toString(){
        return ("Nome: " + nome + "Quantidade: " + quantidade + "Valor unitário: " + valorUnitario + "Tipo: " + tipo + "Valor total: " + getValorTotal());
    }
}
