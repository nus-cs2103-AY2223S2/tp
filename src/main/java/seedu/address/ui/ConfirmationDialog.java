package seedu.address.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.Optional;

/**
 * The UI component that is responsible for confirming user requests.
 */
public class ConfirmationDialog {

    private static final ImageView warningImgPath = new ImageView("/images/icon-warning.png");
    private static String confirmationMessage;
    private static boolean confirmationStatus = false;

    /**
     * Creates a {@code ConfirmationDialog} with the given {@code String}.
     */
    public ConfirmationDialog(String confirmMsg) {
        confirmationMessage = confirmMsg;
        showConfirmationDialog();
    }

    /**
     * Shows a confirmation dialog on current scene.
     * This method only returns after the user has closed the confirmation dialog.
     */
    private static void showConfirmationDialog() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.getDialogPane().getStylesheets().add("view/ConfirmationDialog.css");
        confirmation.initStyle(StageStyle.UTILITY);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText(null);
        confirmation.setGraphic(warningImgPath);
        confirmation.setContentText(confirmationMessage);

        ButtonType confirmButton = new ButtonType("Confirm");
        confirmation.getButtonTypes().setAll(ButtonType.CANCEL, confirmButton);
        ButtonBar buttonBar = (ButtonBar) confirmation.getDialogPane().lookup(".button-bar");
        buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);
        confirmation.getDialogPane().lookupButton(confirmButton).setId("confirmButton");


        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            confirmationStatus = true;
        }
    }

    /**
     * Returns the confirmation done by user.
     */
    public boolean getConfirmationStatus() {
        return confirmationStatus;
    }
}
