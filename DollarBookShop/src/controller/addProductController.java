package controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import util.func;
import view.addProductWindow;

public class addProductController extends Controller{

	private addProductWindow view;
	
	public addProductController(addProductWindow view) {
		this.view = view;
		setOnAction();
	}
	
	public void setOnAction() {
		view.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(isQtyless(view.getQty().getValue())) {
					func.showAlert(AlertType.WARNING, "Warning", "Quantity is less than 0");
					return;
				}
				getData().insertItemtoCart(view.getUser(), view.getProduct(), view.getQty().getValue());
				
				func.showAlert(AlertType.INFORMATION, "Cart", "Item Added");
				view.getStage().close();
			}
		});
	}
	
	public boolean isQtyless(Integer qty) {
		if(qty <= 0) {
			return true;
		}
		
		return false;
	}
}
