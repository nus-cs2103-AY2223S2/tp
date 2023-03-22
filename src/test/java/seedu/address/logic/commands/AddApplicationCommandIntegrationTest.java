package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddApplicationCommandIntegrationTest {

    private ApplicationModel model;

    @BeforeEach
    public void setUp() {
        model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Application validApplication = new ApplicationBuilder().build();

        ApplicationModel expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.addApplication(validApplication);

        assertCommandSuccess(new AddApplicationCommand(validApplication), model,
                String.format(AddApplicationCommand.MESSAGE_SUCCESS, validApplication), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Application appInList = model.getInternshipBook().getApplicationList().get(0);
        assertCommandFailure(new AddApplicationCommand(appInList),
                model, AddApplicationCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

}
