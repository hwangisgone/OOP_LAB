package hust.soict.globalict.aims.screen.add;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Track;
import hust.soict.globalict.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddDigitalVideoDiscToStoreScreenController {
	private Store store;
	
    public void setStore(Store store) {
		this.store = store;
	}

	@FXML
    private TextField fieldCategory;

    @FXML
    private TextField fieldDirector;

    @FXML
    private TextField fieldTitle;

    @FXML
    private Label labelLength;

    @FXML
    private Slider sliderLength;
    
    @FXML
    private Spinner<Double> spinnerCost;

    @FXML
    void initialize() {
    	sliderLength.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelLength.setText((int) newValue.doubleValue() + " mins");
        });
    	
    	RoundedSpinnerMaker.makeRoundedSpinner(spinnerCost, 0, 100, 0, 0.05);
    }

    @FXML
    void addDVD(ActionEvent event) {
    	store.addMedia(new DigitalVideoDisc(
    		fieldTitle.getText(),	
    		fieldCategory.getText(),		
    		fieldDirector.getText(), 
    		(int) sliderLength.getValue(), 
    		spinnerCost.getValue().floatValue()
    	));
    	
    	
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Added DVD");
		alert.setHeaderText(null);
		alert.setContentText("The DVD " + fieldTitle.getText() + " was added!");
		alert.showAndWait();
    	

		fieldTitle.clear();	
		fieldCategory.clear();		
		fieldDirector.clear(); 
		sliderLength.setValue(60); 
		spinnerCost.getValueFactory().setValue(0.0);
    }

}
