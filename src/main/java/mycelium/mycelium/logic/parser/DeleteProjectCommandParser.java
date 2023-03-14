package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;

import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;


/**
 * Parses a {@link DeleteProjectCommand} from string input.
 */
public class DeleteProjectCommandParser implements Parser<DeleteProjectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE));
        }

        String targetProjectName = ParserUtil.parseNonEmptyString(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        return new DeleteProjectCommand(targetProjectName);
    }
}
