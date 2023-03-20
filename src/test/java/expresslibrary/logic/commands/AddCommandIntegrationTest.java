package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.person.Person;
import expresslibrary.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getExpressLibrary().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
