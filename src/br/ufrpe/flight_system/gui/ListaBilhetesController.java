package br.ufrpe.flight_system.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.flight_system.beans.Bilhete;
import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.exceptions.BilheteNotExist;
import br.ufrpe.flight_system.negocio.Fachada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListaBilhetesController implements Initializable{
	@FXML
	private Button backBttn, attbttn;
	@FXML
	private TableView<Bilhete> listaBilhete;
	@FXML
	private TableColumn<Passageiros, String> name, surname;
	@FXML
	private TableColumn<Passageiros, Long> cpf, passaporte;
	@FXML
	private TableColumn<Bilhete, Integer> numAssento;
	
	private List<Bilhete> listB = new ArrayList<>();
	private ObservableList<Bilhete> obsBilheteList;
	
	private Voos vooValue;
	
	@FXML
	public void atualizar(ActionEvent event) {
		try {
			listB = Fachada.getInstance().listarBilhetes(vooValue);
		} catch (BilheteNotExist e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar listar!");
			alert.setContentText("Nenhum bilhete registrado.");
			alert.showAndWait();
		}
		obsBilheteList = FXCollections.observableArrayList(listB);

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		passaporte.setCellValueFactory(new PropertyValueFactory<>("passaporte"));
		numAssento.setCellValueFactory(new PropertyValueFactory<>("numAssento"));

		listaBilhete.setItems(obsBilheteList);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void voltar(ActionEvent event) {
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}
	
	public void setVooValue(Voos v) {
		this.vooValue = v;
	}

}
