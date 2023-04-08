package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.DeleteByNameCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMultipleIndexCommand;
import seedu.address.logic.commands.DeleteSingleIndexCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsAllKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteCommand type object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            throw new ParseException(
                  String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        if (StringUtil.isNonZeroUnsignedInteger(args.trim())) {
            // Delete Single
            Index index = ParserUtil.parseIndex(args);
            return new DeleteSingleIndexCommand(index);
        } else if (args.trim().matches("^[0-9,]+$")) {

            // Delete Multiple
            List<Index> listOfIndexes = ParserUtil.parseIndexes(args);
            return new DeleteMultipleIndexCommand(listOfIndexes);
        } else {
            // delete by name
            String trimmedArgs = args.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new DeleteByNameCommand(new NameContainsAllKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

}
