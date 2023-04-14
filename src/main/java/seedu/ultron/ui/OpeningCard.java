package seedu.ultron.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.ultron.model.opening.Opening;

/**
 * An UI component that displays information of a {@code Opening}.
 */
public class OpeningCard extends UiPart<Region> {

    private static final String FXML = "OpeningListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Opening opening;

    @FXML
    private HBox cardPane;
    @FXML
    private Label company;
    @FXML
    private Label id;
    @FXML
    private Label position;
    @FXML
    private Label status;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private FlowPane keydates;

    /**
     * Creates a {@code OpeningCode} with the given {@code Opening} and index to display.
     */
    public OpeningCard(Opening opening, int displayedIndex) {
        super(FXML);
        this.opening = opening;
        id.setText(displayedIndex + ". ");
        company.setText(opening.getCompany().fullCompany);
        position.setText(opening.getPosition().fullPosition);
        status.setText(opening.getStatus().fullStatus);
        opening.getKeydates().stream()
                .forEach(keydate -> keydates.getChildren().add(new KeydateCard(keydate)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OpeningCard)) {
            return false;
        }

        // state check
        OpeningCard card = (OpeningCard) other;
        return id.getText().equals(card.id.getText())
                && opening.equals(card.opening);
    }
}
