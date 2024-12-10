package com.fazenda;

import java.io.IOException;
import java.time.Month;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;

public class ProducaoMensalController {

    @FXML
    private ComboBox<String> comboMeses;
    @FXML
    private TableView<Producao> tableProducoes;
    @FXML
    private TableColumn<Producao, String> vacas;
    @FXML
    private TableColumn<Producao, String> datas;
    @FXML
    private TableColumn<Producao, String> quantidades;

    private Dao<Producao> daoProducao;

    @FXML
    private void initialize() {

        daoProducao = new Dao<>(Producao.class);

        ObservableList<String> nomeMeses = FXCollections.observableArrayList();
        for (Month mes : Month.values()) {
            String nomeFormatado = mes.name().toLowerCase(); 
            nomeFormatado = nomeFormatado.substring(0, 1).toUpperCase() + nomeFormatado.substring(1);
            nomeMeses.add(nomeFormatado);
        }

        comboMeses.setItems(nomeMeses);

        vacas.setCellValueFactory(new PropertyValueFactory<>("vaca"));
        datas.setCellValueFactory(new PropertyValueFactory<>("data"));
        quantidades.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    @FXML
    private void producaoMensal() {
        String mes = comboMeses.getValue();

        if (mes == null) {
            alertaDeErro("Selecione um mês para visualizar a produção!");
            return;
        }

        int mesIndice = Month.valueOf(mes.toUpperCase()).getValue();

        List<Producao> ListaProducoes = daoProducao.listarTodos();
        ObservableList<Producao> producoesMensais = FXCollections.observableArrayList();

        for (Producao producao : ListaProducoes) {
            if (producao.getData().getMonthValue() == mesIndice) {
                producoesMensais.add(producao);
            }
        }

        if (producoesMensais.isEmpty()) {
            alertaDeErro("Não há produção registrada para o mês de " + mes + ".");
        } else {
            tableProducoes.setItems(producoesMensais);
        }
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


