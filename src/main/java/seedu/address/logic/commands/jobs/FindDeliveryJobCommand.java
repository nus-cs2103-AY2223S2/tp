package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Finds and lists all jobs in job system whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindDeliveryJobCommand extends DeliveryJobCommand {

    public static final String COMMAND_WORD = "find_job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all jobs which contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: keyword_type/KEYWORD [more_type/MORE_KEYWORDS]...\n"
            + "Format: " + COMMAND_WORD + " [ji/JOB_ID]" + " [si/SENDER_ID] [ri/RECIPIENT_ID] "
            + "[date/DELIVER_DATE] [slot/DELIVERY_SLOT] [earn/EARNING]..."
            + "\nExample: " + COMMAND_WORD + " si/ALE874";

    private final Predicate<DeliveryJob> predicate;
    private final String query;

    /**
     * Constructs a FindDeliveryJobCommand.
     * @param predicate
     * @param query for feedback
     */
    public FindDeliveryJobCommand(Predicate<DeliveryJob> predicate, String query) {
        this.predicate = predicate;
        this.query = query;
    }

    /**
     * Constructs a FindDeliveryJobCommand without feedback.
     * @param predicate
     * @param query for feedback
     */
    public FindDeliveryJobCommand(Predicate<DeliveryJob> predicate) {
        this.predicate = predicate;
        this.query = "";
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDeliveryJobList(predicate);
        return new CommandResult(
                String.format(
                        Messages.MESSAGE_DELIVERY_JOB_LISTED_OVERVIEW + "\nQuery options:\n" + query,
                        model.getFilteredDeliveryJobList().size()),
                false, false, false, false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDeliveryJobCommand // instanceof handles nulls
                && predicate.equals(((FindDeliveryJobCommand) other).predicate)); // state check
    }
}
