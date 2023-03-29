package seedu.internship.ui.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Objects.isNull;


public class ClashInfoPage extends Page {

    private static final String FXML = "ClashInfoPage.fxml";
    private final Logger logger = LogsCenter.getLogger(ClashInfoPage.class);

    private HashMap<Event, List<Event>> clashes;

    private static final String PAGE_TITLE = "Event clashes";
    private static final String MESSAGE_TIP = "Click on any Event to view a list of events that clash with it.";
    private static final String MESSAGE_NO_CLASHES = "No clashes found :)";

    @javafx.fxml.FXML
    private ScrollPane pageContainer;

    @FXML
    private Label pageTitle;
    @FXML
    private Label pageSubtitle;

    @FXML
    private Accordion clashesBox;



    public ClashInfoPage(HashMap<Event, List<Event>> clashes) {
        super(FXML);
        this.clashes = clashes;
        setHeadContent();
        setBodyContent();
    }

    /**
     * Fills in the content for the page's title and subtitle.
     */
    public void setHeadContent() {
        pageTitle.setText(PAGE_TITLE);
        if (!clashes.isEmpty()) {
            pageSubtitle.setText(MESSAGE_TIP);
        } else {
            pageSubtitle.setText(MESSAGE_NO_CLASHES);
        }
    }


    /**
     * Sets the content for the body of HomePage.
     * The body of HomePage contains the Events Reminder segment and the Useful Commands segment.
     */
    public void setBodyContent() {

        ArrayList<TitledPane> panes = new ArrayList<>();

        for (Event event : clashes.keySet()) {
            // generate a list of Labels
            String companyAndPosition = String.format("[%s, %s] ",
                    event.getInternship().getCompany(),
                    event.getInternship().getPosition());
            TitledPane pane = new TitledPane(companyAndPosition + event.getName(),
                    new ClashInfoItem(event, clashes.get(event)).getRoot());
            panes.add(pane);

        }
        clashesBox.getPanes().addAll(panes);
    }
}
