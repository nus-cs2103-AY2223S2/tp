package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import arb.logic.commands.project.FindProjectCommand;
import arb.logic.parser.Parser;
import arb.logic.parser.exceptions.ParseException;
import arb.model.project.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindProjectCommand object
 */
public class FindProjectCommandParser implements Parser<FindProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindProjectCommand
     * and returns a FindProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProjectCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
        }

        String[] titleKeywords = trimmedArgs.split("\\s+");

        return new FindProjectCommand(new TitleContainsKeywordsPredicate(Arrays.asList(titleKeywords)));
    }

}
