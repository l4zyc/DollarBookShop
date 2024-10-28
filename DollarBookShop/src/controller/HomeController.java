package controller;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import view.HomeView;
import view.LoginView;

public class HomeController {
	
	private HomeView view;
	
	public HomeController(HomeView view) {
		this.view = view;
		setOnAction();
		setOnMouseEntered();
		setOnMouseExited();
	}
	
	public void setOnAction() {
		view.getLogout().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Log Out");
				alert.setContentText("Do you want to Log out ?");
				
				Optional op = alert.showAndWait();
				
				if(op.get().equals(ButtonType.OK)) {
					new LoginView(view.getStage());	
				}
			}
		});
	}
	
	public void setOnMouseEntered() {
		view.getAddCartBtn().setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				view.getAddCartBtn().setBackground(new Background(
						new BackgroundFill(Color.BLACK, null, null)
						));
			}
		});
	}
	
	public void setOnMouseExited() {
		view.getAddCartBtn().setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
		        view.getAddCartBtn().setBackground(new Background(
		        		new BackgroundFill(Color.DARKGRAY, null, null)));
			}
			
		});
	}

}
