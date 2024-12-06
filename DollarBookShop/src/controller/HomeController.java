package controller;

import java.util.Optional;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.window.Window;
import main.Main;
import model.Product;
import model.User;
import util.func;
import view.CartView;
import view.HomeView;
import view.LoginView;
import view.addProductWindow;

public class HomeController {
	
	private HomeView view;
	private Product product;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	private User user;
	
	public HomeController(HomeView view, User user) {
		this.user = user;
		this.view = view;
		doubleClick();
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
	
	public void doubleClick() {
		view.getProduct_table().setRowFactory(tv -> {
			TableRow<Product> row = new TableRow<Product>();
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() == 2) {
					Product data = row.getItem();
					new addProductWindow(product, user);
				}
			});
			
			return row;
		});
	}	
	
	
	public void setOnAction() {
		view.getLogout().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Log Out");
				alert.setContentText("Do you want to log out ?");
				
				Optional op = alert.showAndWait();
				
				if(op.get().equals(ButtonType.OK)) {
					pref.remove("lastEmail");
					new LoginView(view.getStage());	
				}
			}
		});
		
		view.getCart().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				CartView cartView = new CartView(view.getStage(), user);
//				Scene cartScene = new Scene(cartView.getMainLayout());
//				view.getStage().setScene(cartScene);
				new CartView(view.getStage(),user);
			}
			
			
		});
		
		
		view.getAddCartBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(product != null) {
					new addProductWindow(product, user);
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
