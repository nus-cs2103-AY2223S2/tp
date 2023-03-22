package seedu.address.logic.parser;


import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Classification;

import java.util.regex.Pattern;

/**
 * Parses the given {@code String} of arguments in the context of the ListCommand
 * and returns a ListCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public class ListCommandParser  implements Parser<ListCommand>{
    @Override
    public ListCommand parse(String args) throws ParseException {
        boolean isValidCommand = Pattern.matches("^(char|item|mob)", args.trim());
        if (!isValidCommand) {
            throw new ParseException("Invalid command!");
        }
        Classification classification = ParserUtil.parseClassification(args);
        return new ListCommand(classification);
    }
}
