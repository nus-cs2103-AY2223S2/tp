package seedu.internship.logic.parser;

import seedu.internship.logic.commands.DeleteAllCommand;
import seedu.internship.logic.parser.exceptions.ParseException;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteAllCommandParser implements Parser<DeleteAllCommand> {

    public DeleteAllCommand parse(String args) throws ParseException {
        String code = args.trim();
        if (!code.equals(DeleteAllCommand.CONFIRMATION_CODE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAllCommand.MESSAGE_USAGE));
        }
        return new DeleteAllCommand();
    }

}
