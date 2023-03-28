package seedu.internship.ui.pages;

import com.calendarfx.view.popover.PopOverContentPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;
import seedu.internship.ui.UiPart;

import java.util.logging.Logger;

public class CalendarPopOver extends UiPart<Region> {
    private static final String FXML = "CalendarPopOver.fxml";

    private final Logger logger = LogsCenter.getLogger(ClashInfoPage.class);

    private Event event;


    @javafx.fxml.FXML
    private Label startLabel;
    @FXML
    private Label start;
    @FXML
    private Label endLabel;
    @FXML
    private Label end;
    @FXML
    private Label position;
    @FXML
    private Label title;
    @FXML
    private Label company;

    public CalendarPopOver(Event event) {
        super(FXML);
        this.event = event;
        setHeader();
        setBody();
    }

    public void setHeader() {
        company.setText(event.getInternship().getCompany().companyName);
        title.setText(event.getName().name);
        position.setText(event.getInternship().getPosition().positionName);
    }
    public void setBody() {
        if (event.isDeadline()) {
            start.setManaged(false);
            startLabel.setManaged(false);
            endLabel.setText("Deadline: ");
            end.setText(event.getEnd().toString());
        } else {
            start.setManaged(true);
            startLabel.setManaged(true);
            start.setText(event.getStart().toString());
            endLabel.setText("End: ");
            end.setText(event.getEnd().toString());
        }
    }


}