package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.Main;

public class RegisterView extends viewTemplate {
	
	private BorderPane mainLayout;
	private GridPane registerLayout;
	private Scene registerScene;
	private Label registerLbl;

	public RegisterView(Stage stage) {
		super(stage);
		start();
		arrangeComponent();
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		
		registerScene = new Scene(mainLayout, this.getWidth(), this.getHeight()); 
		
		registerLbl = new Label("Register");

	}
	
	@Override
	public void arrangeComponent() {
		//Set Font
		registerLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		mainLayout.setCenter(registerLbl);
		BorderPane.setAlignment(registerLbl, Pos.CENTER);
		this.getStage().setScene(registerScene);
	}
}
