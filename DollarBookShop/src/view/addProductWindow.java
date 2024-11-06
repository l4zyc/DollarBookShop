package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;
import model.Product;

public class addProductWindow {

	Stage stage;
	Scene scene;
	
	Window window;
	GridPane mainLayout, lblLayout;
	
	Label nameLbl, genreLbl, priceLbl, nameItemLbl, genreItemLbl, priceItemLbl;
	Spinner<Integer> qty;
	Button addBtn;
	
	Product product;

	public addProductWindow(Product product) {
		this.product = product;
		start();
		arrangeComponent();
	}

	public void start() {
		stage = new Stage();
		
		window = new Window();
		
		mainLayout = new GridPane();
		lblLayout = new GridPane();
		
		nameLbl = new Label("Name:");
		genreLbl = new Label("Genre: ");
		priceLbl = new Label("Price: ");
		
		nameItemLbl = new Label(product.getName());
		genreItemLbl = new Label(product.getGenre());
		priceItemLbl = new Label(product.getPrice().toString());
		
		qty = new Spinner<Integer>(0, product.getStock(), 0);
		addBtn = new Button("Add");
	}

	public void arrangeComponent() {
		lblLayout.add(nameLbl, 0, 0);
		lblLayout.add(nameItemLbl, 1, 0);
		
		lblLayout.add(genreLbl, 0, 1);
		lblLayout.add(genreItemLbl, 1, 1);
	
		lblLayout.add(priceLbl, 0, 2);
		lblLayout.add(priceItemLbl, 1, 2);
		
		mainLayout.add(lblLayout, 0, 0);
		mainLayout.add(qty, 0, 1);
		mainLayout.add(addBtn, 0, 2);
		

       
		scene = new Scene(mainLayout);
		stage.setScene(scene);
		stage.show();
	}

}
