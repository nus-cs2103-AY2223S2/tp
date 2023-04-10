package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDAGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTAGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import seedu.dengue.commons.core.Messages;
import seedu.dengue.model.Model;
import seedu.dengue.model.predicate.FindPredicate;

/**
 * Finds and lists all persons in Dengue Hotspot Tracker whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified prefixes (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters:"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_POSTAL + "POSTAL] "
            + "[" + PREFIX_DATE + "DATE]"
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_VARIANT + "VARIANT]...\n"
            + "DATE and AGE can be replaced with specified range values with the following prefixes:\n"
            + "[" + PREFIX_STARTDATE + "STARTDATE]"
            + "[" + PREFIX_ENDDATE + "ENDDATE]\n"
            + "OR \n"
            + "[" + PREFIX_STARTAGE + "STARTAGE]"
            + "[" + PREFIX_ENDAGE + "ENDAGE]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_NAME + "Charlie"
            + PREFIX_POSTAL + "598765"
            + PREFIX_AGE + "13"
            + PREFIX_VARIANT + "DENV2"
            + PREFIX_STARTDATE + "2011-10-12"
            + PREFIX_ENDDATE + "2012-11-13";

    private final FindPredicate predicate;

    public FindCommand(FindPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert(!(predicate == null));
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
