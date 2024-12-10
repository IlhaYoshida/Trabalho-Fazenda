package com.fazenda;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProducaoPorVacaController {

    @FXML
    private ComboBox<Vaca> comboVacas;
    @FXML
    private ComboBox<String> comboMes;
    @FXML
    private TableView<Producao> tableProducao;
    @FXML
    private TableColumn<Producao, LocalDate> datas;
    @FXML
    private TableColumn<Producao, String> quantidades;
    @FXML
    private Label quantidadeTotal;

    private Dao<Producao> daoProducao;

    @FXML
    private void initialize() {
        daoProducao = new Dao<>(Producao.class);
        List<Producao> producoes = daoProducao.listarTodos();
        ObservableList<Vaca> vacas = FXCollections.observableArrayList();

        for (Producao producao : producoes) {
            if (!vacas.contains(producao.getVaca())) {
                vacas.add(producao.getVaca());
            }
        }

        comboVacas.setItems(vacas);

        ObservableList<String> nomeMeses = FXCollections.observableArrayList();
        for (Month month : Month.values()) {
            nomeMeses.add(month.name().substring(0, 1).toUpperCase() + month.name().substring(1).toLowerCase());
        }
        comboMes.setItems(nomeMeses);

        datas.setCellValueFactory(new PropertyValueFactory<>("data"));
        quantidades.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    @FXML
    private void producaoPorVaca() {
        Vaca selecionada = comboVacas.getValue();
        String selecionado = comboMes.getValue();

        if (selecionada == null || selecionado == null) {
            alertaDeErro("Selecione uma vaca e um mês.");
            return;
        }

        List<Producao> producoes = daoProducao.listarTodos();
        ObservableList<Producao> producoesSelecionadas = FXCollections.observableArrayList();
        int total = 0;

        int mesIndice = Month.valueOf(selecionado.toUpperCase()).getValue();

        for (Producao producao : producoes) {
            if (producao.getVaca().getBrinco().equals(selecionada.getBrinco()) &&
                producao.getData().getMonthValue() == mesIndice) {
                producoesSelecionadas.add(producao);

                try {
                    total += Integer.parseInt(producao.getQuantidade());
                } catch (NumberFormatException e) {
                    alertaDeErro("Quantidade inválida para a produção no dia " + producao.getData());
                    return;
                }
            }
        }
        
        tableProducao.setItems(producoesSelecionadas);
        quantidadeTotal.setText("Produção total do mês: " + total + " litros");
    }

    private void alertaDeErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void retornarParaMenu() throws IOException {
        App.setRoot("menu");
    }
} 