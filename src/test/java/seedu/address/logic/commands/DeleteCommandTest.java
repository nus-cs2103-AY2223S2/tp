package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model model2 = createSampleModel();

    private Model createSampleModel() {
        AddressBook ab = new AddressBook();
        Person sf = new Person(new Name("sf"), new Phone("12345678"),
                new Email("sf@123.com"), new Address("com 1 St"), new Age("20"), new HashSet<>());
        Person xz = new Person(new Name("xz"), new Phone("12345678"),
                new Email("xz@123.com"), new Address("com 2 St"), new Age("19"), new HashSet<>());
        Person wd = new Person(new Name("wd"), new Phone("12345678"),
                new Email("wd@123.com"), new Address("com 3 St"), new Age("18"), new HashSet<>());
        Person xy = new Person(new Name("xy"), new Phone("12345678"),
                new Email("xz@123.com"), new Address("com 4 St"), new Age("17"), new HashSet<>());
        Person cx = new Person(new Name("cx"), new Phone("12345678"),
                new Email("cx@123.com"), new Address("com 5 St"), new Age("16"), new HashSet<>());


        ab.addPerson(sf);
        ab.addPerson(xz);
        ab.addPerson(wd);
        ab.addPerson(xy);
        ab.addPerson(cx);
        return new ModelManager(ab, new UserPrefs());
    }

    @Test
    void execute_validIndex_success() {
        Index third = Index.fromOneBased(3);
        DeleteCommand deleteCommand = new DeleteCommand(third);

        String expectedMessage = "Deleted Person: wd; Phone: 12345678; Email: wd@123.com; Address: com 3 St";
        assertDoesNotThrow(() -> {
            CommandResult commandResult = deleteCommand.execute(model2);
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        });
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
