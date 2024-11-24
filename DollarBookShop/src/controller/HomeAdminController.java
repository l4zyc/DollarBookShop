package controller;

import java.util.Optional;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import main.Main;
import model.Product;
import model.User;
import view.HomeViewAdmin;
import view.LoginView;

public class HomeAdminController {
	private HomeViewAdmin view;
	private Product product;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	private User user;
	
	public HomeAdminController(HomeViewAdmin view, User user) {
		this.view = view;
		this.user = user;
		
		setOnAction();
	}
	
	public void setOnAction() {
		view.getLogout().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Log OUt");
				alert.setContentText("Do you want to log out ?");
				
				Optional op = alert.showAndWait();
				if(op.get().equals(ButtonType.OK)) {
					pref.remove("lasteEmail");
					new LoginView(view.getStage());
				}
				
			}
		});
	}
	

}
