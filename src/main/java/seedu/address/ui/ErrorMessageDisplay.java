package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Component that displays error messages for pop-up windows.
 */
public class ErrorMessageDisplay {
    @FXML
    private final HBox errorMessagePlaceholder;
    @FXML
    private final Label errorMessageLabel;

    /**
     * Constructor for ErrorMessageDisplay.
     *
     * @param errorMessagePlaceholder Placeholder for error message display.
     */
    public ErrorMessageDisplay(HBox errorMessagePlaceholder) {
        this.errorMessagePlaceholder = errorMessagePlaceholder;
        errorMessageLabel = new Label();
        errorMessageLabel.setWrapText(true);
        errorMessageLabel.getStyleClass().add("label-bright");
    }

    /**
     * Clear the current error messages.
     */
    public void clearError() {
        errorMessageLabel.setText("");
        errorMessagePlaceholder.getChildren().clear();
    }

    /**
     * Set the error message in the display pane.
     *
     * @param errorMessage The error message to be put into the error message pane.
     */
    public void setError(String errorMessage) {
        clearError();
        errorMessageLabel.setText(errorMessage);
        errorMessagePlaceholder.getChildren().add(errorMessageLabel);
    }
}

