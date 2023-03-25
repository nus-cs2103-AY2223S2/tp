package seedu.vms.ui.vaccination;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.ui.UiPart;


/**
 * Simplified view of a vaccination.
 */
public class SimplifiedVaxTypeCard extends UiPart<HBox> {
    private static final String FXML_FILE = "SimplifiedVaxTypeCard.fxml";

    @FXML private Label indexLabel;
    @FXML private Label nameLabel;


    /**
     * Constructs a {@code SimplifiedVaxTypeCard}.
     *
     * @param index - the index of this card.
     * @param vaxType - the vaccination to display.
     */
    public SimplifiedVaxTypeCard(int index, VaxType vaxType) {
        super(FXML_FILE);
        indexLabel.setText(String.valueOf(index));
        nameLabel.setText(vaxType.getName());
    }
}
