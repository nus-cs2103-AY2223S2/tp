package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "Tutorial/1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Event: %1$s";

    private final Index targetIndex;

    private boolean isTutorial;
    private boolean isLab;
    private boolean isConsultation;

    public DeleteEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        isTutorial = false;
        isLab = false;
        isConsultation = false;
    }

    void markTutorial() {
        isTutorial = true;
        //ensures no mishandling of cases
        isLab = false;
        isConsultation = false;
    }

    void markLab() {
        isLab = true;
        //ensures no mishandling of cases
        isTutorial = false;
        isConsultation = false;
    }

    void markConsultation() {
        isConsultation = true;
        //ensures no mishandling of cases
        isLab = false;
        isTutorial = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteEventCommand) other).targetIndex)); // state check
    }
}
