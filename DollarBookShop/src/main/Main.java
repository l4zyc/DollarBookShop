package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application{
	
	Scene scene;
	BorderPane mainLayout;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		new LoginView(stage);

        stage.setResizable(false);
		stage.setTitle("Dollar Book Shop");
		stage.show();
	}

}
