package view;

import controller.LoginController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Main;

public class LoginView extends viewTemplate{
	
	private Label loginLbl, emailLbl, passwordLbl, noAccountLbl;
	private BorderPane mainLayout;
	private TextField emailField;
	PasswordField passwdField;
	GridPane loginLayout;
	Scene loginScene;
	Button signInBtn;

	public LoginView(Stage stage) {
		super(stage);
		
		start();
		new LoginController(this);
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		loginLayout = new GridPane();
		
		loginScene = new Scene(mainLayout, width, height);
		
		loginLbl = new Label("Login");
		emailLbl = new Label("Email");
		passwordLbl = new Label("Password");
		noAccountLbl = new Label("Don't have an Account ? Register Here!");
		
		emailField = new TextField();
		passwdField = new PasswordField();
		
		emailField.setPromptText("Email Address");
		passwdField.setPromptText("Password");
		
		signInBtn = new Button("Sign in");
	
		arrangeComponent();
		
		this.getStage().setScene(loginScene);
	}
	
	@Override
	public void arrangeComponent() {
		loginLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		emailLbl.setFont(Font.font("Arial" , FontWeight.BOLD, 16));
		passwordLbl.setFont(Font.font("Arial" , FontWeight.BOLD, 16));
		
		mainLayout.setTop(loginLbl);
		mainLayout.setCenter(loginLayout);
		
		loginLayout.add(emailLbl , 0, 0);
		loginLayout.add(emailField, 0, 1);
		loginLayout.add(passwordLbl, 0, 2);
		loginLayout.add(passwdField, 0, 3);
		loginLayout.add(signInBtn, 0, 4);
		loginLayout.add(noAccountLbl, 0, 5);
		
		GridPane.setHalignment(signInBtn, HPos.CENTER);
		signInBtn.setBackground(new Background(
					new BackgroundFill(Color.AQUA, null, null)
				));
		
		GridPane.setHalignment(noAccountLbl, HPos.CENTER);
		noAccountLbl.setTextFill(Color.BLUE);
		
		emailField.setMinWidth(300);
		emailField.setMinHeight(35);
		
		passwdField.setMinWidth(300);
		passwdField.setMinHeight(35);
		signInBtn.setMinWidth(300);
		signInBtn.setMinHeight(35);
		
		loginLayout.setVgap(10);
		mainLayout.setPadding(new Insets(280));
		
		BorderPane.setAlignment(loginLbl, Pos.CENTER);
		loginLayout.setAlignment(Pos.CENTER);	
	}

	public Label getLoginLbl() {
		return loginLbl;
	}

	public void setLoginLbl(Label loginLbl) {
		this.loginLbl = loginLbl;
	}

	public Label getEmailLbl() {
		return emailLbl;
	}

	public void setEmailLbl(Label emailLbl) {
		this.emailLbl = emailLbl;
	}

	public Label getPasswordLbl() {
		return passwordLbl;
	}

	public void setPasswordLbl(Label passwordLbl) {
		this.passwordLbl = passwordLbl;
	}

	public Label getNoAccountLbl() {
		return noAccountLbl;
	}

	public void setNoAccountLbl(Label noAccountLbl) {
		this.noAccountLbl = noAccountLbl;
	}

	public BorderPane getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(BorderPane mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public void setEmailField(TextField emailField) {
		this.emailField = emailField;
	}

	public PasswordField getPasswdField() {
		return passwdField;
	}

	public void setPasswdField(PasswordField passwdField) {
		this.passwdField = passwdField;
	}

	public GridPane getLoginLayout() {
		return loginLayout;
	}

	public void setLoginLayout(GridPane loginLayout) {
		this.loginLayout = loginLayout;
	}

	public Button getSignInBtn() {
		return signInBtn;
	}

	public void setSignInBtn(Button signInBtn) {
		this.signInBtn = signInBtn;
	}

	
}

