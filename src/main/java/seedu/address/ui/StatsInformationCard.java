package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.StatsInformation;

/**
 * An UI component that displays information of a {@code StatsInformation}.
 */
public class StatsInformationCard extends UiPart<Region> {

    private static final String FXML = "StatsInformationCard.fxml";

    /**
     * NoteList: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final StatsInformation statsInformation;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label stats;

    /**
     * Creates a {@code StatsInformationCard} with the given {@code StatsInformation} to display.
     */
    public StatsInformationCard(StatsInformation statsInformation) {
        super(FXML);
        this.statsInformation = statsInformation;
        description.setText(statsInformation.getDescription());
        Number s = statsInformation.getStatsInformation();
        stats.setText(s.toString());
    }

    public void updateStatsInformation() {
        statsInformation.updateStatsInformation();
        Number updatedStatsInformation = statsInformation.getStatsInformation();
        stats.setText(updatedStatsInformation.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatsInformationCard)) {
            return false;
        }

        // state check
        StatsInformationCard card = (StatsInformationCard) other;
        return this.statsInformation.equals(card.statsInformation);
    }
}

