package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.testutil.ApplicationBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddApplicationCommand}.
 */
public class AddApplicationCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplication_success() {
        Application validApplication = new ApplicationBuilder().build();

        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.addApplication(validApplication);
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(new AddApplicationCommand(validApplication), model, commandHistory,
                String.format(AddApplicationCommand.MESSAGE_SUCCESS, validApplication), expectedModel);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application appInList = model.getInternshipBook().getApplicationList().get(0);
        assertCommandFailure(new AddApplicationCommand(appInList),
                model, commandHistory, AddApplicationCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

}
