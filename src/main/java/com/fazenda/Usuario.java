
package com.fazenda;
        
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Usuario {
    @BsonProperty(value="login")
    private String login;
    
    @BsonProperty(value="senha")
    private String senha;
    
    @BsonProperty(value="nome")
    private String nome;
    
    public Usuario(){}
    
    public Usuario(String login, String senha, String nome){
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString () {
       return login;
    }
    
    
}
