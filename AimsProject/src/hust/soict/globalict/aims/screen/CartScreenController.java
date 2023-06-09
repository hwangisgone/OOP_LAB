package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.screen.add.AddBookToStoreScreenController;
import hust.soict.globalict.aims.screen.add.AddCompactDiscToStoreScreenController;
import hust.soict.globalict.aims.screen.add.AddDigitalVideoDiscToStoreScreenController;
import hust.soict.globalict.aims.store.Store;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.binding.FloatBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CartScreenController {
	private Cart cart;

	public void setStore(Store store) {
		fxmlAddBookController.setStore(store);
		fxmlAddDVDController.setStore(store);
		fxmlAddCDController.setStore(store);
	}

	@FXML
	private AddBookToStoreScreenController fxmlAddBookController;
	
	@FXML
	private AddDigitalVideoDiscToStoreScreenController fxmlAddDVDController;
	
	@FXML
	private AddCompactDiscToStoreScreenController fxmlAddCDController;
	
	@FXML
    private MenuItem viewStoreMenuItem;
	
    public MenuItem getViewStoreMenuItem() {
		return viewStoreMenuItem;
	}

	@FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;
    
    @FXML
    private Label totalCost;
    
    @FXML
    void btnActionPlaceOrder(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Placing order");
		alert.setHeaderText(cart.totalCost() + " $");
		alert.setContentText("Your total is " + cart.totalCost() + " $. Please pay in cash.");

		alert.showAndWait();
		
    	cart.clear();
    	changeTotalCost();
    }
    
    public CartScreenController(Cart cart) {
    	super();
    	this.cart = cart;
    }
    
    @FXML
    private void initialize() {  	
    	colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
    	colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
    	colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
    	tblMedia.setItems(this.cart.getItemsOrdered());
    
    	btnPlay.setVisible(false);
    	btnRemove.setVisible(false);
    	
    	tblMedia.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<Media>() {
				@Override
				public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
					if (newValue != null) {
						updateButtonBar(newValue);
					}
				}

				private void updateButtonBar(Media media) {
					// TODO Auto-generated method stub
					btnRemove.setVisible(true);
					if(media instanceof Playable) {
						btnPlay.setVisible(true);
					} else {
						btnPlay.setVisible(false);
					}
				}
			}
		);
    	
    }
    
    public void changeTotalCost() {
    	totalCost.setText(this.cart.totalCost() + " $");
    }
    
    @FXML
    void btnRemovePressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	cart.removeMedia(media);
    	changeTotalCost();
    }
    
    @FXML
    void btnPlayPressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
		if(media instanceof Playable) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Playing Media");
			alert.setHeaderText(null);
			alert.setContentText("Playing " + media.getTitle());

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Ooops, there was an error!");
			alert.setContentText("Ooops, there was an error!");

			alert.showAndWait();
		}
    }
    
    @FXML
    private MenuItem addBookMenuItem;

    @FXML
    private MenuItem addCDMenuItem;

    @FXML
    private MenuItem addDVDMenuItem;
    
    @FXML
    private MenuItem viewCartMenuItem;
    
    @FXML
    private BorderPane paneViewCart;
    
    @FXML
    private AnchorPane paneAddBook;
    
    @FXML
    private AnchorPane paneAddCD;
    
    @FXML
    private AnchorPane paneAddDVD;
    
    
    public void switchAdd(boolean book, boolean cd, boolean dvd) {
    	if (book ^ cd ^ dvd) {
    		addBookMenuItem.setVisible(!book);
    		addCDMenuItem.setVisible(!cd);
    		addDVDMenuItem.setVisible(!dvd);
    		
    		paneViewCart.setVisible(false);
    		viewCartMenuItem.setVisible(true);
    		
    		paneAddBook.setVisible(book);
    		paneAddCD.setVisible(cd);
    		paneAddDVD.setVisible(dvd);
    	} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Ooops, there was an error when switching to add book/cd/dvd windows!");
			alert.setContentText("");

			alert.showAndWait();
    	}
    }
    
    public void switchCart() {
		paneViewCart.setVisible(true);
		viewCartMenuItem.setVisible(false);
		
		addBookMenuItem.setVisible(true);
		addCDMenuItem.setVisible(true);
		addDVDMenuItem.setVisible(true);
		
		paneAddBook.setVisible(false);
		paneAddCD.setVisible(false);
		paneAddDVD.setVisible(false);
    }
    
    @FXML
    void switchActionToCart(ActionEvent event) {
    	switchCart();
    }
    
    @FXML
    void switchAddBook(ActionEvent event) {
    	switchAdd(true, false, false);
    }

    @FXML
    void switchAddCD(ActionEvent event) {
    	switchAdd(false, true, false);
    }

    @FXML
    void switchAddDVD(ActionEvent event) {
    	switchAdd(false, false, true);
    }
}
