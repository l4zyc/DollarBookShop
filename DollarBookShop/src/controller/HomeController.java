package controller;

import java.util.Optional;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.window.Window;
import main.Main;
import model.Product;
import util.func;
import view.HomeView;
import view.LoginView;
import view.addProductWindow;

public class HomeController {
	
	private HomeView view;
	private Product product;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	
	public HomeController(HomeView view) {
		this.view = view;
		setOnMouseClicked();
		setOnAction();
		setOnMouseEntered();
		setOnMouseExited();
	}
	
	public void setOnMouseClicked() {
		view.getProduct_table().setOnMouseClicked(e -> {
			TableSelectionModel<Product> model = view.getProduct_table().getSelectionModel();
			model.setSelectionMode(SelectionMode.SINGLE);
			product = model.getSelectedItem();
		});
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
					pref.remove("lastEmail");
					new LoginView(view.getStage());	
				}
			}
		});
		
		view.getAddCartBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(product != null) {
					new addProductWindow(product);
				} else {
					func.showAlert(AlertType.WARNING, "Warning", "You need to select one product");
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
