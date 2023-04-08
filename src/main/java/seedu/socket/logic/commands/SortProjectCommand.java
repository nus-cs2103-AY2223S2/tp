package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.socket.model.Model;

/**
 * Sorts all persons in SOCket and lists them to the user.
 */
public class SortProjectCommand extends Command {

    public static final String COMMAND_WORD = "sortpj";

    public static final String MESSAGE_SUCCESS = "Sorted by: ";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sorts the list of projects by the given category. "
        + "Sorts by deadline if no category is given.\n"
        + "Parameters: [KEYWORD]\n"
        + "Example: \"" + COMMAND_WORD + " name\"\n"
        + "The following are the available categories:\n"
        + "name, repohost, reponame, deadline, meeting";

    private final String category;

    public SortProjectCommand(String category) {
        this.category = category;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortProjectList(category);
        model.commitSocket();
        return new CommandResult(MESSAGE_SUCCESS + category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortProjectCommand // instanceof handles nulls
            && category.equals(((SortProjectCommand) other).category)); // state check
    }
}
