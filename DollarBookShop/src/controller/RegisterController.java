package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import model.User;
import util.Connect;
import util.Data;
import util.func;
import view.LoginView;
import view.RegisterView;

public class RegisterController extends Controller{
	
	private RegisterView view;
	private Connect connect = Connect.getInstance();

	public RegisterController(RegisterView view) {
		this.view = view;
		onActionHandler();
	}

	
	public void onActionHandler() {
		view.getSignUpBtn().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				validateRegister();
			}
		});
	}
	
//	public String newID() {
//		String query = "SELECT UserID FROM users "
//				+ "ORDER BY UserID "
//				+ "DESC LIMIT 1";
//		
//		connect.rs = connect.execQuery(query);
//		String ID = null;
//		
//		try {
//			if(!(connect.rs.next())) {
//				return "US001";
//			}
//			ID = connect.rs.getString("ID");
//			String IDnum = ID.substring(2);
//			Integer num = Integer.parseInt(IDnum) + 1;
//			ID = String.format("US%03d", num);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return ID;
//	}

	
	public void validateRegister() {
		ArrayList<User> users = getData().getUserListData();
		String email = view.getEmailField().getText();
		String username = view.getUsernameField().getText();
		String password = view.getPasswdField().getText();
		String confPasswd = view.getConfPasswdField().getText();
		LocalDate localDate = view.getDOBPicker().getValue();
		
		if(!func.validateEmail(email)) {
			func.showAlert(AlertType.WARNING, "Warning", "Email Invalid");
			return;
		}
		
		if(username.length() < 5 || !(IsUsernameUnique(users, username))) {
			func.showAlert(AlertType.WARNING, "Warning", "Username Invalid");
			return;
		}
		
		if(password.length() < 8 || func.isNotAlphanumeric(password)) {
			func.showAlert(AlertType.WARNING, "Warning", "Password Invalid");
			return;
		}
		
		if(!confPasswd.equals(password)) {
			func.showAlert(AlertType.WARNING, "Warning", "Password Doesnt Match");
			return;
		}
		
		try {
			if(validateAge(18, localDate)) {
				func.showAlert(AlertType.WARNING, "Warning", "User is under 18");
				return;
			}
		} catch (Exception e) {
			func.showAlert(AlertType.ERROR, "Error", "Date is Null");
			return;
		}
	
		Date DOB = Date.valueOf(localDate);
		
		getData().insertUser(new User(getData().setNewUserID(), email, username, password, DOB, "user"));
		new LoginView(view.getStage());
	}
	
	public boolean IsUsernameUnique(ArrayList<User> users, String username) {
		for (User user : users) {
			if(user.getUsername().equals(username)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isEmailUnique(ArrayList<User> users, String email) {
		for (User user : users) {
			if(user.getEmail().equals(email)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean validateAge(Integer minAge, LocalDate DOB) {
		
		LocalDate currDate =LocalDate.now();
		
		Integer birthYear = DOB.getYear();
		Integer currYear = currDate.getYear();
		
		Integer age = currYear - birthYear;
		
		if(age > minAge) {
			return false;
		}
		
		if(age == minAge) {
            if (currDate.getMonthValue() > DOB.getMonthValue() ||
                    (currDate.getMonthValue() == DOB.getMonthValue() && currDate.getDayOfMonth() >= DOB.getDayOfMonth())) {
                    return false;
                }
		}
		
		return true;
	}
}
