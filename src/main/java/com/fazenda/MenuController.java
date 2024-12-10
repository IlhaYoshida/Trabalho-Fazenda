
package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;

public class MenuController {
    
    @FXML
    public void mudarCadastrarUsuario() throws IOException {
        App.setRoot("telaLoginUsuario");
    }
    
    @FXML
    public void mudarCadastrarVaca() throws IOException {
        App.setRoot("telaCadastrarVaca");
    }
    
    @FXML
    public void mudarProducao() throws IOException {
        App.setRoot("telaProducao");
    }
    
    @FXML
    public void mudarExcluirVaca() throws IOException {
        App.setRoot("telaExcluirVaca");
    }
    
    @FXML
    public void mudarExcluirUsuario() throws IOException {
        App.setRoot("telaExcluirUsuario");
    }
    
    @FXML
    public void mudarModificarVaca() throws IOException {
        App.setRoot("telaModificarVaca");
    }
    
    @FXML
    public void mudarModificarUsuario() throws IOException {
        App.setRoot("telaModificarUsuario");
    }
    
    @FXML
    public void mudarConsultar() throws IOException {
        App.setRoot("TelaConsultar");
    }
    
    @FXML
    public void mudarProducaoDiaria() throws IOException {
        App.setRoot("TelaProducaoDiaria");
    }
    
    @FXML
    public void mudarProducaoMensal() throws IOException {
        App.setRoot("TelaProducaoMensal");
    }
    
    @FXML
    public void mudarProducaoPorVaca() throws IOException {
        App.setRoot("TelaProducaoPorVaca");
    }
    
    @FXML
    public void mudarLogin() throws IOException {
        App.setRoot("TelaLogin");
    }
    
    
}
