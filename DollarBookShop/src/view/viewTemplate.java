package view;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class viewTemplate {
	
	private Stage stage;
	private Double width;
	private Double height;
	
	public viewTemplate(Stage stage) {
		this.stage = stage;
		Scene previousScene = getStage().getScene();
        if (previousScene != null) {
            this.width = previousScene.getWidth();
            this.height = previousScene.getHeight();
        } else {
            this.width = Screen.getPrimary().getBounds().getWidth();
            this.height = Screen.getPrimary().getBounds().getHeight();
        }
	}
	
	public abstract void start();

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
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
