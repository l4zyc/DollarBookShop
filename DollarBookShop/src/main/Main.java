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

public class Main extends Application{
	
	Scene scene;
	BorderPane mainLayout;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	private Data data = new Data();
	public static Stage mainStage;
	
	private double width = Screen.getPrimary().getBounds().getWidth();
	private double height = Screen.getPrimary().getBounds().getHeight();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Check if the User has logged in before
		mainStage = stage;
		
        String lastEmail = pref.get("lastEmail", null);
        if (lastEmail != null) {
            User user = data.getUserInstanceFromEmail(lastEmail);
            pref.remove(lastEmail);
            new HomeView(stage, user);
        } else {
            new LoginView(stage);
        }
		
        //Set the Dollar Book Shop Window
		stage.setTitle("Dollar Book Shop");
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setResizable(false);
		stage.show();
	}

}
