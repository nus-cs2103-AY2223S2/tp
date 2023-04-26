package taa.logic.parser;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.DeleteAlarmCommand;
import taa.logic.parser.exceptions.ParseException;

/**
 * Defines a parser for delete alarm command
 */
public class DeleteAlarmCommandParser implements Parser<DeleteAlarmCommand> {

    /**
     * Parses the command line arguments and identify the alarm that is to be deleted/
     * @param args command line arguments
     * @return the corresponding delete alarm command
     * @throws ParseException if the parsing fails
     */
    public DeleteAlarmCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteAlarmCommand(index.getOneBased());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteAlarmCommand.MESSAGE_USAGE), pe);
        }
    }
}
