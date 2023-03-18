package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ModuleTag;

/**
 * Sorts the persons by any field.
 * If it is a tag field, then sort by the number of common tags.
 * Else, sort by alphanumerical order.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts persons by any field. "
            + "If it is a tag field, then sort by the number of common tags. "
            + "Otherwise, sort by alphanumerical order.\n"
            + "Add an 'a' behind the prefix to sort the field by ascending order. "
            + "Likewise, add a 'd' behind the prefix to sort the field by descending order.\n"
            + "You can chain sorts together to deal with tie breaks.\n"
            + "Parameters: "
            + "PREFIX/a or PREFIX/d\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + Prefix.GROUP_TAG + "a" + Prefix.NAME + "d\n"
            + COMMAND_WORD + " " + Prefix.MODULE_TAG + "\n"
            + "If you want to sort by your original contact's index, then the command is:\n"
            + COMMAND_WORD + " " + "index";
    private final Comparator<Person> comparator;
    private final String comparatorDesc;

    /**
     * @param comparator to compare the persons
     * @param comparatorDesc that describes what the sort command sorts by
     */
    public SortCommand(Comparator<Person> comparator, String comparatorDesc) {
        this.comparator = comparator;
        this.comparatorDesc = comparatorDesc;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateObservablePersonList(comparator);

        return new CommandResult(
                String.format("Sorted by:\n%s", comparatorDesc));
    }

    @Override
    public boolean equals(Object other) {
        // we don't check for comparator equality because it is not possible
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparatorDesc.equals(((SortCommand) other).comparatorDesc));
    }
}
