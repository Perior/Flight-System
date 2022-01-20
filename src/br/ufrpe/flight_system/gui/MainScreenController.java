package br.ufrpe.flight_system.gui;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.exceptions.RemoveErrorException;
import br.ufrpe.flight_system.negocio.Fachada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainScreenController implements Initializable{
	@FXML
	private TableView<Passageiros> listaPass;
	@FXML
	private TableColumn<Passageiros, String> name, surname;
	@FXML
	private TableColumn<Passageiros, Long> cpf, passaporte;

	@FXML
	private TableView<Voos> listaVoo;
	@FXML
	private TableColumn<Voos, String> strOrigem, strDestino;
	@FXML
	private TableColumn<Voos, String> dtSaida, dtChegada;

	private List<Passageiros> listP = new ArrayList<>();
	private ObservableList<Passageiros> obsPassList;

	private List<Voos> listV = new ArrayList<>();
	private ObservableList<Voos> obsVooList;

	public void refreshPass() {
		listP = Fachada.getInstance().listarPassageiros();
		obsPassList = FXCollections.observableArrayList(listP);

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		passaporte.setCellValueFactory(new PropertyValueFactory<>("passaporte"));

		listaPass.setItems(obsPassList);
	}

	public void refreshVoo() {
		listV = Fachada.getInstance().listarVoos();
		obsVooList = FXCollections.observableArrayList(listV);

		strOrigem.setCellValueFactory(new PropertyValueFactory<>("strOrigem"));
		strDestino.setCellValueFactory(new PropertyValueFactory<>("strDestino"));
		dtSaida.setCellValueFactory(new PropertyValueFactory<>("dtSaida"));
		dtChegada.setCellValueFactory(new PropertyValueFactory<>("dtChegada"));

		listaVoo.setItems(obsVooList);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshPass();
		refreshVoo();
	}

	//Aba Passageiros
	public void openPassAddStage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarPassageiros.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
			stage.getIcons().add(icon);
			stage.setTitle("Flight System Management");
			stage.setScene(new Scene(root));
			stage.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					refreshPass();

				}
			});
			stage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void openPassEditStage(ActionEvent event) {
		Passageiros item = listaPass.getSelectionModel().getSelectedItem();
		int index = listaPass.getSelectionModel().getSelectedIndex();
		if(index != -1) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AlterarPassageiros.fxml"));
				Parent root = (Parent) loader.load();
				AlterarPassageirosController attPass = loader.getController();
				attPass.setIndexAtt(index);
				attPass.setOldPass(item);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
				stage.getIcons().add(icon);
				stage.setTitle("Flight System Management");
				stage.setScene(new Scene(root));
				stage.setOnHiding(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent event) {
						refreshPass();

					}
				});
				stage.show();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar alterar!");
			alert.setContentText("Selecione um Passageiro!");
			alert.showAndWait();
		}
	}

	public void removePass(ActionEvent event) {
		int item = listaPass.getSelectionModel().getSelectedIndex();
		if(item != -1) {
			try {
				Fachada.getInstance().removerPassageiro(item);
				Fachada.getInstance().salvarArquivoPassageiros();
				refreshPass();
			} catch(RemoveErrorException e){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Erro ao tentar remover!");
				alert.setContentText("Passageiro possui bilhete e  Voo ainda não decolou.");
				alert.showAndWait();
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar remover!");
			alert.setContentText("Nenhum Passageiro selecionado!");
			alert.showAndWait();
		}
	}

	//Aba Voos
	public void openVooAddStage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarVoos.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
			stage.getIcons().add(icon);
			stage.setTitle("Flight System Management");
			stage.setScene(new Scene(root));
			stage.setOnHiding(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					refreshVoo();

				}
			});
			stage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void openVooEditStage(ActionEvent event) {
		int index = listaVoo.getSelectionModel().getSelectedIndex();

		if(index != -1){
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AlterarVoos.fxml"));
				Parent root = (Parent) loader.load();
				AlterarVoosController attVoo = loader.getController();
				attVoo.setIndexAtt(index);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
				stage.getIcons().add(icon);
				stage.setTitle("Flight System Management");
				stage.setScene(new Scene(root));
				stage.setOnHiding(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent event) {
						refreshVoo();

					}
				});
				stage.show();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar alterar!");
			alert.setContentText("Selecione um Voo!");
			alert.showAndWait();
		}
	}

	public void removeVoo(ActionEvent event) {
		int item = listaVoo.getSelectionModel().getSelectedIndex();
		if(item != -1) {
			try {
				Fachada.getInstance().removerVoo(item);
				Fachada.getInstance().salvarArquivosVoos();
				refreshVoo();
			} catch(RemoveErrorException e){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Erro ao tentar remover!");
				alert.setContentText("Voo já decolou ou Bilhete emitido para o mesmo.");
				alert.showAndWait();
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar remover!");
			alert.setContentText("Nenhum Voo selecionado!");
			alert.showAndWait();
		}
	}

	public void openTicketList(ActionEvent event) {
		Voos v = listaVoo.getSelectionModel().getSelectedItem();
		if(v != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaBilhetes.fxml"));
				Parent root = (Parent) loader.load();
				ListaBilhetesController enviarVoo = loader.getController();
				enviarVoo.setVooValue(v);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
				stage.getIcons().add(icon);
				stage.setTitle("Flight System Management");
				stage.setScene(new Scene(root));
				stage.show();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar criar bilhete!");
			alert.setContentText("Selecione um Voo!");
			alert.showAndWait();
		}
		
	}

	public void openTicketCreator(ActionEvent event) {
		Voos v = listaVoo.getSelectionModel().getSelectedItem();
		if(v != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ListaPassageiros.fxml"));
				Parent root = (Parent) loader.load();
				ListaPassageirosController enviarVoo = loader.getController();
				enviarVoo.setVooValue(v);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
				stage.getIcons().add(icon);
				stage.setTitle("Flight System Management");
				stage.setScene(new Scene(root));
				stage.setOnHiding(new EventHandler<WindowEvent>() {

					@Override
					public void handle(WindowEvent event) {
						refreshVoo();

					}
				});
				stage.show();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Erro ao tentar criar bilhete!");
			alert.setContentText("Selecione um Voo!");
			alert.showAndWait();
		}
	}
}
