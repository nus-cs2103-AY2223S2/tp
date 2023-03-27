package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.crew.Crew;

/**
 * A generic view for crew.
 */
public class CrewCard extends UiPart<VBox> {
    private static final String FXML = "CrewCard.fxml";
    private final Crew crew;

    @FXML
    private VBox cardPane;

    /**
     * Creates a view for the given crew. The crew is an identifiable object
     * that can be displayed in a list.
     *
     * @param crew The crew to be displayed.
     */
    public CrewCard(Crew crew) {
        super(FXML);
        this.crew = crew;
        for (String line : crew.getDisplayList()) {
            Label label = new Label(line);
            cardPane.getChildren().add(label);
        }
    }

    /**
     * Returns the crew that is being displayed.
     *
     * @return The crew that is being displayed.
     */
    public Crew getCrew() {
        return crew;
    }
}
