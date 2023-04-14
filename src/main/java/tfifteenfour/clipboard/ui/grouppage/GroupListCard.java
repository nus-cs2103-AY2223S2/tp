package tfifteenfour.clipboard.ui.grouppage;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.ui.UiPart;

/**
 * An UI component that displays information of a {@code Group}.
 */
public class GroupListCard extends UiPart<Region> {

    private static final String FXML = "GroupListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Group group;

    @FXML
    private Label id;
    @FXML
    private Label name;

    /**
     * Creates a {@code GroupListCard} with the given {@code Group} and index to display.
     */
    public GroupListCard(Group group, int displayedIndex) {
        super(FXML);
        this.group = group;
        id.setText(displayedIndex + ". ");
        name.setText(group.getGroupName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupListCard)) {
            return false;
        }

        // state check
        GroupListCard card = (GroupListCard) other;
        return id.getText().equals(card.id.getText())
                && group.equals(card.group);
    }
}


