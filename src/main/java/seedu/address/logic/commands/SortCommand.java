package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the address book and lists them to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted by: ";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sorts the list of contacts by the given category. "
        + "Sorts by name if none is given.\n"
        + "Parameters: [KEYWORD]\n"
        + "Example: \"" + COMMAND_WORD + " address\" will sort the list by address.";

    private final String category;

    public SortCommand(String category) {
        this.category = category;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(category);
        model.commitSocket();
        return new CommandResult(MESSAGE_SUCCESS + category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortCommand // instanceof handles nulls
            && category.equals(((SortCommand) other).category)); // state check
    }
}
