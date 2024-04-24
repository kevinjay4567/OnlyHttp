package org.kevin.desktopappfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

    @FXML
    private TreeView<String> requestTreeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        methodComboBox.getItems().addAll("GET", "POST", "PUT", "DELETE");
        methodComboBox.setValue("GET");
        TreeItem<String> rootItem = new TreeItem<>("Saved Requests");
        requestTreeView.setRoot(rootItem);
    }

    @FXML
    protected void onAddButtonClick() {
        statusText.setText("Status: ");
        if (!urlInput.getText().isEmpty()) {

            Request request = new Request();

            switch (methodComboBox.getValue()) {
                case "GET":
                    responseText.setText(request.get(urlInput.getText()).get("body"));
                    statusText.setText(statusText.getText() + " " + request.get(urlInput.getText()).get("status"));
                    break;

                case "POST":
                    responseText.setText(request.post(urlInput.getText(), requestInput.getText()).get("body"));
                    statusText.setText(statusText.getText() + " " + request.post(urlInput.getText(),
                            requestInput.getText()).get("status"));
                    break;
            }
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        if (!urlInput.getText().isEmpty()) {

            requestTreeView.getRoot().getChildren()
                    .add(new TreeItem<>("[ " + methodComboBox.getValue() + " ] " + urlInput.getText()));
        }
    }
}