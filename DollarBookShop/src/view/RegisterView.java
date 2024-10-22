package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterView extends viewTemplate {
	
	private BorderPane mainLayout;
	private GridPane registerLayout;
	private Label registerLbl, emailLbl, usernameLbl, passwordLbl, confPasswdLbl,
	DOBlbl, accountExistLbl;
	private Scene registerScene;

	public RegisterView(Stage stage) {
		super(stage);
		start();
	}

	@Override
	public void start() {
		// Use the current stage dimensions directly
		init();
		
		getCurrStage().setScene(registerScene);
	}
	
	public void init() {
		Double width = getCurrStage().getWidth();
		Double height = getCurrStage().getHeight();
		
		mainLayout = new BorderPane();
		registerLayout = new GridPane();
		registerScene = new Scene(mainLayout, width, height);
		
		registerLbl = new Label("Register");
		registerLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		emailLbl = new Label("Email");
		usernameLbl = new Label("Username");
		passwordLbl = new Label("Password");
		confPasswdLbl = new Label("Confirm Password");
		DOBlbl = new Label("Date of Birth");
		
		registerLayout.add(emailLbl, 0, 0);
		registerLayout.add(usernameLbl, 0, 1);
		registerLayout.add(passwordLbl, 0, 2);
		registerLayout.add(confPasswdLbl, 0, 3);
		registerLayout.add(DOBlbl, 0, 4);
		
		BorderPane.setAlignment(registerLbl, Pos.CENTER);
		
		mainLayout.setTop(registerLbl);
		mainLayout.setCenter(registerLayout);
	}

}
