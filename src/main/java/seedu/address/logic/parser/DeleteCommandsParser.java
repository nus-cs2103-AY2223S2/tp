package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommands;
import seedu.address.logic.parser.exceptions.ParseException;






/**
 * Delete people identified using it's displayed index from the address book.
 */
public class DeleteCommandsParser implements Parser<DeleteCommands> {

    @Override
    public DeleteCommands parse(String userInput) throws ParseException {
        try {
            ArrayList<Index> indices = ParserUtil.parseindexs(userInput.trim(), " ");
            return new DeleteCommands(indices);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommands.MESSAGE_USAGE), pe);
        }
    }


}
