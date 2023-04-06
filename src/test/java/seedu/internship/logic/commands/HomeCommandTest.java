package seedu.internship.logic.commands;



import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.event.Event;


public class HomeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(), new UserPrefs());
    }

    @Test
    public void execute_home_success() {
        expectedModel.updateFilteredEventList(HomeCommand.PREDICATE_EVENT_REMINDER);
        ObservableList<Event> expectedEvents = expectedModel.getFilteredEventList();
        CommandResult expectedCommandResult =
                new CommandResult(HomeCommand.SHOWING_HOME_MESSAGE, ResultType.HOME, expectedEvents);
        assertCommandSuccess(new HomeCommand(), model, expectedCommandResult, expectedModel);


    }

}
