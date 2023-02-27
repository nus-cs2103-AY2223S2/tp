package arb.logic.commands.client;

import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.person.Person;
import arb.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddClientCommand}.
 */
public class AddClientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddClientCommand(validPerson), model,
                String.format(AddClientCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddClientCommand(personInList), model, AddClientCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
