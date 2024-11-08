package main;

import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.User;
import util.Data;
import view.HomeView;
import view.LoginView;
import view.RegisterView;

public class Main extends Application{
	
	Scene scene;
	BorderPane mainLayout;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	private Data data = new Data();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {	
        String lastEmail = pref.get("lastEmail", null);
        if (lastEmail != null) {
            User user = data.getUserInstanceFromEmail(lastEmail);
            new HomeView(stage, user);
        } else {
            new LoginView(stage);
        }
		
		stage.setTitle("Dollar Book Shop");
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.show();
	}

}
