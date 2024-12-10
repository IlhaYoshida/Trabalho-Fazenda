package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class telaLoginUsuarioController {

    Usuario usuario;

    @FXML
    private void initialize() {
        usuario = new Usuario();
    }

    @FXML
    private TextField definirLogin;

    @FXML
    private TextField definirSenha;

    @FXML
    private TextField definirNome;

    @FXML
    private void cadastrarUsuario() {
        if (definirLogin.getText().isEmpty() || definirSenha.getText().isEmpty() || definirNome.getText().isEmpty()) {
            alertaDeErro("Preencha todos os campos.");
            return;
        }

        Dao<Usuario> daoUsuario = new Dao(Usuario.class);
        
        if (daoUsuario.buscarPorChave("login", definirLogin.getText()) != null) {
            definirLogin.setText("");
            alertaDeErro("Login já Existente");
        } else {
            usuario.setLogin(definirLogin.getText());
            usuario.setSenha(definirSenha.getText());
            usuario.setNome(definirNome.getText());
            daoUsuario.inserir(usuario);
            alertaDeConfirmacao("Usuário Cadastrado!");
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
        definirLogin.setText("");
        definirSenha.setText("");
        definirNome.setText("");
    }

    @FXML
    public void voltarParaOMenu() throws IOException {
        App.setRoot("menu");
    }
}
