package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.stats.PersonStats;

/**
 * An UI component that displays information of a {@code PersonStats}.
 */
public class PersonStatsCard extends UiPart<Region> {

    private static final String FXML = "PersonStatsListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final PersonStats personStats;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label tasksAssigned;
    @FXML
    private Label tasksCompleted;
    @FXML
    private Label averageScore;

    /**
     * Creates a {@code PersonStatsCard} with the given {@code PersonStats} and index to display.
     */
    public PersonStatsCard(PersonStats personStats, int displayedIndex) {
        super(FXML);
        this.personStats = personStats;
        id.setText(displayedIndex + ". ");
        name.setText(personStats.getPerson().getName().fullName);
        tasksAssigned.setText("No. of tasks assigned: " + Integer.toString(personStats.getTasksAssigned()));
        tasksCompleted.setText("No. of tasks completed: " + Integer.toString(personStats.getTasksCompleted()));
        averageScore.setText("Average score: "
                + (Double.isNaN(personStats.getAverageScore()) ? "-" : personStats.getAverageScore()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonStatsCard)) {
            return false;
        }

        // state check
        PersonStatsCard card = (PersonStatsCard) other;
        return id.getText().equals(card.id.getText())
                && personStats.equals(card.personStats);
    }
}
