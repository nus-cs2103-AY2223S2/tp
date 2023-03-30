package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Classification;



/**
 * Parses the given {@code String} of arguments in the context of the ListCommand
 * and returns a ListCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public class ListCommandParser implements Parser<ListCommand> {
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            return new ListCommand();
        }

        boolean isValidCommand = Pattern.matches("^(char|item|mob|c|i|m)", args.trim());
        if (!isValidCommand) {
            throw new ParseException("Invalid command!");
        }

        Classification classification = ParserUtil.parseClassification(args);
        return new ListCommand(classification);
    }
}
