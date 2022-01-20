package br.ufrpe.flight_system.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.negocio.Fachada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ListaAssentosController implements Initializable{
	@FXML
	private Button backBttn, addBttn, openListBttn;
	@FXML
	private ListView<Integer> listaAssentos; 

	private List<Integer> listB = new ArrayList<>();
	private ObservableList<Integer> obsBilheteList;

	private Voos vooValue;
	private Passageiros passValue;
	
	@FXML
	public void abrirLista(ActionEvent event) {
		openListBttn.setDisable(true);
		listB = this.vooValue.getFixed();

		obsBilheteList = FXCollections.observableArrayList(listB);

		listaAssentos.setItems(obsBilheteList);
	}

	@FXML
	public void confirmar(ActionEvent event) {
		int assento = listaAssentos.getSelectionModel().getSelectedItem();

		try {
			Stage stage = (Stage) addBttn.getScene().getWindow();
			Fachada.getInstance().criarBilhete(this.passValue, this.vooValue, assento);
			Fachada.getInstance().salvarArquivosBilhetes();
			System.out.println("Bilhete criado!");
			if(this.vooValue.getFixed().contains(assento)) {
				this.vooValue.getFixed().remove(assento);
			}
			stage.close();

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void voltar(ActionEvent event) {
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void setVooValue(Voos v) {
		this.vooValue = v;
	}

	public void setPassValue(Passageiros p) {
		this.passValue = p;
	}

}
