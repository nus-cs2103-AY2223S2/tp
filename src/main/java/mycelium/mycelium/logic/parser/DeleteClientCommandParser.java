package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import mycelium.mycelium.commons.core.index.Index;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Email;

public class DeleteClientCommandParser implements Parser<DeleteClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClientCommand
     * and returns a DeleteClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteClientCommand parse(String args) throws ParseException {
        try {
            Email targetEmail = ParserUtil.parseEmail(args);
            return new DeleteClientCommand(targetEmail);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE), pe);
        }
    }
}
