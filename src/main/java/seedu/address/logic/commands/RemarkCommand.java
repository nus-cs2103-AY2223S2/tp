package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.INDEX_PLACEHOLDER;
import static seedu.address.logic.parser.CliSyntax.REMARK_PLACEHOLDER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;


/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(
            INDEX_PLACEHOLDER,
            REMARK_PLACEHOLDER.asOptional()
    ));

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: "
            + ARGUMENT_PREFIXES.stream()
                    .map(Prefix::toString)
                    .collect(Collectors.joining(" "))
            + "\nExample: " + COMMAND_WORD + " 1 Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * Constructor for RemarkCommand
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (this.remark == null) {
            return new CommandResult("Editing remark...", false, false,
                    personToEdit.getOptionalRemark().map(String::valueOf).orElse(""));
        }
        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getOptionalPhone().orElse(null),
                personToEdit.getOptionalEmail().orElse(null),
                personToEdit.getOptionalAddress().orElse(null),
                personToEdit.getOptionalEducation().orElse(null),
                remark.value.equals("\0") ? null : remark,
                personToEdit.getOptionalTelegram().orElse(null),
                personToEdit.getModules(),
                personToEdit.getTags()
        );

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook(COMMAND_WORD);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = personToEdit.getOptionalRemark().isPresent()
                ? MESSAGE_ADD_REMARK_SUCCESS
                : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && (remark == e.remark // handles when remark is null
                || remark.equals(e.remark));
    }
}
