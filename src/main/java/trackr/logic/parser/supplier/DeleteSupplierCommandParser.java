package trackr.logic.parser.supplier;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import trackr.commons.core.index.Index;
import trackr.logic.commands.supplier.DeleteSupplierCommand;
import trackr.logic.parser.Parser;
import trackr.logic.parser.ParserUtil;
import trackr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteSupplierCommand object.
 */
public class DeleteSupplierCommandParser implements Parser<DeleteSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSupplierCommand
     * and returns a DeleteSupplierCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteSupplierCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteSupplierCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSupplierCommand.MESSAGE_USAGE), pe);
        }
    }

}
