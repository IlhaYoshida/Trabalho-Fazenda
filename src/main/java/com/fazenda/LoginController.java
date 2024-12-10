package com.fazenda;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private TextField login;
    
    @FXML
    private PasswordField senha;

    private Dao<Usuario> daoUsuario;

    public LoginController() {
        daoUsuario = new Dao<>(Usuario.class);
    }

    @FXML
    private void fazerLogin() throws IOException {
        String loginUsuario = login.getText();
        String senhaUsuario = senha.getText();

        if (loginUsuario.isEmpty() || senhaUsuario.isEmpty()) {
            alertaDeErro("Preencha todos os campos.");
            limpar();
            return;
        }

        List<Usuario> usuarios = daoUsuario.listarTodos();
        Usuario usuarioAutenticado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(loginUsuario) && usuario.getSenha().equals(senhaUsuario)) {
                usuarioAutenticado = usuario;
                break;
            }
        }

        if (usuarioAutenticado == null) {
            alertaDeErro("Login ou senha incorretos.");
            limpar();
            return;
        }

        limpar();
        voltarParaOMenu();
    }

    private void alertaDeErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limpar() {
        login.setText("");
        senha.setText("");
    }

    @FXML
    public void voltarParaOMenu() throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    public void mudarCadastrarUsuario() throws IOException {
        App.setRoot("telaLoginUsuario");
    }
}


