package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author willi
 */
public class telaExcluirUsuario {
    
    private Usuario usuarioAExcluir;
    
    @FXML
    private TextField login;
    
    @FXML
private void excluirUsuario() {
    String loginUsuario = login.getText();

    if (loginUsuario.isEmpty()) {
        alertaDeErro("O campo de login não pode estar vazio!");
        return;
    }

    Dao<Usuario> daoUsuario = new Dao<>(Usuario.class);
    Usuario usuarioEncontrado = daoUsuario.buscarPorChave("login", loginUsuario);

    if (usuarioEncontrado != null) {
        daoUsuario.excluir("login", loginUsuario);
        alertaDeConfirmacao("Usuário excluído com sucesso!");
        limpar();
    } else {
        alertaDeErro("Usuário não encontrado! Verifique o login.");
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
        login.setText("");
    }
    
     @FXML
    public void retornarParaMenu () throws IOException {
        App.setRoot("menu");
    }
}
