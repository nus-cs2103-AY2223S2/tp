package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Filters and list all students in mathutoring that has tag/s which contains any of the
 * filter keyword/s.
 * Keyword matching is case-insensitive, but only full words will be matched.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose has any tag "
            + "that matches with the filter condition (case-insensitive) and displays the "
            + "filtered result as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " primary";

    private final TagContainsKeywordsPredicate predicate;

    /**
     * Constructs a {@code FilterCommand} with the specified {@code TagContainsKeywordsPredicate},
     * filter out the student/s that fulfill the given predicate.
     *
     * @param predicate The user provided predicate.
     */
    public FilterCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the filter command result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        logger.info("Filter student base on their tag success.");

        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    /**
     * Checks if an object is equal to the filter command.
     *
     * @param other The object that need to compare with.
     * @return A boolean value if the object and the filter command are the same.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
