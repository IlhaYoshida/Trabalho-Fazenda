
package com.fazenda;

import java.time.LocalDate;


import java.time.LocalDate;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Producao {
    @BsonProperty(value = "vaca")
    private Vaca vaca;
    @BsonProperty(value = "data")
    private LocalDate data;
    @BsonProperty(value = "quantidade")
    private String quantidade;
    
    public Producao() {
        this.vaca = null;
        this.data = null;
        this.quantidade = "";
    }
    
    public Producao(Vaca vaca, LocalDate data, String quantidade) {
        this.vaca = vaca;
        this.data = data;
        this.quantidade = quantidade;
    }

    public Vaca getVaca() {
        return vaca;
    }

    public void setVaca(Vaca vaca) {
        this.vaca = vaca;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

}
