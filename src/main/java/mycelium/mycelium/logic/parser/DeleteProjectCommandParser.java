package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.*;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;

import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;


public class DeleteProjectCommandParser implements Parser<DeleteProjectCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProjectCommand parse(String args) throws ParseException {


        /**
         * Parses the given {@code String} of arguments in the context of the DeleteProjectCommand
         * and returns a DeleteProjectCommand object for execution.
         *
         * @throws ParseException if the user input does not conform the expected format
         */
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE));
        }


        String targetProjectName = ParserUtil.parseNonEmptyString(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        return new DeleteProjectCommand(targetProjectName);


    }
}
