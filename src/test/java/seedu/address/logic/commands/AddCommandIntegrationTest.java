package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTuteeManagingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTuteeManagingSystem(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() throws IllegalValueException {
        Tutee validTutee = new TuteeBuilder().build();

        Model expectedModel = new ModelManager(model.getTuteeManagingSystem(), new UserPrefs());
        expectedModel.addTutee(validTutee);

        assertCommandSuccess(new AddCommand(validTutee), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTutee), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Tutee tuteeInList = model.getTuteeManagingSystem().getPersonList().get(0);
        assertCommandFailure(new AddCommand(tuteeInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
