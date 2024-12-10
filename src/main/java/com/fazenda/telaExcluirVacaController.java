
package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class telaExcluirVacaController {
    
    @FXML
    private TextField brincoExcluir;
    
    @FXML
    private void excluir(){
            
        Dao<Vaca> daoVaca = new Dao<>(Vaca.class);
        String brincoVacaExcluir = brincoExcluir.getText();
        Vaca vaca = daoVaca.buscarPorChave("brinco", brincoVacaExcluir);
        
        if(vaca != null){
            daoVaca.excluir("brinco", brincoVacaExcluir);
            alertaDeConfirmacao("Vaca Excluida!");
            limpar();
        }else {
            alertaDeErro("Brinco inexistente!");
        }
        
        
    }
    
    @FXML
    private void alertaDeErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    @FXML
       private void alertaDeConfirmacao(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
       
    private void limpar() {
        brincoExcluir.setText("");
    }
       
    @FXML
    public void voltarParaOMenu() throws IOException {
        App.setRoot("menu");
    }
}
