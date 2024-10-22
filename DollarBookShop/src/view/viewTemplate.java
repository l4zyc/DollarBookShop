package view;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class viewTemplate {
	
	private Stage currStage;
	private Double width;
	private Double height;
	
	public viewTemplate(Stage stage) {
		this.currStage = stage;
		this.width = Screen.getPrimary().getBounds().getWidth();
		this.height = Screen.getPrimary().getBounds().getHeight();
	}
	
	public abstract void start();

	public Stage getCurrStage() {
		return currStage;
	}

	public void setCurrStage(Stage currStage) {
		this.currStage = currStage;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	
	

}
