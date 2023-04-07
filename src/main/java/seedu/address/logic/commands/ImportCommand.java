package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Importer;
import seedu.address.storage.JsonImporter;
import seedu.address.storage.exceptions.JsonNotFoundException;

/**
 * Represents a {@code Command} that imports data of a single {@code Person} into the current Address Book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Import the person data contained in an "
            + "export file. If the person already exists, the personal information and events are updated."
            + "Groups and Tags are not modified."
            + "If not, a new person is added with no Groups and Tags.\n"
            + "File must be stored in folder with the path \"./data\" "
            + "and must be named \"export.json\" to be read.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_IMPORT_PERSON_SUCCESS = "Import Person: %1$s";

    private static final Path standardPath = Path.of("data", "export.json");

    private final Importer importer;

    public ImportCommand(Importer importer) {
        this.importer = importer;
    }

    public ImportCommand() {
        this.importer = new JsonImporter(standardPath);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws seedu.address.logic.commands.exceptions.CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        ReadOnlyAddressBook importReadOnlyAddressBook;

        try {
            importReadOnlyAddressBook = importer.readData();
        } catch (DataConversionException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_DATA);
        } catch (JsonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_IMPORT);
        }

        assert(importReadOnlyAddressBook != null);
        List<Person> importPersonList = importReadOnlyAddressBook.getPersonList();

        if (importPersonList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_IMPORT_SIZE_WRONG);
        }

        Person importedPerson = importPersonList.get(0);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> allPersonList = model.getFilteredPersonList();

        Person personToAdd;
        for (Person listPerson : allPersonList) {
            if (listPerson.isSamePerson(importedPerson)) {
                personToAdd = createImportPerson(importedPerson, listPerson);
                model.setPerson(listPerson, personToAdd);
                return new CommandResult(String.format(MESSAGE_IMPORT_PERSON_SUCCESS, personToAdd));
            }
        }
        personToAdd = createNewImportPerson(importedPerson);
        model.addPerson(personToAdd);
        return new CommandResult(String.format(MESSAGE_IMPORT_PERSON_SUCCESS, personToAdd));
    }

    private static Person createImportPerson(Person importData, Person existingData) {
        return new Person(importData.getName(),
                importData.getPhone(),
                importData.getEmail(),
                importData.getAddress(),
                existingData.getTags(), // Do not replace tags
                existingData.getGroups(), // Do not replace groups
                importData.getIsolatedEventList(),
                importData.getRecurringEventList());
    }
    private static Person createNewImportPerson(Person importData) {
        return new Person(importData.getName(),
                importData.getPhone(),
                importData.getEmail(),
                importData.getAddress(),
                new HashSet<>(),
                new HashSet<>(),
                importData.getIsolatedEventList(),
                importData.getRecurringEventList());
    }
}
