package bookopedia.logic.commands;

import bookopedia.model.AddressBook;
import bookopedia.model.Model;
import bookopedia.model.ModelManager;
import bookopedia.model.UserPrefs;
import bookopedia.model.person.Person;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bookopedia.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookopedia.testutil.TypicalPersons.ALICE;
import static bookopedia.testutil.TypicalPersons.BENSON;
import static bookopedia.testutil.TypicalPersons.CARL;
import static bookopedia.testutil.TypicalPersons.DANIEL;
import static bookopedia.testutil.TypicalPersons.ELLE;
import static bookopedia.testutil.TypicalPersons.FIONA;
import static bookopedia.testutil.TypicalPersons.GEORGE;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;

class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final List<Person> sortedList = Arrays.asList(DANIEL, ELLE, FIONA, GEORGE, BENSON, CARL, ALICE);

    @Test
    public void executeSort() {
        SortCommand sortCommand = new SortCommand();
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS);

        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        expectedAddressBook.setPersons(sortedList);
        Model expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}