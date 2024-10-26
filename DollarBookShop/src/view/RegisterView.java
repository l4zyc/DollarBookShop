package view;

import java.time.LocalDate;

import controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.Main;

public class RegisterView extends viewTemplate {
	
	private BorderPane mainLayout;
	private GridPane registerLayout;
	private Scene registerScene;
	private VBox buttonLayout;
	
	private Label registerLbl, emailLbl, usernameLbl, passwdLbl, confPasswdLbl,
	DOBLbl, accountExistLbl;
	
	private TextField emailField, usernameField, passwdField, confPasswdField;
	
	private DatePicker DOBPicker;
	
	private Button signUpBtn;

	public RegisterView(Stage stage) {
		super(stage);
		start();
		new RegisterController(this);
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		
		registerScene = new Scene(mainLayout, this.getWidth(), this.getHeight()); 
		
		registerLayout = new GridPane();
		
		registerLbl = new Label("Register");
		
		emailLbl = new Label("Email");
		emailField = new TextField();
		
		usernameLbl = new Label("Username");
		usernameField = new TextField();
		
		passwdLbl = new Label("Password");
		passwdField = new TextField();
		
		confPasswdLbl = new Label("Confirm Password");
		confPasswdField = new TextField();
		
		DOBLbl = new Label("Date of Birth");
		DOBPicker = new DatePicker();
		
		signUpBtn = new Button("Sign Up");
		accountExistLbl = new Label("Already Have an Account? Login Here!");
		
		buttonLayout = new VBox();
		
		arrangeComponent();
	}

	@Override
	public void arrangeComponent() {
		// TODO Auto-generated method stub
		registerLbl = new Label("Register");
		registerLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		registerLayout.add(emailLbl, 0, 0);
		registerLayout.add(emailField, 0, 1);
		registerLayout.add(usernameLbl, 0, 2);
		registerLayout.add(usernameField, 0, 3);
		registerLayout.add(passwdLbl, 0, 4);
		registerLayout.add(passwdField, 0, 5);		
		registerLayout.add(confPasswdLbl, 0, 6);		
		registerLayout.add(confPasswdField, 0, 7);		
		registerLayout.add(DOBLbl, 0, 8);		
		registerLayout.add(DOBPicker, 0, 9);
		
		buttonLayout.getChildren().add(signUpBtn);
		buttonLayout.getChildren().add(accountExistLbl);	
		
		buttonLayout.setAlignment(Pos.CENTER);
		
		registerLayout.add(buttonLayout, 0, 10);
		registerLayout.setVgap(10);
		
		//Set Prompt TExt for Text Fie;d
		emailField.setPromptText("Email Address");
		usernameField.setPromptText("Username");
		passwdField.setPromptText("Password");
		confPasswdField.setPromptText("Confirm Password");
		
		//Set TextField and DatePicker Width and Height
		emailField.setMinWidth(300);
		emailField.setMinHeight(35);
		
		usernameField.setMinHeight(35);
		passwdField.setMinHeight(35);
		confPasswdField.setMinHeight(35);
		DOBPicker.setMinHeight(35);
		
		//Font Size
		emailLbl.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		usernameLbl.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		passwdLbl.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		confPasswdLbl.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		DOBLbl.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		
		signUpBtn.setMinWidth(300);
		signUpBtn.setMinHeight(40);
		signUpBtn.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		
		buttonLayout.setSpacing(10);
		accountExistLbl.setTextFill(Color.BLUE);
		
		mainLayout.setTop(registerLbl);
		
		mainLayout.setCenter(registerLayout);

		registerLayout.setAlignment(Pos.CENTER);
		mainLayout.setPadding(new Insets(150));
		
		BorderPane.setAlignment(registerLbl, Pos.CENTER);
		
		this.getStage().setScene(registerScene);
	}

	public BorderPane getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(BorderPane mainLayout) {
		this.mainLayout = mainLayout;
	}

	public GridPane getRegisterLayout() {
		return registerLayout;
	}

	public void setRegisterLayout(GridPane registerLayout) {
		this.registerLayout = registerLayout;
	}

	public Scene getRegisterScene() {
		return registerScene;
	}

	public void setRegisterScene(Scene registerScene) {
		this.registerScene = registerScene;
	}

	public VBox getButtonLayout() {
		return buttonLayout;
	}

	public void setButtonLayout(VBox buttonLayout) {
		this.buttonLayout = buttonLayout;
	}

	public Label getRegisterLbl() {
		return registerLbl;
	}

	public void setRegisterLbl(Label registerLbl) {
		this.registerLbl = registerLbl;
	}

	public Label getEmailLbl() {
		return emailLbl;
	}

	public void setEmailLbl(Label emailLbl) {
		this.emailLbl = emailLbl;
	}

	public Label getUsernameLbl() {
		return usernameLbl;
	}

	public void setUsernameLbl(Label usernameLbl) {
		this.usernameLbl = usernameLbl;
	}

	public Label getPasswdLbl() {
		return passwdLbl;
	}

	public void setPasswdLbl(Label passwdLbl) {
		this.passwdLbl = passwdLbl;
	}

	public Label getConfPasswdLbl() {
		return confPasswdLbl;
	}

	public void setConfPasswdLbl(Label confPasswdLbl) {
		this.confPasswdLbl = confPasswdLbl;
	}

	public Label getDOBLbl() {
		return DOBLbl;
	}

	public void setDOBLbl(Label dOBLbl) {
		DOBLbl = dOBLbl;
	}

	public Label getAccountExistLbl() {
		return accountExistLbl;
	}

	public void setAccountExistLbl(Label accountExistLbl) {
		this.accountExistLbl = accountExistLbl;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public void setEmailField(TextField emailField) {
		this.emailField = emailField;
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(TextField usernameField) {
		this.usernameField = usernameField;
	}

	public TextField getPasswdField() {
		return passwdField;
	}

	public void setPasswdField(TextField passwdField) {
		this.passwdField = passwdField;
	}

	public TextField getConfPasswdField() {
		return confPasswdField;
	}

	public void setConfPasswdField(TextField confPasswdField) {
		this.confPasswdField = confPasswdField;
	}

	public DatePicker getDOBPicker() {
		return DOBPicker;
	}

	public void setDOBPicker(DatePicker dOBPicker) {
		DOBPicker = dOBPicker;
	}

	public Button getSignUpBtn() {
		return signUpBtn;
	}

	public void setSignUpBtn(Button signUpBtn) {
		this.signUpBtn = signUpBtn;
	}
}
