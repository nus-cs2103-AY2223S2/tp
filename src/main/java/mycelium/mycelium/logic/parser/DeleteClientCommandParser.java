package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;

import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Email;

/**
 * A command to delete an existing client.
 */
public class DeleteClientCommandParser implements Parser<DeleteClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClientCommand
     * and returns a DeleteClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_EMAIL);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CLIENT_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE));
        }

        Email targetEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());

        return new DeleteClientCommand(targetEmail);
    }
}
