package controller;

import java.util.Optional;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableSelectionModel;
import main.Main;
import model.Product;
import model.User;
import util.func;
import view.HomeViewAdmin;
import view.LoginView;

public class HomeAdminController extends Controller {
    private HomeViewAdmin view;
    private Product product;
    private Preferences pref = Preferences.userNodeForPackage(Main.class);
    private User user;

    public HomeAdminController(HomeViewAdmin view, User user) {
        this.view = view;
        this.user = user;
        setOnMouseClicked();
        setOnAction();
    }

    public void setOnAction() {
        // Logout button action
        view.getLogout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Log Out");
                alert.setContentText("Do you want to log out?");

                Optional<ButtonType> op = alert.showAndWait();
                if (op.isPresent() && op.get().equals(ButtonType.OK)) {
                    pref.remove("lastEmail");
                    new LoginView(view.getStage());
                }
            }
        });

        // Delete button action
        view.getDeleteBtn().setOnAction(event -> {
            if (product != null) {
                Product selectedProduct = view.getProduct_table().getSelectionModel().getSelectedItem();
                view.getProduct_table().getItems().remove(selectedProduct);
                getData().removeProductFromSomeoneCart(product);
                getData().removeProduct(product);
                func.showAlert(AlertType.INFORMATION, "Success", "Product removed successfully");
                product = null;
            } else {
                func.showAlert(AlertType.WARNING, "Warning", "No product selected to delete.");
            }
        });

     // Update button action
        view.getUpdateBtn().setOnAction(event -> {
            if (product != null) {
                String priceText = view.getPriceTF().getText();
                int stock = view.getStockSpinner().getValue();
                String name = view.getNameTF().getText().trim();
                String genre = view.getGenreTF().getText().trim();
            
                if (stock <= 0) {
                    func.showAlert(AlertType.WARNING, "Invalid Input", "Stock must be more than 0.");
                    return;
                }

                if (!isNumeric(priceText)) {
                    func.showAlert(AlertType.WARNING, "Invalid Input", "Price must be numeric.");
                    return;
                }

                int price = Integer.parseInt(priceText);
                if (price < 1000) {
                    func.showAlert(AlertType.WARNING, "Invalid Input", "Price must be at least 1000.");
                    return;
                }
          
                if (name.isEmpty()) {
                    func.showAlert(AlertType.WARNING, "Invalid Input", "Name cannot be empty.");
                    return;
                }
                
                if (genre.isEmpty()) {
                    func.showAlert(AlertType.WARNING, "Invalid Input", "Genre cannot be empty.");
                    return;
                }
                
                int price1 = Integer.parseInt(priceText);
                if (price1 < 1000) {
                    func.showAlert(AlertType.WARNING, "Invalid Input", "Price must be at least 1000.");
                    return;
                }

                product.setName(name);
                product.setGenre(genre);
                product.setStock(stock);
                product.setPrice(price1);

                getData().updateProduct(product);
                view.refreshTable(view.getProduct_table(), getData().getProductListData());
                func.showAlert(AlertType.INFORMATION, "Success", "Product updated successfully.");
            } else {
                func.showAlert(AlertType.WARNING, "Warning", "No product selected to update.");
            }
        });



        // Add button action
        view.getAddBtn().setOnAction(event -> {
            String name = view.getNameTF().getText();
            String genre = view.getGenreTF().getText();
            String priceText = view.getPriceTF().getText();
            int stock = view.getStockSpinner().getValue();

            if (name.isEmpty() || genre.isEmpty() || priceText.isEmpty()) {
                func.showAlert(AlertType.WARNING, "Invalid Input", "All fields must be filled.");
                return;
            }

            if (stock <= 0) {
                func.showAlert(AlertType.WARNING, "Invalid Input", "Stock must be more than 0.");
                return;
            }

            if (!isNumeric(priceText)) {
                func.showAlert(AlertType.WARNING, "Invalid Input", "Price must be numeric.");
                return;
            }
            
            int price = Integer.parseInt(priceText);
            if (price < 1000) {
                func.showAlert(AlertType.WARNING, "Invalid Input", "Price must be at least 1000.");
                return;
            }

            Product newProduct = new Product(
                getData().setNewProductID(),
                name,
                genre,
                stock,
                Integer.parseInt(priceText)
            );

            getData().addNewProduct(newProduct);
            view.refreshTable(view.getProduct_table(), getData().getProductListData());
            func.showAlert(AlertType.INFORMATION, "Success", "Product added successfully.");
        });
    }

    public void setOnMouseClicked() {
        view.getProduct_table().setOnMouseClicked(e -> {
            TableSelectionModel<Product> model = view.getProduct_table().getSelectionModel();
            model.setSelectionMode(SelectionMode.SINGLE);
            product = model.getSelectedItem();
            if (product != null) {
                view.getNameTF().setText(product.getName());
                view.getGenreTF().setText(product.getGenre());
                view.getStockSpinner().getValueFactory().setValue(product.getStock());
                view.getPriceTF().setText(String.valueOf(product.getPrice()));
            }
        });
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
