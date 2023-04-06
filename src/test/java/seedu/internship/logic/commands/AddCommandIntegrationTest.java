package seedu.internship.logic.commands;

import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
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
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;
import seedu.internship.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {

        Internship validInternship = new InternshipBuilder().build();

        Model expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(),
                new UserPrefs());
        expectedModel.addInternship(validInternship);
        expectedModel.updateFilteredEventList(new EventByInternship(model.getSelectedInternship()));

        ObservableList<Event> events = model.getFilteredEventList();

        assertCommandSuccess(new AddCommand(validInternship), model,
                new CommandResult(String.format(AddCommand.MESSAGE_SUCCESS, validInternship), ResultType.SHOW_INFO,
                        validInternship, events), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Internship internshipInList = model.getInternshipCatalogue().getInternshipList().get(0);
        assertCommandFailure(new AddCommand(internshipInList), model, AddCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

}
