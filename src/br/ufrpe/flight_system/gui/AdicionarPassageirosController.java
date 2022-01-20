package br.ufrpe.flight_system.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.flight_system.beans.Passageiros;
import br.ufrpe.flight_system.exceptions.AlreadyExistException;
import br.ufrpe.flight_system.negocio.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdicionarPassageirosController implements Initializable{
	@FXML
	private Button backBttn, addBttn;
	@FXML
	private TextField namePass, surnamePass, cpfPass, passportPass;

	@FXML
	public void enviarPass(ActionEvent event) {
		String nome, surname, cpfS, passportS;

		nome = namePass.getText();
		surname = surnamePass.getText();
		cpfS = cpfPass.getText();
		passportS = passportPass.getText();

		if(!nome.equals("") && !surname.equals("") && !cpfS.equals("") && !passportS.equals("") ){

			try{
				long cpf = Long.parseLong(cpfS);
				long passport = Long.parseLong(passportS);

				Passageiros pass = new Passageiros(nome, surname, cpf, passport);
				try{
					Stage stage = (Stage) addBttn.getScene().getWindow();
					Fachada.getInstance().adicionarPassageiro(pass);
					Fachada.getInstance().salvarArquivoPassageiros();
					System.out.println("Cadastrado!");
					stage.close();
				}catch(AlreadyExistException e){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao cadastrar!");
					alert.setContentText("Passageiro já cadastrado!");
					alert.showAndWait();

				}
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
