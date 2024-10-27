package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

public class HomeView extends HomeViewTemplate{

	private Scene scene;
	private BorderPane mainLayout;
	private User user;
	
	private Label welcomeLbl;
	private MenuBar mb; 
	private Menu actionMenu;
	private MenuItem home, cart, logout;
	
	public HomeView(Stage stage, User user) {
		super(stage, user);
		this.user = user;
		
		start();
		arrangeComponent();
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		scene = new Scene(mainLayout, width, height);
		
		welcomeLbl = new Label(String.format("Welcome, %s", user.getUsername()));
		mb = new MenuBar();
		actionMenu = new Menu("Action");
		home = new MenuItem("Home");
		cart = new MenuItem("Cart");
		logout = new MenuItem("Log Out");
	}

	@Override
	public void arrangeComponent() {
		// TODO Auto-generated method stub
		
		welcomeLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		mb.getMenus().add(actionMenu);
		actionMenu.getItems().add(home);
		actionMenu.getItems().add(cart);
		actionMenu.getItems().add(logout);
		
		mainLayout.setTop(mb);
		mainLayout.setCenter(welcomeLbl);
		
		this.getStage().setScene(scene);
	}

	

}
