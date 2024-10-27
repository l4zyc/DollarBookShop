package view;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class viewTemplate {
	
	private Stage stage;
	protected Double width;
	protected Double height;
	
	public viewTemplate(Stage stage) {
		this.stage = stage;
	}
	
	public abstract void start();
	public abstract void arrangeComponent();

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
