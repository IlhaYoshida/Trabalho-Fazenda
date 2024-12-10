package com.fazenda;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProducaoDiariaController {

    @FXML
    private TableView<Producao> tableProducaoDiaria;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Producao, String> vaca;

    @FXML
    private TableColumn<Producao, String> data;

    @FXML
    private TableColumn<Producao, String> quantidade;

    @FXML
    private void initialize() {
        Dao<Producao> daoProducao = new Dao<>(Producao.class);
        List<Producao> listaProducoes = daoProducao.listarTodos();
        ObservableList<Producao> producoes = FXCollections.observableArrayList(listaProducoes);

        vaca.setCellValueFactory(new PropertyValueFactory<>("vaca"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tableProducaoDiaria.setItems(producoes);
    }

    @FXML
    private void escolherDia() {
        LocalDate diaEscolhido = datePicker.getValue();

        if (diaEscolhido == null) {
            alertaDeErro("Por favor, selecione uma data.");
            return;
        }

        Dao<Producao> daoProducao = new Dao<>(Producao.class);
        List<Producao> listaProducoes = daoProducao.listarTodos();
        ObservableList<Producao> producoesDiarias = FXCollections.observableArrayList();

        for (Producao producao : listaProducoes) {
            if (producao.getData().equals(diaEscolhido)) {
                producoesDiarias.add(producao);
            }
        }

        if (producoesDiarias.isEmpty()) {
            alertaDeErro("Nenhuma produção encontrada para o dia selecionado.");
        }

        tableProducaoDiaria.setItems(producoesDiarias);
    }

    @FXML
    public void retornarParaMenu() throws IOException {
        App.setRoot("menu");
    }

    private void alertaDeErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
