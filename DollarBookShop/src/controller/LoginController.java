package controller;

import java.sql.Date;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.User;
import util.Connect;
import util.func;
import view.LoginView;
import view.RegisterView;

public class LoginController {
	
	//JDBC Connect
	private Connect connect = Connect.getInstance();
	
	//Constructor
	public LoginController(LoginView view) {
		view.getSignInBtn().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				validateLogin(view);
			}
		});
		
		view.getNoAccountLbl().setOnMouseClicked(e -> 
					new RegisterView(view.getStage())
				);
		
		keyEventHandler(view);
	}
	
	//Cek Role dari User antara Customer or Admin
	public Integer checkRole(String role) {
		if(role.equals("Customer")) {
			return 0;
		} else if(role.equals("Admin")) {
			return 1;
		}
		
		return -1;
	}
	
	//Validasi Login
	public void validateLogin(LoginView view) {
		String email = view.getEmailField().getText();
		String password = view.getPasswdField().getText();
		
		ArrayList<User> users = getUserListData();
		
		for(int i = 0; i < users.size(); i++) {
			if((email.equals(users.get(i).getEmail())
					&& password.equals(users.get(i).getPassword())) 
					) {
				func.showAlert(AlertType.INFORMATION, "Information", "Logged In !");
				return;
			}
		}
	
		func.showAlert(AlertType.WARNING, "Warning", "Invalid Credentials!");
	}
	
	//Ambil data user dari mySQL Server kemudian di simpan dalam ArrayList 
	public ArrayList<User> getUserListData() {
		ArrayList<User> users = new ArrayList<User>();
		String query = "SELECT * FROM users";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String UserID = connect.rs.getString("UserID");
				String Email = connect.rs.getString("Email");
				String Username = connect.rs.getString("Username");
				String Password = connect.rs.getString("Password");
				Date DOB = connect.rs.getDate("DOB");
				String Role = connect.rs.getString("Role");
				
				User user = new User(UserID, Email, Username, Password, DOB, Role);
				
				users.add(user);
			}
		} catch (Exception e) {
			
		}
		return users;
	}
	
	//Validasi Email (Ex: example@gmail.com)
	public boolean validateEmail(String email) {
		Integer atIndex = email.indexOf('@');
		
		if(atIndex == -1) {
			return false;
		}
		
		Integer dotIndex = email.indexOf('.', atIndex);
		Integer domainIndex = email.indexOf("gmail", atIndex);
		
		if(dotIndex == -1) {
			return false;
		}
		
		if(dotIndex == 0 || atIndex == email.length() - 1) {
			return false;
		}
		
		if(domainIndex > dotIndex) {
			return false;
		}
		
		
		return true;
	}
	
	public void keyEventHandler(LoginView view) {
		view.getEmailField().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					view.getPasswdField().requestFocus();
				}
			}
		});
		
		view.getPasswdField().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					validateLogin(view);
				}
			}
		});
	}

}
