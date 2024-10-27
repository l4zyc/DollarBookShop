package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.LoginView;
import view.RegisterView;

public class Main extends Application{
	
	Scene scene;
	BorderPane mainLayout;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		new LoginView(stage);
		
		stage.setTitle("Dollar Book Shop");
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.show();
	}

}
