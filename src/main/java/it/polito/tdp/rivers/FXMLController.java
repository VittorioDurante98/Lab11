package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doFiume(ActionEvent event) {
    	River fiume = model.trovaFiume(boxRiver.getValue());
    	River r= model.getRiver(fiume);
    	txtStartDate.setText(r.getFlows().get(0).getDay().toString());
    	txtEndDate.setText(r.getFlows().get(r.getFlows().size()-1).getDay().toString());
    	txtFMed.setText(r.getFlowAvg()+"");
    	txtNumMeasurements.setText(r.getFlows().size()+"");
    	
    }
    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	
    	River fiume = model.trovaFiume(boxRiver.getValue());
    	River r= model.getRiver(fiume);
    	model.getSim().init(r, Double.parseDouble(txtK.getText()));
    	txtResult.appendText(model.getSim().run());
    	
    }

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
		this.model= model;
		txtEndDate.setEditable(false);
		txtFMed.setEditable(false);
		txtResult.setEditable(false);
		for (River r: model.getFiumi()) {
			boxRiver.getItems().add(r.getName());
		}
	}
}
