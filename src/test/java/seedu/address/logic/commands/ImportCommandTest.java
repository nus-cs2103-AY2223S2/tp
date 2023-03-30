package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.person.Person;
import seedu.address.storage.Importer;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SampleEventUtil;
import seedu.address.testutil.TypicalPersons;

class ImportCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validImport_success() {

        AddressBook validImport = new AddressBook();
        Person personToImport = new PersonBuilder(TypicalPersons.ALICE).withTags().withGroups().build();
        validImport.addPerson(personToImport);

        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_PERSON_SUCCESS, TypicalPersons.ALICE);

        ImporterStub validImporter = new ImporterStub(validImport);
        ImportCommand successfulCommand = new ImportCommand(validImporter);
        assertCommandSuccess(successfulCommand, model, expectedMessage, model);
    }

    @Test
    void execute_validImportWithTagsGroups_successTagsGroupsIgnored() {
        AddressBook validImport = new AddressBook();
        Person personToImport = new PersonBuilder(TypicalPersons.ALICE)
                .withTags("SomeTag", "AnotherTag")
                .withGroups("SomeGroup", "AnotherGroup").build();
        validImport.addPerson(personToImport);

        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_PERSON_SUCCESS, TypicalPersons.ALICE);

        ImporterStub validImporter = new ImporterStub(validImport);
        ImportCommand successfulCommand = new ImportCommand(validImporter);
        assertCommandSuccess(successfulCommand, model, expectedMessage, model);
    }

    @Test
    void execute_validImportUpdatesEvents_success() {
        AddressBook validImport = new AddressBook();

        Set<IsolatedEvent> validIsolatedEventSet = new TreeSet<>();
        validIsolatedEventSet.add(SampleEventUtil.SKIING_ISOLATED_EVENT);
        validIsolatedEventSet.add(SampleEventUtil.GYM_ISOLATED_EVENT);

        Set<RecurringEvent> validRecurringEventSet = new TreeSet<>();
        validRecurringEventSet.add(SampleEventUtil.BIKING_RECURRING_EVENT);

        Person personToImport = new PersonBuilder(TypicalPersons.ALICE)
                .withTags()
                .withGroups()
                .withIsolatedEventList(validIsolatedEventSet)
                .withRecurringEventList(validRecurringEventSet)
                .build();

        validImport.addPerson(personToImport);

        Person expectedPerson = new PersonBuilder(TypicalPersons.ALICE)
                .withIsolatedEventList(validIsolatedEventSet)
                .withRecurringEventList(validRecurringEventSet)
                .build();

        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_PERSON_SUCCESS, expectedPerson);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(TypicalPersons.ALICE, expectedPerson);

        ImporterStub validImporter = new ImporterStub(validImport);
        ImportCommand successfulCommand = new ImportCommand(validImporter);
        assertCommandSuccess(successfulCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validImportUpdatesPersonalDetails_success() {
        AddressBook validImport = new AddressBook();
        PersonBuilder personToImport = new PersonBuilder(TypicalPersons.ALICE);
        personToImport.withEmail("aliceMalice@chalice.org");
        personToImport.withTags();
        personToImport.withGroups();
        validImport.addPerson(personToImport.build());

        Person expectedPerson = new PersonBuilder(TypicalPersons.ALICE).withEmail("aliceMalice@chalice.org").build();
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_PERSON_SUCCESS, expectedPerson);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(TypicalPersons.ALICE, expectedPerson);

        ImporterStub validImporter = new ImporterStub(validImport);
        ImportCommand successfulCommand = new ImportCommand(validImporter);
        assertCommandSuccess(successfulCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_personDoesNotExist_successAddNewPerson() {
        AddressBook validImport = new AddressBook();

        Person personToImport = new PersonBuilder(TypicalPersons.ALICE).withName("Bob the Builder").build();
        validImport.addPerson(personToImport);

        Person expectedPerson = new PersonBuilder(TypicalPersons.ALICE)
                .withName("Bob the Builder")
                .withTags()
                .withGroups()
                .build();
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_PERSON_SUCCESS, expectedPerson);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);

        ImporterStub validImporter = new ImporterStub(validImport);
        ImportCommand successfulCommand = new ImportCommand(validImporter);
        assertCommandSuccess(successfulCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidImportTwoPerson_throwsCommandException() {
        AddressBook invalidImport = new AddressBook();

        Person personToImport = new PersonBuilder(TypicalPersons.ALICE).withTags().withGroups().build();
        invalidImport.addPerson(personToImport);

        Person secondPersonToImport = new PersonBuilder(TypicalPersons.BENSON).withTags().withGroups().build();
        invalidImport.addPerson(secondPersonToImport);

        ImporterStub validImporter = new ImporterStub(invalidImport);
        ImportCommand unsuccessfulCommand = new ImportCommand(validImporter);
        assertCommandFailure(unsuccessfulCommand, model, Messages.MESSAGE_IMPORT_SIZE_WRONG);
    }

    @Test
    void execute_invalidImportEmptyAddressBook_throwsCommandException() {
        AddressBook invalidImport = new AddressBook();

        ImporterStub validImporter = new ImporterStub(invalidImport);
        ImportCommand unsuccessfulCommand = new ImportCommand(validImporter);
        assertCommandFailure(unsuccessfulCommand, model, Messages.MESSAGE_IMPORT_SIZE_WRONG);
    }

    private static class ImporterStub implements Importer {

        private final ReadOnlyAddressBook readOnlyAddressBook;

        ImporterStub(ReadOnlyAddressBook readOnlyAddressBook) {
            this.readOnlyAddressBook = readOnlyAddressBook;
        }

        @Override
        public ReadOnlyAddressBook readData() {
            return readOnlyAddressBook;
        }
    }
}
