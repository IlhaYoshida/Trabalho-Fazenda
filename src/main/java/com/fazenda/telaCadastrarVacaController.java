
package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class telaCadastrarVacaController {

    Vaca vaca;

    @FXML
    private void initialize() {
        vaca = new Vaca();
    }

    @FXML
    private TextField definirBrinco;

    @FXML
    private TextField definirNome;

    @FXML
    private TextField definirRaca;

    @FXML
    private void cadastrarVaca() {
        if (definirBrinco.getText().isEmpty() || definirNome.getText().isEmpty() || definirRaca.getText().isEmpty()) {
            alertaDeErro("Preencha todos os campos.");
            return;
        }

        Dao<Vaca> daoVaca = new Dao(Vaca.class);
        
        if (daoVaca.buscarPorChave("brinco", definirBrinco.getText()) != null) {
            definirBrinco.setText("");
            alertaDeErro("Brinco já Cadastrado");
        } else {
            vaca.setBrinco(definirBrinco.getText());
            vaca.setNome(definirNome.getText());
            vaca.setRaca(definirRaca.getText());
            daoVaca.inserir(vaca);
            alertaDeConfirmacao("Vaca Cadastrada!");
            limparCampos();
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

    @FXML
    private void limparCampos() {
        definirBrinco.setText("");
        definirNome.setText("");
        definirRaca.setText("");
    }

    @FXML
    public void voltarParaOMenu() throws IOException {
        App.setRoot("menu");
    }
}

