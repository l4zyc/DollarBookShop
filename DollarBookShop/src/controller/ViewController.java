package controller;

import java.util.Optional;
import java.util.prefs.Preferences;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.window.Window;
import main.Main;
import model.CartItem;
import model.Product;
import model.User;
import util.Data;
import util.func;
import view.CartView;
import view.HomeView;
import view.LoginView;
import view.addProductWindow;

public class ViewController extends Controller{
	
	private CartView view;
	private CartItem cart;
	private Preferences pref = Preferences.userNodeForPackage(Main.class);
	private User user;
	private Data data = new Data();

//	private ObservableList<CartItem> selectedItems;
	
	public ViewController(CartView view, User user) {
		this.user = user;
		this.view = view;
		
//		selectedItems = FXCollections.observableArrayList();

		setOnMouseClicked();
		setOnAction();
	}
	
	public void setOnMouseClicked() {
		view.getCartTable().setOnMouseClicked(e -> {
			TableSelectionModel<CartItem> carts = view.getCartTable().getSelectionModel();
			carts.setSelectionMode(SelectionMode.SINGLE);

			//coba-coba:
//			selectedItems.clear();
//			selectedItems.setAll(carts.getSelectedItems());
			
			cart = carts.getSelectedItem();
			
			//view.getQuantitySpinner().getValueFactory().setValue(cart.getQty());
			
		
	        Product product = data.getProductId(cart.getProductId());
	        
	        if(product!=null) {
	        	int maxstock = product.getStock();
	        	int currentQty = data.getItemQty(user, product);
	        	
	        	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, maxstock, currentQty);
	        	view.getQuantitySpinner().setValueFactory(valueFactory);
	        }


	        
//			if(!selectedItems.isEmpty()) {
//				CartItem firstSelectedItem = selectedItems.get(0);
//				view.getQuantitySpinner().getValueFactory().setValue(firstSelectedItem.getQty());
//			} else {
//				view.getQuantitySpinner().getValueFactory().setValue(0);
//			}
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
		
		
		view.getHome().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				HomeView homeView = new HomeView(view.getStage(), user);
//				Scene homeScene = new Scene(homeView.getMainLayout());
//				view.getStage().setScene(homeScene);
				new HomeView(view.getStage(), user);
				
			}
			
			
		});
		
		
		
		view.getRemoveBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(cart != null) {
					CartItem selectedItem = view.getCartTable().getSelectionModel().getSelectedItem();
					view.getCartTable().getItems().remove(selectedItem);
					getData().deleteCartItem(selectedItem, user);
					func.showAlert(AlertType.INFORMATION, "Success", "Item removed successfully");
					cart = null;
				}else {
					func.showAlert(AlertType.WARNING, "Warning", "No item selected to remove.");
				}
				
//				if(selectedItems.size() == 1) {
//					CartItem selectedItem = selectedItems.get(0);
//					view.getCartTable().getItems().remove(selectedItem);
//					getData().deleteCartItem(selectedItem, user);
//					func.showAlert(AlertType.INFORMATION, "Success", "Item removed successfully");
//					cart = null;
//				}else if (selectedItems.size()>1){
//					func.showAlert(AlertType.WARNING, "Warning", "Must select only 1 item to remove.");
//				}else {
//					func.showAlert(AlertType.WARNING, "Warning", "No item selected to remove.");
//				}
				
			}
			
		});
		
		view.getUpdateBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(cart != null) {
					int newQty = view.getQuantitySpinner().getValue();
					System.out.println("New : " + newQty);
					if(newQty <= 0) {
						func.showAlert(AlertType.WARNING, "Invalid quantity", "Quantity must be more than 0.");
						return;
					}
					
					cart.setQty(newQty);
					cart.setTotal(cart.getQty()*cart.getPrice());
					getData().setQtyCart(user, cart.getProductId(), newQty);
					
					view.getCartTable().refresh();
					func.showAlert(AlertType.INFORMATION, "Success", "The book's quantity has been updated successfully.");
					
				}else {
					func.showAlert(AlertType.WARNING, "No Selection", "Please select a book to update");
				}
				
//				if(selectedItems.size() == 1) {
//					int newQty = view.getQuantitySpinner().getValue();
//					System.out.println("New : " + newQty);
//					if(newQty <= 0) {
//						func.showAlert(AlertType.WARNING, "Invalid quantity", "Quantity must be more than 0.");
//						return;
//					}
//					CartItem selectedItem = selectedItems.get(0);
//					
//					selectedItem.setQty(newQty);
//					selectedItem.setTotal(selectedItem.getQty()*selectedItem.getPrice());
//					getData().setQtyCart(user, selectedItem.getProductId(), newQty);
//					
//					view.getCartTable().refresh();
//					func.showAlert(AlertType.INFORMATION, "Success", "The book's quantity has been updated successfully.");
//					
//				}else if (selectedItems.size()>1){
//					func.showAlert(AlertType.WARNING, "Warning", "Must select only 1 book to update.");
//				}else  {
//					func.showAlert(AlertType.WARNING, "No Selection", "Please select a book to update");
//				}
				
			}
			
			
		});
		
		view.getCheckoutBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(cart != null) {
					CartItem selectedItem = view.getCartTable().getSelectionModel().getSelectedItem();
					getData().insertTransaction(user, selectedItem);
					view.getCartTable().getItems().remove(selectedItem);	
					getData().deleteCartItem(selectedItem, user);
					
					view.getCartTable().refresh();
					
					func.showAlert(AlertType.INFORMATION, "Success", "Checkout successful for the selected item!");
					return;
				}else {
					func.showAlert(AlertType.WARNING, "No Selection", "Please select a book to checkout");
				}
				
//				if(!selectedItems.isEmpty()) {
//					CartItem selectedItem = view.getCartTable().getSelectionModel().getSelectedItem();
//					getData().insertTransactionVersiAlternative(user, selectedItems);
//					view.getCartTable().getItems().removeAll(selectedItems);	
//					getData().deleteCartItemVersiAlternatve(selectedItems, user);
//					
//					view.getCartTable().refresh();
//					
//					func.showAlert(AlertType.INFORMATION, "Success", "Checkout successful for the selected item!");
//					return;
//				}else {
//					func.showAlert(AlertType.WARNING, "No Selection", "Please select a book to checkout");
//				}
				
				
				
			}
			
		});
		
	}


}
