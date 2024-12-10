package com.fazenda;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class telaProducaoController {
    
    private Dao<Vaca> daoVaca;
    private Producao producao;
    private Vaca selecionada;

    @FXML
    private ComboBox<Vaca> comboVacas;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextField quantidadeDeLeite;

    @FXML
    private void initialize() {
        producao = new Producao();
        daoVaca = new Dao(Vaca.class);
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();
        ObservableList<Vaca> itensComboBox = FXCollections.observableArrayList(vacasCadastradas);
        comboVacas.setItems(itensComboBox);
    }
    
    @FXML
    private void cadastrarProducao() {
        Dao<Producao> daoProducao = new Dao(Producao.class);

        selecionada = comboVacas.getValue();
        if (selecionada == null) {
            alertaDeErro("Por favor, selecione uma vaca.");
            return;
        }

        
        if (dataPicker.getValue() == null) {
            alertaDeErro("Por favor, selecione uma data.");
            return;
        }

        
        String quantidade = quantidadeDeLeite.getText();
        if (quantidade == null || quantidade.isEmpty()) {
            alertaDeErro("Por favor, informe a quantidade de leite.");
            return;
        }

        
        try {
            double quantidadeNumerica = Double.parseDouble(quantidade);
            if (quantidadeNumerica <= 0) {
                alertaDeErro("A quantidade de leite deve ser um valor positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            alertaDeErro("A quantidade de leite deve ser um número válido.");
            return;
        }

        
        producao.setVaca(selecionada);
        producao.setData(dataPicker.getValue());
        producao.setQuantidade(quantidade);

        daoProducao.inserir(producao);

        alertaDeConfirmacao("Produção cadastrada com sucesso!");

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
        quantidadeDeLeite.setText("");
    }
    
     @FXML
    public void retornarParaMenu () throws IOException {
        App.setRoot("menu");
    }
}
