package seedu.address.logic.parser.jobs;

import seedu.address.logic.commands.jobs.DeleteDeliveryJobCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteDeliveryJobCommandParser implements Parser<DeleteDeliveryJobCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDeliveryJobCommand parse(String args) throws ParseException {
        String jobId = args.trim();

        if (jobId.isEmpty()) {
            throw new ParseException(DeleteDeliveryJobCommand.MESSAGE_MISSING_JOB_ID);
        }
        return new DeleteDeliveryJobCommand(jobId);
    }

}
