package view;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.User;

public class HomeView extends HomeViewTemplate {

    private Scene scene;
    private BorderPane mainLayout;
    private User user;
    
    private Label welcomeLbl;
    private MenuBar mb; 
    private Menu actionMenu;
    private MenuItem home, cart, logout;
    
    public HomeView(Stage stage, User user) {
        super(stage, user);
        this.user = user;
        
        start();
        arrangeComponent();
        startTypingAnimation();
    }

    @Override
    public void start() {
        mainLayout = new BorderPane();
        scene = new Scene(mainLayout, width, height);
        
        welcomeLbl = new Label();
        welcomeLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        
        mb = new MenuBar();
        actionMenu = new Menu("Action");
        home = new MenuItem("Home");
        cart = new MenuItem("Cart");
        logout = new MenuItem("Log Out");
        
        home.setDisable(true);
    }

    @Override
    public void arrangeComponent() {
        mb.getMenus().add(actionMenu);
        actionMenu.getItems().addAll(home, cart, logout);
        
        mainLayout.setTop(mb);
        mainLayout.setCenter(welcomeLbl);
        
        this.getStage().setScene(scene);
    }
    
    private void startTypingAnimation() {
        String baseText = "Welcome, ";
        String username = user.getUsername();
        Timeline timeline = new Timeline();

        for (int i = 0; i <= username.length(); i++) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200 * i),
                    e -> welcomeLbl.setText(baseText + username.substring(0, index)));
            timeline.getKeyFrames().add(keyFrame);
        }
        
        
        for (int i = username.length(); i >= 0; i--) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200 * username.length() + 2000 + 200 * (username.length() - i)), 
                    e -> welcomeLbl.setText(baseText + username.substring(0, index)));
            timeline.getKeyFrames().add(keyFrame);
        }
       

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
