package view;

import controller.HomeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Product;
import model.User;

public class HomeView extends HomeViewTemplate {

    private Scene scene;
    private BorderPane mainLayout, homeLayout;
    private User user;
    
    private TableView<Product> product_table;
    private TableColumn<Product, String> ProductID, ProductName, ProductGenre;
    private TableColumn<Product, Integer> ProductStock, ProductPrice;
    
    private Button addCartBtn;
    
    private Label welcomeLbl;
    
    public HomeView(Stage stage, User user) {
        super(stage, user);
        this.user = user;
        
        
        start();
        setTable();
        arrangeComponent();
        startTypingAnimation();
        new HomeController(this);
    }
    
    public void setTable() {
    	product_table = new TableView<>();
    	
    	ProductID = new TableColumn<>("ID");
    	ProductID.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
    	
    	ProductName = new TableColumn<>("Name");
    	ProductName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
    
    	ProductGenre = new TableColumn<>("Genre");
    	ProductGenre.setCellValueFactory(new PropertyValueFactory<Product, String>("genre"));
    	
    	ProductStock = new TableColumn<>("Stock");
    	ProductStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
    
    	ProductPrice = new TableColumn<>("Price");
    	ProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
    	
    	product_table.getColumns().addAll(ProductID, ProductName, ProductGenre, ProductStock, ProductPrice);
    	
    	ProductID.prefWidthProperty().bind(product_table.widthProperty().multiply(0.20));
    	ProductName.prefWidthProperty().bind(product_table.widthProperty().multiply(0.20));
    	ProductGenre.prefWidthProperty().bind(product_table.widthProperty().multiply(0.20));
    	ProductStock.prefWidthProperty().bind(product_table.widthProperty().multiply(0.20));
    	ProductPrice.prefWidthProperty().bind(product_table.widthProperty().multiply(0.20));
    	
    	product_table.setItems(getData().getProductListData());
    }

    @Override
    public void start() {
        mainLayout = new BorderPane();
        homeLayout = new BorderPane();
        scene = new Scene(mainLayout);
        
        welcomeLbl = new Label();
        welcomeLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        
        mb = new MenuBar();
        actionMenu = new Menu("Action");
        home = new MenuItem("Home");
        cart = new MenuItem("Cart");
        logout = new MenuItem("Log Out");
        
        addCartBtn = new Button("Add to Cart");
        
        home.setDisable(true);
    }

    @Override
    public void arrangeComponent() {
        mb.getMenus().add(actionMenu);
        actionMenu.getItems().addAll(home, cart, logout);
        
        addCartBtn.setTextFill(Color.WHITE);
        addCartBtn.setBackground(new Background(
        		new BackgroundFill(Color.DARKGRAY, null, null)));
        addCartBtn.setPadding(new Insets(20));
        addCartBtn.setMinWidth(350);
        
        mainLayout.setTop(mb);
        mainLayout.setCenter(homeLayout);
        
        homeLayout.setTop(welcomeLbl);
        homeLayout.setCenter(product_table);
        HBox buttonBox = new HBox(addCartBtn);
        buttonBox.setAlignment(Pos.CENTER);
        homeLayout.setBottom(buttonBox);
      
        welcomeLbl.setPadding(new Insets(10, 0, 10, 0));
        
        getStage().setScene(scene);
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
    
    public void refreshTable(TableView<Product> table, ObservableList<Product> list) {
    	list.clear();
    	list.addAll(getData().getProductListData());
    	table.setItems(list);
    }

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public BorderPane getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(BorderPane mainLayout) {
		this.mainLayout = mainLayout;
	}

	public BorderPane getHomeLayout() {
		return homeLayout;
	}

	public void setHomeLayout(BorderPane homeLayout) {
		this.homeLayout = homeLayout;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TableView<Product> getProduct_table() {
		return product_table;
	}

	public void setProduct_table(TableView<Product> product_table) {
		this.product_table = product_table;
	}

	public TableColumn<Product, String> getProductID() {
		return ProductID;
	}

	public void setProductID(TableColumn<Product, String> productID) {
		ProductID = productID;
	}

	public TableColumn<Product, String> getProductName() {
		return ProductName;
	}

	public void setProductName(TableColumn<Product, String> productName) {
		ProductName = productName;
	}

	public TableColumn<Product, String> getProductGenre() {
		return ProductGenre;
	}

	public void setProductGenre(TableColumn<Product, String> productGenre) {
		ProductGenre = productGenre;
	}

	public TableColumn<Product, Integer> getProductStock() {
		return ProductStock;
	}

	public void setProductStock(TableColumn<Product, Integer> productStock) {
		ProductStock = productStock;
	}

	public TableColumn<Product, Integer> getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(TableColumn<Product, Integer> productPrice) {
		ProductPrice = productPrice;
	}

	public Button getAddCartBtn() {
		return addCartBtn;
	}

	public void setAddCartBtn(Button addCartBtn) {
		this.addCartBtn = addCartBtn;
	}

	public Label getWelcomeLbl() {
		return welcomeLbl;
	}

	public void setWelcomeLbl(Label welcomeLbl) {
		this.welcomeLbl = welcomeLbl;
	}
    
    
}
