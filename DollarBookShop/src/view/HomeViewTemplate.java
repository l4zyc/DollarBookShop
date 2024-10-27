package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.User;

public abstract class HomeViewTemplate extends viewTemplate{

	private User user;
    protected MenuBar mb; 
    protected Menu actionMenu;
    protected MenuItem home, cart, logout;
	
	public HomeViewTemplate(Stage stage, User user) {
		super(stage);
		this.user = user;
		this.width = stage.getWidth();
		this.height = stage.getHeight();
	}


}
