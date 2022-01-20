package br.ufrpe.flight_system.gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import br.ufrpe.flight_system.beans.Voos;
import br.ufrpe.flight_system.enums.Aeronave;
import br.ufrpe.flight_system.enums.Cidade;
import br.ufrpe.flight_system.negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdicionarVoosController implements Initializable{

	@FXML
	private Button backBttn, addBttn;
	@FXML
	private TextField ciuVoo, horaSaida, minutoSaida, horaChegada, minutoChegada;
	@FXML
	private ChoiceBox<Cidade> origem, destino;
	@FXML
	private ChoiceBox<Aeronave> aeronave;
	@FXML
	private DatePicker dataSaida, dataChegada;

	@FXML
	public void enviarVoo(ActionEvent event) {
		String ciu, horaS, horaC, minutoS, minutoC;

		ciu = ciuVoo.getText();
		horaS = horaSaida.getText();
		horaC = horaChegada.getText();
		minutoS = minutoSaida.getText();
		minutoC = minutoChegada.getText();
		
		Cidade cO = origem.getSelectionModel().getSelectedItem();
		Cidade cD = destino.getSelectionModel().getSelectedItem();

		Aeronave aviao = aeronave.getSelectionModel().getSelectedItem();

		if(!ciu.equals("") && !horaS.equals("") && !horaC.equals("") && !minutoS.equals("") && !minutoC.equals("")
				&& dataSaida.getValue() != null && dataChegada.getValue() != null && origem.getValue() != null &&
				destino.getValue() != null){

			try{
				int codIU = Integer.parseInt(ciu);
				int outHour = Integer.parseInt(horaS);
				int outMin = Integer.parseInt(minutoS);
				int inHour = Integer.parseInt(horaC);
				int inMin = Integer.parseInt(minutoC);

				LocalTime horarioSaida = LocalTime.of(outHour, outMin);
				LocalTime horarioChegada = LocalTime.of(inHour, inMin);

				LocalDateTime saidaSemFuso = LocalDateTime.of(dataSaida.getValue(), horarioSaida);
				LocalDateTime chegadaSemFuso = LocalDateTime.of(dataChegada.getValue(), horarioChegada);

				ZonedDateTime dataSaida = ZonedDateTime.of(saidaSemFuso, cO.getZoneId());
				ZonedDateTime dataChegada = ZonedDateTime.of(chegadaSemFuso, cD.getZoneId());

				Voos v = new Voos(codIU, cO, cD, aviao, dataSaida, dataChegada);

				Stage stage = (Stage) addBttn.getScene().getWindow();
				Fachada.getInstance().adicionarVoo(v);
				Fachada.getInstance().salvarArquivosVoos();
				System.out.println("Cadastrado!");
				stage.close();

			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Campo(s) Vazio(s)!");
			alert.setContentText("Por favor preencha todos os campos.");
			alert.showAndWait();
		}
	}

	public void voltarInicio(ActionEvent event) {
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		origem.getItems().addAll(Cidade.values());
		destino.getItems().addAll(Cidade.values());
		aeronave.getItems().addAll(Aeronave.values());

	}

}
