package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class viewTemplate {
	
	private Stage currStage;
	
	public viewTemplate(Stage stage) {
		this.currStage = stage;
	}
	
	public abstract void start();

	public Stage getCurrStage() {
		return currStage;
	}

	public void setCurrStage(Stage currStage) {
		this.currStage = currStage;
	}


}
