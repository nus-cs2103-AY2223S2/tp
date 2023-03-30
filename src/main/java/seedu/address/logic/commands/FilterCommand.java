package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.experimental.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;


/**
 * Filters all entities in the address book to the user according to the given tag.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_SUCCESS = "Filtered all entities";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the entities of a provided tag";

    private final List<String> tagList;

    /**
     * Saves the args to filter later
     *
     * @param asList
     */
    public FilterCommand(List<String> asList) {
        requireNonNull(asList);
        this.tagList = asList;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addPredicate(entity -> true);
        model.addPredicate(entity -> {
            boolean result = true;
            for (String tag : tagList) {
                if (!entity.getTags().contains(new Tag(tag))) {
                    result = false;
                }
            }

            return result;
        });

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
