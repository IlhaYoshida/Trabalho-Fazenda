/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fazenda;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Vaca  {
    @BsonProperty(value="brinco")
    private String brinco;
    
    @BsonProperty(value="nome")
    private String nome;
    
    @BsonProperty(value="raca")
    private String raca; 

    public Vaca(){}
    
    public Vaca(String brinco, String nome, String raca){
        this.brinco = brinco; 
        this.nome = nome;
        this.raca = raca;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getBrinco() {
        return brinco;
    }

    public void setBrinco(String brinco) {
        this.brinco = brinco;
    }
    
    @Override
    public String toString () {
       return nome;
    }
        
}
