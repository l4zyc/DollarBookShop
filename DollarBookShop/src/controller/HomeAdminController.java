package controller;

import java.util.Optional;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.Alert.AlertType;
import main.Main;
import model.Product;
import model.User;
import util.func;
import view.HomeViewAdmin;
import view.LoginView;

public class HomeAdminController extends Controller{
	private HomeViewAdmin view;
	private Product product;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	private User user;
	
	public HomeAdminController(HomeViewAdmin view, User user) {
		this.view = view;
		this.user = user;
		setOnMouseClicked();
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
		
		view.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(product != null) {
					Product selectedItem = view.getProduct_table().getSelectionModel().getSelectedItem();
					view.getProduct_table().getItems().remove(selectedItem);
					getData().removeProductFromSomeoneCart(product);
					getData().removeProduct(product);
					func.showAlert(AlertType.INFORMATION, "Success", "Product removed sucessfully");
					product = null;
				} else {
					//lanjutkan
				}
				
			}
			
		});
	}
	
	public void setOnMouseClicked() {
		view.getProduct_table().setOnMouseClicked(e->{
			TableSelectionModel<Product> model = view.getProduct_table().getSelectionModel();
			model.setSelectionMode(SelectionMode.SINGLE);
			product = model.getSelectedItem();
			view.getNameTF().setText(product.getName());
			view.getGenreTF().setText(product.getGenre());
			view.getStockSpinner().getValueFactory().setValue(product.getStock());
			view.getPriceTF().setText(String.valueOf(product.getPrice()));
		});
	}
	

}
