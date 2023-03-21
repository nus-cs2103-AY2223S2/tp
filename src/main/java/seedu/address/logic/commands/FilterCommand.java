package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tutee.FieldContainsKeywordsPredicate;

/**
 * Filters and lists all tutees in address book whose field matches
 * any of the argument keywords. Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters through all persons in the tutee managing system, "
            + "showing only the tutees that matches the parameters that have been provided\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME] "
            + PREFIX_PHONE + "PHONE] "
            + PREFIX_EMAIL + "EMAIL] "
            + PREFIX_ADDRESS + "ADDRESS] "
            + PREFIX_SUBJECT + "SUBJECT] "
            + PREFIX_SCHEDULE + "SCHEDULE] "
            + PREFIX_STARTTIME + "START TIME] "
            + PREFIX_ENDTIME + "END TIME] "
            + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_PHONE + "987 "
            + PREFIX_EMAIL + "johnd@e "
            + PREFIX_ADDRESS + "311, Clementi "
            + PREFIX_SUBJECT + "Math "
            + PREFIX_SCHEDULE + "monday "
            + PREFIX_STARTTIME + "08:30 "
            + PREFIX_ENDTIME + "10:30 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_NO_FIELD_GIVEN = "At least one field must be provided to filter.";
    public static final String MESSAGE_EMPTY_FIELD = "Field values should not be blank";

    private final FieldContainsKeywordsPredicate predicate;

    public FilterCommand(FieldContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTuteeList(subjectPredicate.and(schedulePredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredTuteeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && subjectPredicate.equals(((FilterCommand) other).subjectPredicate)
                && schedulePredicate.equals(((FilterCommand) other).schedulePredicate)); // state check
    }
}

