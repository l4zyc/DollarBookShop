package view;

import javafx.stage.Stage;
import model.User;

public abstract class HomeViewTemplate extends viewTemplate{

	private User user;
	
	public HomeViewTemplate(Stage stage, User user) {
		super(stage);
		this.user = user;
		this.width = stage.getWidth();
		this.height = stage.getHeight();
	}


}
