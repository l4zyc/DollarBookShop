package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterView extends viewTemplate {
	
	private BorderPane mainLayout;
	private Scene registerScene;

	public RegisterView(Stage stage) {
		super(stage);
		start();
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		Scene previousScene = getCurrStage().getScene();
		
		registerScene = new Scene(mainLayout, this.getWidth(), this.getHeight()); 
		
		Label registerLbl = new Label("Register");
		registerLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		mainLayout.setCenter(registerLbl);
		BorderPane.setAlignment(registerLbl, Pos.CENTER);
		
		this.getCurrStage().setScene(registerScene);
	}
}
