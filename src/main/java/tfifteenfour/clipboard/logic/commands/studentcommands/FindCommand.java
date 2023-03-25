package tfifteenfour.clipboard.logic.commands.studentcommands;

import static java.util.Objects.requireNonNull;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.student.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates a FindCommand to search the list for names containing the specified string
     *
     * @param predicate the predicate tester for checking if the student name contains the search string
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        super(false);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CurrentSelection currentSelection) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(this,
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getUnmodifiableFilteredStudentList().size()), willModifyState);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
