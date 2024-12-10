package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.User;
import util.Data;

public abstract class HomeViewAdminTemplate extends viewTemplate{

	protected User user;
	protected MenuBar mb;
	protected Menu actionMenu;
	protected MenuItem logout;
	private Data data = new Data();
	
	public HomeViewAdminTemplate(Stage stage, User user) {
		super(stage);
		this.user = user;
		
		initMenu();
	}
	
	public void initMenu() {
		mb = new MenuBar();
		actionMenu = new Menu("Action");
		logout = new MenuItem("Log Out");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public MenuItem getLogout() {
		return logout;
	}

	public void setLogout(MenuItem logout) {
		this.logout = logout;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	


}
