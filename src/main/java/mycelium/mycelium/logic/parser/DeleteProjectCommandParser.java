package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import mycelium.mycelium.commons.core.index.Index;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;



public class DeleteProjectCommandParser implements Parser<DeleteProjectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProjectCommand
     * and returns a DeleteProjectCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProjectCommand parse(String args) throws ParseException {
        try {
            String targetProjectName = ParserUtil.parseSource(args);
            return new DeleteProjectCommand(targetProjectName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE), pe);
        }
    }

}
