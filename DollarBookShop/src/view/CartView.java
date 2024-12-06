package view;

import controller.HomeController;
import controller.ViewController;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CartItem;
import model.Product;
import model.User;

public class CartView extends HomeViewTemplate{

	private Scene scene;
	private BorderPane mainLayout, cartLayout;
	
	private TableView<CartItem> cartTable;
	private TableColumn<CartItem, String> productIdColumn, productNameColumn, genreColumn;
	private TableColumn<CartItem, Integer> productPriceColumn, productQuantityColumn, totalPriceColumn;
	
	private Button removeBtn,checkoutBtn,updateBtn;
	private Spinner<Integer> quantitySpinner;
	
	private ObservableList<CartItem> cartItem;
	
    private Label cartLbl;

	public CartView(Stage stage, User user) {
		super(stage, user);
		this.user = user;
		
		start();
		setTable();
		arrangeComponent();
		startTypingAnimation();
		new ViewController(this, user);
		
	}
	
	@Override
	public void start() {
		
        mainLayout = new BorderPane();
        cartLayout = new BorderPane();
        scene = new Scene(mainLayout);
        
        cartLbl = new Label();
        cartLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        
        removeBtn = new Button("Remove");
        checkoutBtn = new Button("Checkout");
        updateBtn = new Button("Update");
        
//        removeBtn.setBackground(new Background(
//        		new BackgroundFill(Color.LIGHTGRAY, null, null)));
//        checkoutBtn.setBackground(new Background(
//        		new BackgroundFill(Color.LIGHTGRAY, null, null)));
//        updateBtn.setBackground(new Background(
//        		new BackgroundFill(Color.LIGHTGRAY, null, null)));
        
        //Set so that the Menu Item for home cannot be click again when in home 
        //view
        cart.setDisable(true);
		
	}
	
	
	public void setTable() {
		cartTable = new TableView<>();
    	
		productIdColumn = new TableColumn<>("ID");
		productIdColumn.setCellValueFactory(new PropertyValueFactory<CartItem, String>("ProductId"));
    	
		productNameColumn = new TableColumn<>("Name");
		productNameColumn.setCellValueFactory(new PropertyValueFactory<CartItem,String>("Name"));
    
		genreColumn = new TableColumn<>("Genre");
		genreColumn.setCellValueFactory(new PropertyValueFactory<CartItem, String>("genre"));
    	
		productPriceColumn = new TableColumn<>("Price");
		productPriceColumn.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("Price"));
    
		productQuantityColumn = new TableColumn<>("Quantity");
		productQuantityColumn.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("qty"));
    	
		totalPriceColumn = new TableColumn<>("Total");
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<CartItem, Integer>("total"));
		
		
		cartTable.getColumns().addAll(productIdColumn, productNameColumn, genreColumn, productPriceColumn, productQuantityColumn,totalPriceColumn );
    	
		
		cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		cartTable.setItems(getData().getCartListData(user));

		//cartLayout.setCenter(cartTable);
		

		
	}
	

	@Override
	public void arrangeComponent() {
		  	mb.getMenus().add(actionMenu);
	        actionMenu.getItems().addAll(home, cart, logout);

	        mainLayout.setTop(mb);
	        mainLayout.setCenter(cartLayout);
	        
	        cartLayout.setTop(cartLbl);
	        

	        cartLayout.setCenter(cartTable);
	        
	        quantitySpinner = new Spinner<Integer>(1,100,1);
	        quantitySpinner.setPrefWidth(100);
	 
	        
	        HBox buttonBox = new HBox(20, removeBtn, checkoutBtn, updateBtn);
	        
	        VBox vBox = new VBox(quantitySpinner,buttonBox);
	        vBox.setAlignment(Pos.BOTTOM_LEFT);
	        vBox.setPadding(new Insets(5));
	        
	        cartLayout.setBottom(vBox);
	      
	        cartLbl.setPadding(new Insets(10, 0, 10, 0));
	        
	        getStage().setScene(scene);
	        
	     
	        
		
	}
	
	 //This is a typing animation when the User is in the home view
    //This will type Welcome, [Name]
    //Not Required
    private void startTypingAnimation() {
    	//Start of with the base Text
    	String username = user.getUsername();
        String baseText = "'s Cart";
       
        //Timeline Object
        Timeline timeline = new Timeline();
        
        //Will loop through the alphabet one by one till it reach the end
        //each 200ms
        for (int i = 0; i <= username.length(); i++) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200 * i),
                    e -> cartLbl.setText(username.substring(0,index)+baseText));
            //Add the keyframe to the timeline object
            timeline.getKeyFrames().add(keyFrame);
        }
        
        //After 2 seconds
        //It will then start deleting the alphabet on the name one by one 
        //every 200ms like before
        for (int i = username.length(); i >= 0; i--) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(5000 + 200 * (username.length() - i)), 
                    e -> cartLbl.setText(username.substring(0,index)+baseText));
            timeline.getKeyFrames().add(keyFrame);
        }
       
        //To set so that it never stops
        timeline.setCycleCount(Timeline.INDEFINITE);
        //Start the animation
        timeline.play();
    }

    
    public void refreshTable(TableView<CartItem> cartTable, ObservableList<CartItem> list) {
    	list.clear();
    	list.addAll(getData().getCartListData(user));
    	cartTable.setItems(list);
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

	public BorderPane getCartLayout() {
		return cartLayout;
	}

	public void setCartLayout(BorderPane cartLayout) {
		this.cartLayout = cartLayout;
	}

	public TableView<CartItem> getCartTable() {
		return cartTable;
	}

	public void setCartTable(TableView<CartItem> cartTable) {
		this.cartTable = cartTable;
	}

	public TableColumn<CartItem, String> getProductIdColumn() {
		return productIdColumn;
	}

	public void setProductIdColumn(TableColumn<CartItem, String> productIdColumn) {
		this.productIdColumn = productIdColumn;
	}

	public TableColumn<CartItem, String> getProductNameColumn() {
		return productNameColumn;
	}

	public void setProductNameColumn(TableColumn<CartItem, String> productNameColumn) {
		this.productNameColumn = productNameColumn;
	}

	public TableColumn<CartItem, String> getGenreColumn() {
		return genreColumn;
	}

	public void setGenreColumn(TableColumn<CartItem, String> genreColumn) {
		this.genreColumn = genreColumn;
	}

	public TableColumn<CartItem, Integer> getProductPriceColumn() {
		return productPriceColumn;
	}

	public void setProductPriceColumn(TableColumn<CartItem, Integer> productPriceColumn) {
		this.productPriceColumn = productPriceColumn;
	}

	public TableColumn<CartItem, Integer> getProductQuantityColumn() {
		return productQuantityColumn;
	}

	public void setProductQuantityColumn(TableColumn<CartItem, Integer> productQuantityColumn) {
		this.productQuantityColumn = productQuantityColumn;
	}

	public TableColumn<CartItem, Integer> getTotalPriceColumn() {
		return totalPriceColumn;
	}

	public void setTotalPriceColumn(TableColumn<CartItem, Integer> totalPriceColumn) {
		this.totalPriceColumn = totalPriceColumn;
	}

	public Button getRemoveBtn() {
		return removeBtn;
	}

	public void setRemoveBtn(Button removeBtn) {
		this.removeBtn = removeBtn;
	}

	public Button getCheckoutBtn() {
		return checkoutBtn;
	}

	public void setCheckoutBtn(Button checkoutBtn) {
		this.checkoutBtn = checkoutBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(Button updateBtn) {
		this.updateBtn = updateBtn;
	}

	public Spinner<Integer> getQuantitySpinner() {
		return quantitySpinner;
	}

	public void setQuantitySpinner(Spinner<Integer> quantitySpinner) {
		this.quantitySpinner = quantitySpinner;
	}

	public ObservableList<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(ObservableList<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	public Label getCartLbl() {
		return cartLbl;
	}

	public void setCartLbl(Label cartLbl) {
		this.cartLbl = cartLbl;
	}
    
    
    
    
    
    
    
    
	

}
