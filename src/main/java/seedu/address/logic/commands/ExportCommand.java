package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEventList;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Export a person's information to a json file
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "All details except tags and groups are exported.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EXPORT_PERSON_SUCCESS = "Export Person: %1$s";

    private final Index targetIndex;

    private Person exportPerson;

    /**
     * Creates an ExportCommand to export the specified person at {@code Index}
     */
    public ExportCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToExport = lastShownList.get(targetIndex.getZeroBased());
        exportPerson = createExportPerson(personToExport);
        return new CommandResult(String.format(MESSAGE_EXPORT_PERSON_SUCCESS, exportPerson));
    }

    private static Person createExportPerson(Person personToExport) {

        assert personToExport != null;

        Name exportName = personToExport.getName();
        Phone exportPhone = personToExport.getPhone();
        Email exportEmail = personToExport.getEmail();
        Address exportAddress = personToExport.getAddress();
        Set<Tag> exportTags = new HashSet<>();
        Set<Group> exportGroups = new HashSet<>();
        IsolatedEventList exportIsolatedEvents = personToExport.getIsolatedEventList();
        RecurringEventList exportRecurringEvents = personToExport.getRecurringEventList();

        return new Person(exportName, exportPhone, exportEmail, exportAddress, exportTags, exportGroups,
                exportIsolatedEvents, exportRecurringEvents);
    }

    public Person getExportPerson() {
        return exportPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand// instanceof handles nulls
                && targetIndex.equals(((ExportCommand) other).targetIndex)); // state check
    }
}
