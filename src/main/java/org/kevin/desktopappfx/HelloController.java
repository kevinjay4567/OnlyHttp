package org.kevin.desktopappfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> methodComboBox;

    @FXML
    private TextField urlInput;

    @FXML
    private TextArea responseText;

    @FXML
    private Label statusText;

    @FXML
    private TextArea requestInput;

    @FXML
    private Label descriptionText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        methodComboBox.getItems().addAll("GET", "POST", "PUT", "DELETE");
        methodComboBox.setValue("GET");

        statusText.setText("Status: ");
    }

    @FXML
    protected void onAddButtonClick() {
        statusText.setText("Status: ");
        if (!urlInput.getText().isEmpty()) {

            switch (methodComboBox.getValue()) {
                case "GET":
                    responseText.setText(getRequest().toPrettyString());
                    break;
                case "POST":
                    responseText.setText(postRequest());
            }

            statusText.setText(statusText.getText() + "200 OK");
        }
    }

    private JsonNode getRequest() {
        return Unirest.get(urlInput.getText()).asJson().getBody();
    }

    private String postRequest() {
        if (!requestInput.getText().isEmpty()) {
            return Unirest.post(urlInput.getText()).header("Content-Type", "application/json")
                    .body(requestInput.getText()).asJson().getBody().toPrettyString();
        }

        return "Se requiere body...";
    }
}