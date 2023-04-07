package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSONS_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String indexString = args.replace("delete", "");
        if (indexString == null || indexString.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        try {
            List<Index> indexes = ParserUtil.parseIndexes(args);
            return new DeleteCommand(indexes);
        } catch (ParseException pe) {
            String err = pe.getMessage();
            throw new ParseException(err, pe);
        } catch (IndexOutOfBoundsException iobe) {
            throw new ParseException(
                    MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, iobe
            );
        }
    }
}
 //+ '\n' + DeleteCommand.MESSAGE_USAGE