package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * Informs the user on the number of undone tasks
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    //private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    public EventListPanel() {
        super(FXML);
    }
}
