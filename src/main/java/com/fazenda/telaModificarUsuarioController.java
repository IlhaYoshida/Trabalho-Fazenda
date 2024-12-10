
package com.fazenda;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class telaModificarUsuarioController {
    
    @FXML
    private TextField login;
    
    @FXML
    private TextField novoLogin;
    
    @FXML
    private TextField novoNome;
    
    @FXML
    private TextField novaSenha;
    
    @FXML
private void modificarUsuario() {
    String loginUsuarioAModificar = login.getText();
    if (loginUsuarioAModificar.isEmpty()) {
        alertaDeErro("O campo login está vazio!");
        return;
    }

    Dao<Usuario> daoUsuario = new Dao<>(Usuario.class);
    Usuario usuario = daoUsuario.buscarPorChave("login", loginUsuarioAModificar);

    if (usuario == null) {
        alertaDeErro("Usuário não encontrado!");
        return;
    }

    String novo_login = novoLogin.getText();
    String novo_nome = novoNome.getText();
    String nova_senha = novaSenha.getText();

    if (novo_login.isEmpty() || novo_nome.isEmpty() || nova_senha.isEmpty()) {
        alertaDeErro("Os campos não podem estar vazios!");
        return;
    }

    String loginAntigo = usuario.getLogin();

    usuario.setLogin(novo_login);
    usuario.setNome(novo_nome);
    usuario.setSenha(nova_senha);

    daoUsuario.alterar("login", loginAntigo, usuario);
    alertaDeConfirmacao("Dados do usuário modificados!");
    limpar();
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
        novoLogin.setText("");
        novoNome.setText("");
        novaSenha.setText("");
    }

    @FXML
    public void retornarParaMenu() throws IOException {
        App.setRoot("menu");
    }
}
