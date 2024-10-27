package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeView extends viewTemplate{

	private Scene scene;
	private BorderPane mainLayout;
	
	public HomeView(Stage stage) {
		super(stage);
		
		start();
		arrangeComponent();
		
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		scene = new Scene(mainLayout);
	}

	@Override
	public void arrangeComponent() {
		// TODO Auto-generated method stub
		
		this.getStage().setScene(scene);
	}

	

}
