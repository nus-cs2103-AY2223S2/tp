package taa.logic.parser;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.DeleteAlarm;
import taa.logic.parser.exceptions.ParseException;

public class DeleteAlarmParser implements Parser<DeleteAlarm>{
    public DeleteAlarm parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteAlarm(index.getOneBased());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteAlarm.MESSAGE_USAGE), pe);
        }
    }
}
