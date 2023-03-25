package seedu.address.logic.parser.jobs;

import seedu.address.logic.commands.jobs.CompleteDeliveryJobCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class CompleteDeliveryJobCommandParser implements Parser<CompleteDeliveryJobCommand> {

    private Boolean setDelivered;

    /**
     * Constructs a CompleteDeliveryJobCommandParser.
     *
     * @param setDelivered
     */
    public CompleteDeliveryJobCommandParser(Boolean setDelivered) {
        this.setDelivered = setDelivered;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompleteDeliveryJobCommand parse(String args) throws ParseException {
        String jobId = args.trim();
        return new CompleteDeliveryJobCommand(jobId, setDelivered);
    }

}
