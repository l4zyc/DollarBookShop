package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application{
	
	Scene scene;
	BorderPane mainLayout;
	
	public void init() {
		mainLayout = new BorderPane();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		init();
		
	
		LoginView loginView = new LoginView(stage);
	
		stage.setTitle("Dollar Book Shop");
		stage.setResizable(false);
		stage.show();
	}

}
