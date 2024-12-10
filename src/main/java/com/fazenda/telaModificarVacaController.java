package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class telaModificarVacaController {

    private Vaca vacaAModificar;

    @FXML
    private TextField brinco;

    @FXML
    private TextField novoBrinco;

    @FXML
    private TextField novoNome;

    @FXML
    private TextField novaRaca;

    @FXML
    private void buscarEModificarVaca() {
        String brincoVaca = brinco.getText();

        if (brincoVaca.isEmpty()) {
            alertaDeErro("O campo de brinco está vazio");
            return;
        }

        Dao<Vaca> daoVaca = new Dao<>(Vaca.class);
        vacaAModificar = daoVaca.buscarPorChave("brinco", brincoVaca);

        if (vacaAModificar != null) {
            if (novoBrinco.getText().isEmpty() || novoNome.getText().isEmpty() || novaRaca.getText().isEmpty()) {
                alertaDeErro("Todos os campos devem estar preenchidos!");
                return;
            }

            vacaAModificar.setBrinco(novoBrinco.getText());
            vacaAModificar.setNome(novoNome.getText());
            vacaAModificar.setRaca(novaRaca.getText());

            daoVaca.alterar("brinco", brincoVaca, vacaAModificar);

            alertaDeConfirmacao("Dados da vaca modificados!");
            limpar();
        } else {
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

    @FXML
    private void limpar() {
        brinco.setText("");
        novoBrinco.setText("");
        novoNome.setText("");
        novaRaca.setText("");
    }

    @FXML
    public void retornarParaMenu() throws IOException {
        App.setRoot("menu");
    }
}