package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * Informs the user on the number of undone tasks
 */
public class EventCard extends UiPart<Region> {
    private static final String FXML = "EventCard.fxml";
    //private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    public EventCard() {
        super(FXML);
    }
}
