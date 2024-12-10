package edu.sysagenda.controller;

import edu.sysagenda.persistence.ContatoDAO;
import edu.sysagenda.model.entity.Contato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgendaController {

    @FXML private TextField nomeTextField;
    @FXML private TextField telefoneTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField enderecoTextField;

    @FXML private TableView<Contato> contatosTableView;
    @FXML private TableColumn<Contato, Integer> idColumn;
    @FXML private TableColumn<Contato, String> nomeColumn;
    @FXML private TableColumn<Contato, String> telefoneColumn;
    @FXML private TableColumn<Contato, String> emailColumn;
    @FXML private TableColumn<Contato, String> enderecoColumn;

    private ContatoDAO contatoDAO;
    private ObservableList<Contato> contatosList;

    @FXML
    public void initialize() {
        contatoDAO = new ContatoDAO();

        // Configurar colunas da tabela
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        // Popular tabela
        atualizarTabela();

        // Listener para seleção na tabela
        contatosTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        preencherCampos(newSelection);
                    }
                }
        );
    }

    private void preencherCampos(Contato contato) {
        nomeTextField.setText(contato.getNome());
        telefoneTextField.setText(contato.getTelefone());
        emailTextField.setText(contato.getEmail());
        enderecoTextField.setText(contato.getEndereco());
    }

    @FXML
    private void handleAdicionar() {
        Contato novoContato = new Contato(
                nomeTextField.getText(),
                telefoneTextField.getText(),
                emailTextField.getText(),
                enderecoTextField.getText()
        );
        contatoDAO.adicionar(novoContato);
        atualizarTabela();
        limparCampos();
    }

    @FXML
    private void handleEditar() {
        Contato contatoSelecionado = contatosTableView.getSelectionModel().getSelectedItem();

        if (contatoSelecionado != null) {
            contatoSelecionado.setNome(nomeTextField.getText());
            contatoSelecionado.setTelefone(telefoneTextField.getText());
            contatoSelecionado.setEmail(emailTextField.getText());
            contatoSelecionado.setEndereco(enderecoTextField.getText());

            contatoDAO.atualizar(contatoSelecionado);
            atualizarTabela();
            limparCampos();
        }
    }

    @FXML
    private void handleExcluir() {
        Contato contatoSelecionado = contatosTableView.getSelectionModel().getSelectedItem();

        if (contatoSelecionado != null) {
            contatoDAO.removerPorId(contatoSelecionado.getId());
            atualizarTabela();
            limparCampos();
        }
    }

    @FXML
    private void handleLimpar() {
        limparCampos();
    }

    private void atualizarTabela() {
        contatosList = FXCollections.observableArrayList(contatoDAO.listarTodos());
        contatosTableView.setItems(contatosList);
    }

    private void limparCampos() {
        nomeTextField.clear();
        telefoneTextField.clear();
        emailTextField.clear();
        enderecoTextField.clear();
        contatosTableView.getSelectionModel().clearSelection();
    }
}
