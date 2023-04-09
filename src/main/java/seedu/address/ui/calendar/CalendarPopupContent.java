
package seedu.address.ui.calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.session.Session;
import seedu.address.ui.UiPart;

//@@author wongyewjon
/**
 * The content that is displayed within the {@code CalendarPopup}
 */

public class CalendarPopupContent extends UiPart<Region> {
    private static final String FXML = "CalendarPopupContent.fxml";
    @FXML
    private ScrollPane popupContent;
    @FXML
    private Label studentLabel;
    @FXML
    private FlowPane datePane;
    @FXML
    private FlowPane timePane;
    @FXML
    private FlowPane locationPane;
    @FXML
    private Label dateLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private VBox popupVBox;

    //@@author wongyewjon
    /**
     * Creates a {@code CalendarPopupContent} with the given CalendarEvent details.
     * @param session
     */

    public CalendarPopupContent(Session session) {
        super(FXML);
        studentLabel.setText(session.getSessionName().toString());
        dateLabel.setText(session.getStartDateTime());
        durationLabel.setText(session.getSessionDuration().toString());
        locationLabel.setText(session.getLocation().toString());
    }
}

