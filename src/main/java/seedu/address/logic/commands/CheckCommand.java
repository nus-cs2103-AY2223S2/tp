package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Creates check command for user to find the respective student's information they want to check.
 */
public class CheckCommand extends Command {
    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Check the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CHECK_PERSON_SUCCESS = "Check Person: %1$s";

    private final Index targetIndex;

    public CheckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToCheck = personList.get(targetIndex.getZeroBased());
        model.checkPerson(personToCheck);
        String nameKeywords = personToCheck.getName().fullName;

        model.updateFilteredPersonList(person -> person.getName().fullName.equals(nameKeywords));
        return new CommandResult(String.format(MESSAGE_CHECK_PERSON_SUCCESS, personToCheck));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckCommand // instanceof handles nulls
                && targetIndex.equals(((CheckCommand) other).targetIndex)); // state check
    }
}
