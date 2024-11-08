package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.User;
import util.Data;

public abstract class HomeViewTemplate extends viewTemplate{

	protected User user;
    protected MenuBar mb; 
    protected Menu actionMenu;
    protected MenuItem home, cart, logout;
    private Data data = new Data();
	
	public HomeViewTemplate(Stage stage, User user) {
		super(stage);
		this.user = user;
		
		initMenu();
	}
	
	public void initMenu() {
        mb = new MenuBar();
        actionMenu = new Menu("Action");
        home = new MenuItem("Home");
        cart = new MenuItem("Cart");
        logout = new MenuItem("Log Out");
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public MenuBar getMb() {
		return mb;
	}

	public void setMb(MenuBar mb) {
		this.mb = mb;
	}

	public Menu getActionMenu() {
		return actionMenu;
	}

	public void setActionMenu(Menu actionMenu) {
		this.actionMenu = actionMenu;
	}

	public MenuItem getHome() {
		return home;
	}

	public void setHome(MenuItem home) {
		this.home = home;
	}

	public MenuItem getCart() {
		return cart;
	}

	public void setCart(MenuItem cart) {
		this.cart = cart;
	}

	public MenuItem getLogout() {
		return logout;
	}

	public void setLogout(MenuItem logout) {
		this.logout = logout;
	}
}
