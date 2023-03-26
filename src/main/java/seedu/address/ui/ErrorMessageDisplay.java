package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Component that displays error messages for command windows.
 */
public class ErrorMessageDisplay {
    @FXML
    private final HBox errorMessagePlaceholder;
    @FXML
    private final Label errorMessageLabel;

    public ErrorMessageDisplay(HBox errorMessagePlaceholder) {
        this.errorMessagePlaceholder = errorMessagePlaceholder;
        errorMessageLabel = new Label();
        errorMessageLabel.setWrapText(true);
        errorMessageLabel.getStyleClass().add("label-bright");
    }

    public void clearError() {
        errorMessageLabel.setText("");
        errorMessagePlaceholder.getChildren().clear();
    }

    public void setError(String errorMessage) {
        clearError();
        errorMessageLabel.setText(errorMessage);
        errorMessagePlaceholder.getChildren().add(errorMessageLabel);
    }
}

