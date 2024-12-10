
package com.fazenda;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaConsultarController {
 

    @FXML
    private ComboBox<Vaca> comboVacas;
    @FXML
    private TableView<Vaca> tableVacas;
    @FXML
    private TableColumn<Vaca, String> brinco;
    @FXML
    private TableColumn<Vaca, String> nomeVaca;
    @FXML
    private TableColumn<Vaca, String> raca;

    @FXML
    private ComboBox<Usuario> comboUsuarios;
    @FXML
    private TableView<Usuario> tableUsuarios;
    @FXML
    private TableColumn<Usuario, String> nomeUsuario;
    @FXML
    private TableColumn<Usuario, String> login;

    @FXML
    private void initialize() {
        Dao<Vaca> daoVaca = new Dao<>(Vaca.class);
        List<Vaca> listaVacas = daoVaca.listarTodos();
        ObservableList<Vaca> vacas = FXCollections.observableArrayList(listaVacas);
        comboVacas.setItems(vacas);

        brinco.setCellValueFactory(new PropertyValueFactory<>("brinco"));
        nomeVaca.setCellValueFactory(new PropertyValueFactory<>("nome"));
        raca.setCellValueFactory(new PropertyValueFactory<>("raca"));

        tableVacas.setItems(vacas);

        Dao<Usuario> daoUsuario = new Dao<>(Usuario.class);
        List<Usuario> listaUsuarios = daoUsuario.listarTodos();
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(listaUsuarios);
        comboUsuarios.setItems(usuarios);

        nomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));

        tableUsuarios.setItems(usuarios);
    }

    @FXML
    private void consultarVaca() {
        Vaca selecionada = comboVacas.getValue();
        if (selecionada != null) {
            ObservableList<Vaca> escolhida = FXCollections.observableArrayList(selecionada);
            tableVacas.setItems(escolhida);
        } else {
            mostrarMensagemErro("Selecione uma Vaca!");
        }
    }

    @FXML
    private void consultarUsuario() {
        Usuario selecionado = comboUsuarios.getValue();
        if (selecionado != null) {
            ObservableList<Usuario> escolhido = FXCollections.observableArrayList(selecionado);
            tableUsuarios.setItems(escolhido);
        } else {
            mostrarMensagemErro("Selecione um Usuário!");
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void retornarParaMenu() throws IOException {
        App.setRoot("menu");
    }
}




