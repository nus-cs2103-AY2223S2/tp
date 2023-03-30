package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import teambuilder.logic.commands.DeleteCommand;
import teambuilder.logic.commands.RemoveCommand;
import teambuilder.logic.parser.exceptions.ParseException;
import teambuilder.model.team.TeamName;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        try {
            TeamName name = ParserUtil.parseTeamName(args);
            return new RemoveCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }
    }

}
