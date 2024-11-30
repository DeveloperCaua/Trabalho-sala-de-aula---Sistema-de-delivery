package ObjetosDominioProblema;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cauã
 */
public class Cliente {
    private String nome;
    private String tipo;
    private double fidelidade;
    private String logradouro;
    private String bairro;
    private String cidade;
    
    public Cliente(String nome, String tipo, double fidelidade, String logradouro, String bairro, String cidade){
        if(nome == null || tipo == null || fidelidade < 0.0 || logradouro == null || bairro == null || cidade == null) throw new IllegalArgumentException("Algum dado do cliente informado é inválido.");
        
        this.nome = nome;
        this.tipo = tipo;
        this.fidelidade = fidelidade;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getLogradouro(){
        return logradouro;
    }
    
    public String getBairro(){
        return bairro;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public double getFidelidade(){
        return fidelidade;
    }
    
    public void setFidelidade(double fidelidade){
        this.fidelidade = fidelidade;
    }
    
    @Override
    public String toString(){
        return "Nome: " + nome + "Tipo: " + tipo + "Fidelidade: " + fidelidade + "Logradouro: " + logradouro;
    }
}
