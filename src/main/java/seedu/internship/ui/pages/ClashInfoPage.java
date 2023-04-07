package seedu.internship.ui.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;

/**
 * A {@code Page} to be shown to display clashing events as a result of the `clash` command.
 */
public class ClashInfoPage extends Page {

    private static final String FXML = "ClashInfoPage.fxml";
    private static final String PAGE_TITLE = "Event clashes";
    private static final String MESSAGE_TIP = "Click on any date to view a list of events that clash on that day.";
    private static final String MESSAGE_NO_CLASHES = "No clashes found :)";
    private static final String DATE_PATTERN = "d MMM uuuu EEE";

    private final Logger logger = LogsCenter.getLogger(ClashInfoPage.class);

    private HashMap<LocalDate, List<Event>> clashes;

    @javafx.fxml.FXML
    private ScrollPane pageContainer;

    @FXML
    private Label pageTitle;
    @FXML
    private Label pageSubtitle;

    @FXML
    private Accordion clashesBox;


    /**
     * Constructor for {@code ClashInfoPage}.
     *
     * @param clashes A hashmap of clashing events.
     */
    public ClashInfoPage(HashMap<LocalDate, List<Event>> clashes) {
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
     * Sets the content for the body of ClashInfoPage.
     */
    public void setBodyContent() {

        ArrayList<TitledPane> panes = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        List<LocalDate> keys = clashes.keySet().stream().sorted().collect(Collectors.toList());
        for (LocalDate date : keys) {
            String title = date.format(formatter);
            TitledPane pane = new TitledPane(title,
                    new ClashInfoItem(clashes.get(date)).getRoot());
            panes.add(pane);

        }
        clashesBox.getPanes().addAll(panes);
    }
}
