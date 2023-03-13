package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.CARNIVAL;
import static seedu.address.testutil.TypicalIndexSets.INDEX_SET_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexSets.INDEX_SET_NO_EVENT;
import static seedu.address.testutil.TypicalIndexSets.INDEX_SET_SECOND_EVENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

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

        assertCommandSuccess(new AddCommand(validPerson, INDEX_SET_NO_EVENT), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }


    @Test
    public void execute_newPersonWithEvent_success() {
        Person validPerson = new PersonBuilder().withEventSet(CARNIVAL).build();

        AddCommand addCommand = new AddCommand(validPerson, INDEX_SET_SECOND_EVENT);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        String expectedString = String.format(AddCommand.MESSAGE_SUCCESS_ADD_EVENT, validPerson);

        assertCommandSuccess(addCommand, model, expectedString, expectedModel);
    }


    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList, INDEX_SET_FIRST_EVENT), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
