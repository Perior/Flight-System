package br.ufrpe.flight_system.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlterarPassageirosController implements Initializable{
	@FXML
	private Button backBttn, attBttn;
	@FXML
	private TextField namePass, surnamePass;

	private int indexAtt;
	private Passageiros oldPass;

	@FXML
	public void enviarPass(ActionEvent event) {
		String nome, surname;

		nome = namePass.getText();
		surname = surnamePass.getText();

		if(!nome.equals("") && !surname.equals("")){
			//TODO aviso.setText("");

			try{
				Passageiros pass = new Passageiros(nome, surname, this.oldPass.getCpf(), this.oldPass.getPassaporte());

				Stage stage = (Stage) attBttn.getScene().getWindow();
				Fachada.getInstance().alterarPassageiro(indexAtt, pass);
				Fachada.getInstance().salvarArquivoPassageiros();
				System.out.println("Alterado!");
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setIndexAtt(int index) {
		this.indexAtt = index;
	}

	public void setOldPass(Passageiros item) {
		this.oldPass = item;
	}

	public void voltarInicio(ActionEvent event) {
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}
}
