package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import teambuilder.logic.commands.ShowCommand;
import teambuilder.logic.parser.exceptions.ParseException;
import teambuilder.model.person.TeamContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        String[] teamKeywords = trimmedArgs.split("\\s+");

        return new ShowCommand(new TeamContainsKeywordsPredicate(Arrays.asList(teamKeywords)));
    }
}
