package seedu.internship.ui.pages;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.logic.commands.*;
import seedu.internship.model.event.Event;



/**
 * The Home page of TinS.
 */
public class HomePage extends Page {

    private static final String FXML = "HomePage.fxml";
    private static final String PAGE_TITLE = "Home";
    private static final String MESSAGE_WELCOME = "Welcome onboard.";
    
    private final Logger logger = LogsCenter.getLogger(HomePage.class);

    private static final String COMMAND_HELP = AddCommand.MESSAGE_USAGE + "\n\n"
            + CalendarCommand.MESSAGE_USAGE + "\n\n"
            + ClashCommand.MESSAGE_USAGE + "\n\n"
            + DeleteAllCommand.MESSAGE_USAGE + "\n\n"
            + DeleteCommand.MESSAGE_USAGE + "\n\n"
            + EditCommand.MESSAGE_USAGE + "\n\n"
            + ExitCommand.MESSAGE_USAGE + "\n\n"
            + FindCommand.MESSAGE_USAGE + "\n\n"
            + HelpCommand.MESSAGE_USAGE + "\n\n"
            + HomeCommand.MESSAGE_USAGE + "\n\n"
            + ListCommand.MESSAGE_USAGE + "\n\n"
            + SelectCommand.MESSAGE_USAGE + "\n\n"
            + StatsCommand.MESSAGE_USAGE + "\n\n";

    private ObservableList<Event> reminderEvents;

    @javafx.fxml.FXML
    private ScrollPane pageContainer;

    @FXML
    private Label pageTitle;
    @FXML
    private Label pageSubtitle;

    @FXML
    private Label noReminderText;
    @FXML
    private HBox reminderBox;

    @FXML
    private VBox helpBox;

    @FXML
    private Label commands;


    /**
     * Creates a {@code HomePage} displaying all information of an Internship.
     */
    public HomePage(ObservableList<Event> events) {
        super(FXML);
        reminderEvents = events;
        setHeadContent();
        setBodyContent();
    }

    /**
     * Fills in the content for the page's title and subtitle.
     */
    public void setHeadContent() {
        pageTitle.setText(PAGE_TITLE);
        pageSubtitle.setText(MESSAGE_WELCOME);
    }


    /**
     * Sets the content for the body of HomePage.
     * The body of HomePage contains the Events Reminder segment and the Useful Commands segment.
     */
    public void setBodyContent() {
        // setting reminderBox
        if (!reminderEvents.isEmpty()) {
            noReminderText.setManaged(false);
            reminderBox.getChildren().addAll(reminderEvents.stream()
                    .map(event -> EventCard.of(event, false)
                            .getRoot())
                    .collect(Collectors.toList()));
        } else {
            noReminderText.setManaged(true);
            reminderBox.setManaged(false);
        }

        // setting helpBox
        commands.setText(COMMAND_HELP);
    }

}
