package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteAppointmentCommand} object
 */
public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeletePolicyCommand}
     * @return an {@code DeletePolicyCommand} object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            logger.info("Parsed: " + args);
            return new DeleteAppointmentCommand(index);
        } catch (ParseException pe) {
            logger.info("Missing Index: " + args);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
