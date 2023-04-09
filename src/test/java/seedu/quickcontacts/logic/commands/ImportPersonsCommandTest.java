package seedu.quickcontacts.logic.commands;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.UserPrefs;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.testutil.TypicalPersons;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

public class ImportPersonsCommandTest {
    @Test
    public void success() {
        Model model = new ModelManager(new QuickBook(), new UserPrefs());
        ImportPersonsCommand command = new ImportPersonsCommand(TypicalPersons.getTypicalPersons(), false);
        Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBookPersonsOnly(),
                new UserPrefs());
        CommandTestUtil.assertCommandSuccess(command, model, ImportPersonsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicateForced_success() {
        Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookPersonsOnly(),
                new UserPrefs());
        List<Person> peopleToImport = List.of(
                TypicalPersons.getTypicalPersons().get(0),
                TypicalPersons.getTypicalPersons().get(1));
        model.deletePerson(TypicalPersons.getTypicalPersons().get(1));
        ImportPersonsCommand command = new ImportPersonsCommand(peopleToImport, true);
        Model expectedModel = new ModelManager(TypicalQuickBooks.getTypicalQuickBookPersonsOnly(),
                new UserPrefs());
        expectedModel.deletePerson(TypicalPersons.getTypicalPersons().get(1));
        expectedModel.addPerson(TypicalPersons.getTypicalPersons().get(1));
        CommandTestUtil.assertCommandSuccess(command, model, ImportPersonsCommand.SUCCESS, expectedModel);
    }

    @Test
    public void duplicate_failure() {
        Model model = new ModelManager(TypicalQuickBooks.getTypicalQuickBookPersonsOnly(), new UserPrefs());
        ImportPersonsCommand command = new ImportPersonsCommand(List.of(TypicalPersons.getTypicalPersons().get(0)),
                false);
        CommandTestUtil.assertCommandFailure(command, model, ImportPersonsCommand.DUPLICATE_PERSON);
    }
}
