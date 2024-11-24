package view;

import controller.HomeAdminController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Product;
import model.User;

public class HomeViewAdmin extends HomeViewAdminTemplate{

	private Scene scene;
	private BorderPane mainLayout, homeAdminLayout;
	private VBox fieldsBox;
	private HBox buttonBox;
	
	private TableView<Product> product_table;
	private TableColumn<Product, String> ProductID, ProductName, ProductGenre;
	private TableColumn<Product, Integer> ProductStock, ProductPrice;
	
	private Button deleteBtn, addBtn, updateBtn;
	private Label welcomeLbl, nameLbl, genreLbl, stockLbl, priceLbl;
	private TextField nameTF, genreTF, priceTF;
	private Spinner<Integer> stockSpinner;
	
	public HomeViewAdmin(Stage stage, User user) {
		super(stage, user);
		this.user = user;
		
		start();
		setTable();
		arrangeComponent();
		startTypingAnimation();
		new HomeAdminController(this, user);
		
	}

	@Override
	public void start() {
		mainLayout = new BorderPane();
		homeAdminLayout = new BorderPane();
		fieldsBox = new VBox();
		buttonBox = new HBox();
		scene = new Scene(mainLayout);
		
		welcomeLbl = new Label();
		welcomeLbl.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		
		addBtn = new Button("Add");
		deleteBtn = new Button("Delete");
		updateBtn = new Button("Update");
		
		nameLbl = new Label("Name");
		genreLbl = new Label("Genre");
		stockLbl = new Label("Stock");
		priceLbl = new Label("Price");
		
		nameTF = new TextField();
		nameTF.setPromptText("Name");
		
		genreTF = new TextField();
		genreTF.setPromptText("Genre");
		
		stockSpinner = new Spinner<>(0, 1000, 0, 1); //Untuk sementara
		
		priceTF = new TextField();
		priceTF.setPromptText("Price");
		
		
	}
	
	public void setTable() {
		product_table = new TableView<>();
		ProductID = new TableColumn<Product, String>("ID");
		ProductID.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
		
		ProductName = new TableColumn<Product, String>("Name");
		ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		
		ProductGenre = new TableColumn<Product, String>("Genre");
		ProductGenre.setCellValueFactory(new PropertyValueFactory<Product, String>("genre"));
		
		ProductStock = new TableColumn<Product, Integer>("Stock");
		ProductStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
		
		ProductPrice = new TableColumn<Product, Integer>("Price");
		ProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		
		product_table.getColumns().addAll(ProductID, ProductName, ProductGenre, ProductStock, ProductPrice);
		
		product_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		product_table.setItems(getData().getProductListData());
	}

	@Override
	public void arrangeComponent() {
		mb.getMenus().add(actionMenu);
		actionMenu.getItems().add(logout);
		
		mainLayout.setTop(mb);
		mainLayout.setCenter(homeAdminLayout);
		
		homeAdminLayout.setTop(welcomeLbl);
		homeAdminLayout.setCenter(product_table);
		
		nameLbl.setFont(Font.font(14));
		genreLbl.setFont(Font.font(14));
		stockLbl.setFont(Font.font(14));
		priceLbl.setFont(Font.font(14));
		
		
		buttonBox.getChildren().addAll(deleteBtn, addBtn, updateBtn);
		buttonBox.setMaxWidth(400);
		buttonBox.setSpacing(25);
		buttonBox.setAlignment(Pos.CENTER);
		
		deleteBtn.setPrefWidth((buttonBox.getMaxWidth() - (buttonBox.getSpacing()*2))/3);
		addBtn.setPrefWidth((buttonBox.getMaxWidth() - (buttonBox.getSpacing()*2))/3);
		updateBtn.setPrefWidth((buttonBox.getMaxWidth() - (buttonBox.getSpacing()*2))/3);

		
		fieldsBox.getChildren().addAll(nameLbl, nameTF, genreLbl, genreTF, stockLbl, stockSpinner, priceLbl, priceTF, buttonBox);
		fieldsBox.setMaxWidth(400);
		stockSpinner.setMaxWidth(200);
		
		BorderPane.setAlignment(fieldsBox, Pos.CENTER);
		
		homeAdminLayout.setBottom(fieldsBox);
		
		getStage().setScene(scene);
		
	}

	//This is a typing animation when the User is in the home view Admin
    //This will type Welcome, [Name]
    //Not Required
    private void startTypingAnimation() {
    	//Start of with the base Text
        String baseText = "Welcome, ";
        String username = user.getUsername();
        //Timeline Object
        Timeline timeline = new Timeline();
        
        //Will loop through the alphabet one by one till it reach the end
        //each 200ms
        for (int i = 0; i <= username.length(); i++) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200 * i),
                    e -> welcomeLbl.setText(baseText + username.substring(0, index)));
            //Add the keyframe to the timeline object
            timeline.getKeyFrames().add(keyFrame);
        }
        
        //After 2 seconds
        //It will then start deleting the alphabet on the name one by one 
        //every 200ms like before
        for (int i = username.length(); i >= 0; i--) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(5000 + 200 * (username.length() - i)), 
                    e -> welcomeLbl.setText(baseText + username.substring(0, index)));
            timeline.getKeyFrames().add(keyFrame);
        }
       
        //To set so that it never stops
        timeline.setCycleCount(Timeline.INDEFINITE);
        //Start the animation
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

	public BorderPane getHomeAdminLayout() {
		return homeAdminLayout;
	}

	public void setHomeAdminLayout(BorderPane homeAdminLayout) {
		this.homeAdminLayout = homeAdminLayout;
	}

	public VBox getFieldsBox() {
		return fieldsBox;
	}

	public void setFieldsBox(VBox fieldsBox) {
		this.fieldsBox = fieldsBox;
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

	public Button getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(Button deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public Button getAddBtn() {
		return addBtn;
	}

	public void setAddBtn(Button addBtn) {
		this.addBtn = addBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(Button updateBtn) {
		this.updateBtn = updateBtn;
	}

	public Label getWelcomeLbl() {
		return welcomeLbl;
	}

	public void setWelcomeLbl(Label welcomeLbl) {
		this.welcomeLbl = welcomeLbl;
	}

	public Label getNameLbl() {
		return nameLbl;
	}

	public void setNameLbl(Label nameLbl) {
		this.nameLbl = nameLbl;
	}

	public Label getGenreLbl() {
		return genreLbl;
	}

	public void setGenreLbl(Label genreLbl) {
		this.genreLbl = genreLbl;
	}

	public Label getStockLbl() {
		return stockLbl;
	}

	public void setStockLbl(Label stockLbl) {
		this.stockLbl = stockLbl;
	}

	public Label getPriceLbl() {
		return priceLbl;
	}

	public void setPriceLbl(Label priceLbl) {
		this.priceLbl = priceLbl;
	}

	public TextField getNameTF() {
		return nameTF;
	}

	public void setNameTF(TextField nameTF) {
		this.nameTF = nameTF;
	}

	public TextField getGenreTF() {
		return genreTF;
	}

	public void setGenreTF(TextField genreTF) {
		this.genreTF = genreTF;
	}

	public TextField getPriceTF() {
		return priceTF;
	}

	public void setPriceTF(TextField priceTF) {
		this.priceTF = priceTF;
	}

	public Spinner<Integer> getStockSpinner() {
		return stockSpinner;
	}

	public void setStockSpinner(Spinner<Integer> stockSpinner) {
		this.stockSpinner = stockSpinner;
	}

	public HBox getButtonBox() {
		return buttonBox;
	}

	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}
	
	
    
    
    
}
