package seedu.sprint.logic.commands;

import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.ApplicationModel;
import seedu.sprint.model.ApplicationModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.testutil.ApplicationBuilder;


/**
 * Contains integration tests (interaction with the ApplicationModel) for {@code AddApplicationCommand}.
 */
public class AddApplicationCommandIntegrationTest {

    private ApplicationModel model;
    private CommandHistory commandHistory = new CommandHistory();

    @BeforeEach
    public void setUp() {
        model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplication_success() {
        Application validApplication = new ApplicationBuilder().build();

        ApplicationModel expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
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
