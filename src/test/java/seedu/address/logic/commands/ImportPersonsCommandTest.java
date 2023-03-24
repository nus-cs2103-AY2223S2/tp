package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalAddressBooks;
import seedu.address.testutil.TypicalPersons;

public class ImportPersonsCommandTest {
    @Test
    public void success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportPersonsCommand command = new ImportPersonsCommand(TypicalPersons.getTypicalPersons(), false);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(),
                new UserPrefs());
        assertCommandSuccess(command, model, ImportPersonsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicateForced_success() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(),
                new UserPrefs());
        List<Person> peopleToImport = List.of(
                TypicalPersons.getTypicalPersons().get(0),
                TypicalPersons.getTypicalPersons().get(1));
        model.deletePerson(TypicalPersons.getTypicalPersons().get(1));
        ImportPersonsCommand command = new ImportPersonsCommand(peopleToImport, true);
        Model expectedModel = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(),
                new UserPrefs());
        expectedModel.deletePerson(TypicalPersons.getTypicalPersons().get(1));
        expectedModel.addPerson(TypicalPersons.getTypicalPersons().get(1));
        assertCommandSuccess(command, model, ImportPersonsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicate_failure() {
        Model model = new ModelManager(TypicalAddressBooks.getTypicalAddressBookPersonsOnly(), new UserPrefs());
        ImportPersonsCommand command = new ImportPersonsCommand(List.of(TypicalPersons.getTypicalPersons().get(0)),
                false);
        assertCommandFailure(command, model, ImportPersonsCommand.DUPLICATE_PERSON);
    }
}
