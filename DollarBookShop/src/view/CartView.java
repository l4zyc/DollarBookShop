package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

public class CartView extends HomeViewTemplate{

	private Scene scene;
	private BorderPane mainLayout, cartLayout;
	//belum dilanjutkan (mirip dengan HomeView)
	

	public CartView(Stage stage, User user) {
		super(stage, user);
		this.user = user;
		
		start();
		arrangeComponent();
	}
	
	@Override
	public void start() {
		// Mirip dengan isi start HomeView
		
		
		//Untuk Menu Item!!! jangan lupa set home.setDisable(false) dan cart.setDisable(true); 
			//agar menu item nya bisa di tekan tetapi cart tidak bisa ditekan
	}

	@Override
	public void arrangeComponent() {
		// TODO Auto-generated method stub
		
	}

}
