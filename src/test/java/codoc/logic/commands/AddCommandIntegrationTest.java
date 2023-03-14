package codoc.logic.commands;

import static codoc.logic.commands.CommandTestUtil.assertCommandFailure;
import static codoc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static codoc.testutil.TypicalPersons.getTypicalCodoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.UserPrefs;
import codoc.model.person.Person;
import codoc.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCodoc(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getCodoc(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getCodoc().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
