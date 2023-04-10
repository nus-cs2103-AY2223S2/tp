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
import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.CalendarCommand;
import seedu.internship.logic.commands.ClashCommand;
import seedu.internship.logic.commands.DeleteAllCommand;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.EditCommand;
import seedu.internship.logic.commands.ExitCommand;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.commands.HelpCommand;
import seedu.internship.logic.commands.HomeCommand;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.commands.SelectCommand;
import seedu.internship.logic.commands.StatsCommand;
import seedu.internship.logic.commands.event.EventAddCommand;
import seedu.internship.logic.commands.event.EventDeleteCommand;
import seedu.internship.logic.commands.event.EventFindCommand;
import seedu.internship.model.event.Event;

/**
 * The Home page of TinS.
 */
public class HomePage extends Page {

    private static final String FXML = "HomePage.fxml";
    private static final String PAGE_TITLE = "Home";
    private static final String MESSAGE_WELCOME = "Welcome onboard.";

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
            + StatsCommand.MESSAGE_USAGE + "\n\n"
            + EventAddCommand.MESSAGE_USAGE + "\n\n"
            + EventDeleteCommand.MESSAGE_USAGE + "\n\n"
            + EventFindCommand.MESSAGE_USAGE + "\n\n";

    private final Logger logger = LogsCenter.getLogger(HomePage.class);

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
                    .map(event -> {
                        EventCard eventCard = EventCard.of(event, false);
                        eventCard.setEventCardContent();
                        return eventCard.getRoot();
                    })
                    .collect(Collectors.toList()));
        } else {
            noReminderText.setManaged(true);
            reminderBox.setManaged(false);
        }

        // setting helpBox
        commands.setText(COMMAND_HELP);
    }

}
