package view;

import controller.addProductController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.labs.internal.scene.control.skin.window.DefaultWindowIconSkin;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.labs.scene.control.window.WindowIcon;
import model.Product;
import model.User;

public class addProductWindow {

	Stage stage;
	Scene scene;
	
	StackPane root;
	Window window;
	GridPane mainLayout, lblLayout;
	
	Label nameLbl, genreLbl, priceLbl, nameItemLbl, genreItemLbl, priceItemLbl;
	Spinner<Integer> qty;
	Button addBtn;
	
	Product product;
	User user;

	public addProductWindow(Product product, User user) {
		this.product = product;
		this.user = user;
		start();
		arrangeComponent();
		
		new addProductController(this);
	}

	public void start() {
		stage = new Stage();
		
		window = new Window();
		
		root = new StackPane();
		
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
		root.getChildren().add(window);
		
		lblLayout.add(nameLbl, 0, 0);
		lblLayout.add(nameItemLbl, 1, 0);
		
		lblLayout.add(genreLbl, 0, 1);
		lblLayout.add(genreItemLbl, 1, 1);
	
		lblLayout.add(priceLbl, 0, 2);
		lblLayout.add(priceItemLbl, 1, 2);
		
		mainLayout.add(lblLayout, 0, 0);
		mainLayout.add(qty, 0, 1);
		mainLayout.add(addBtn, 0, 2);
		
		window.getContentPane().getChildren().add(mainLayout);
		window.setTitle("Add to Cart");
		CloseIcon close = new CloseIcon(window);
		window.getRightIcons().add(close);
		
		close.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}
		});
		
		scene = new Scene(root, 400, 300);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public StackPane getRoot() {
		return root;
	}

	public void setRoot(StackPane root) {
		this.root = root;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public GridPane getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(GridPane mainLayout) {
		this.mainLayout = mainLayout;
	}

	public GridPane getLblLayout() {
		return lblLayout;
	}

	public void setLblLayout(GridPane lblLayout) {
		this.lblLayout = lblLayout;
	}

	public Label getNameLbl() {
		return nameLbl;
	}

	public void setNameLbl(Label nameLbl) {
		this.nameLbl = nameLbl;
	}

	public Label getGenreLbl() {
		return genreLbl;
	}

	public void setGenreLbl(Label genreLbl) {
		this.genreLbl = genreLbl;
	}

	public Label getPriceLbl() {
		return priceLbl;
	}

	public void setPriceLbl(Label priceLbl) {
		this.priceLbl = priceLbl;
	}

	public Label getNameItemLbl() {
		return nameItemLbl;
	}

	public void setNameItemLbl(Label nameItemLbl) {
		this.nameItemLbl = nameItemLbl;
	}

	public Label getGenreItemLbl() {
		return genreItemLbl;
	}

	public void setGenreItemLbl(Label genreItemLbl) {
		this.genreItemLbl = genreItemLbl;
	}

	public Label getPriceItemLbl() {
		return priceItemLbl;
	}

	public void setPriceItemLbl(Label priceItemLbl) {
		this.priceItemLbl = priceItemLbl;
	}

	public Spinner<Integer> getQty() {
		return qty;
	}

	public void setQty(Spinner<Integer> qty) {
		this.qty = qty;
	}

	public Button getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(Button addBtn) {
		this.addBtn = addBtn;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
