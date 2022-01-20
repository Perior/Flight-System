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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListaPassageirosController implements Initializable{
	@FXML
	private Button backBttn, addBttn;
	@FXML
	private TableView<Passageiros> listaPass;
	@FXML
	private TableColumn<Passageiros, String> name, surname;
	@FXML
	private TableColumn<Passageiros, Long> cpf, passaporte;
	
	private List<Passageiros> listP = new ArrayList<>();
	private ObservableList<Passageiros> obsPassList;
	private Voos vooValue;
	private int i;
	
	@FXML
	public void criar(ActionEvent event) {
		Passageiros p = listaPass.getSelectionModel().getSelectedItem();
		UniqueRandomNumbers number = new UniqueRandomNumbers(this.vooValue.getTipoAeronave().getNumAssentos());
		if(p != null) {
			try {
				Stage stage = (Stage) addBttn.getScene().getWindow();
				Fachada.getInstance().criarBilhete(p, vooValue, number.getList().get(i));
				Fachada.getInstance().salvarArquivosBilhetes();
				System.out.println("Criado!");
				i++;
				stage.close();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro!");
			alert.setContentText("Selecione um Passageiro!");
			alert.showAndWait();
		}
	}
	
	public void voltar(ActionEvent event) {
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listP = Fachada.getInstance().listarPassageiros();
		obsPassList = FXCollections.observableArrayList(listP);

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		passaporte.setCellValueFactory(new PropertyValueFactory<>("passaporte"));

		listaPass.setItems(obsPassList);
	}
	
	public void setVooValue(Voos v) {
		this.vooValue = v;
	}
}
