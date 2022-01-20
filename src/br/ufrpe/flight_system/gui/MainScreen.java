package br.ufrpe.flight_system.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainScreen extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
			Scene scene = new Scene(root);
			stage.setResizable(true);
			stage.setScene(scene);
			Image icon = new Image("/br/ufrpe/flight_system/gui/resources/plane_Icon.png");
			stage.getIcons().add(icon);
			stage.setTitle("Flight System Management");
			stage.show();

		}catch(Exception e) {
			System.out.println("Erro!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
