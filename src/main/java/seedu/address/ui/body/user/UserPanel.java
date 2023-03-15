package seedu.address.ui.body.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays {@code User} information.
 */
public class UserPanel extends UiPart<Region> {
    private static final String FXML = "body/user/UserPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private VBox dataContainer;

    /**
     * Creates a {@code UserPanel} with the given {@code userData}.
     */
    public UserPanel(ReadOnlyUserData userData) {
        super(FXML);

        userData.getData().addListener((observable, oldValue, newValue) -> {
            name.setText(newValue.getName().toString());
        });
    }
}
