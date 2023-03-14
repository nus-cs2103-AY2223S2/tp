package seedu.address.ui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

/**
 * The UI component that is responsible for confirming user requests.
 */
public class ConfirmationDialog {

    private static final ImageView warningImgPath = new ImageView("/images/icon-warning.png");
    private static final ButtonType confirmButton = new ButtonType("Confirm");
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
     * Returns the confirmation done by user.
     */
    public boolean getConfirmationStatus() {
        return confirmationStatus;
    }

    /**
     * Shows a confirmation dialog on current scene.
     * This method only returns after the user has closed the confirmation dialog.
     */
    private static void showConfirmationDialog() {
        final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        setDialogStyle(confirmation);

        setDialogButton(confirmation);

        showAndGetResult(confirmation);
    }

    private static void setDialogStyle(Alert confirmation) {
        confirmation.getDialogPane().getStylesheets().add("view/ConfirmationDialog.css");
        confirmation.initStyle(StageStyle.UTILITY);

        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText(null);
        confirmation.setGraphic(warningImgPath);
        confirmation.setContentText(confirmationMessage);
    }

    private static void setDialogButton(Alert confirmation) {
        confirmation.getButtonTypes().setAll(ButtonType.CANCEL, confirmButton);
        ButtonBar buttonBar = (ButtonBar) confirmation.getDialogPane().lookup(".button-bar");
        buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);
        confirmation.getDialogPane().lookupButton(confirmButton).setId("confirmButton");
    }

    private static void showAndGetResult(Alert confirmation) {
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            confirmationStatus = true;
        }
    }
}
