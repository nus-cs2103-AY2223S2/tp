package seedu.loyaltylift.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.stream.Stream;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.AppendCustomerNoteCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AppendCustomerNoteCommand object
 */
public class AppendCustomerNoteCommandParser implements Parser<AppendCustomerNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AppendCustomerNoteCommand
     * and returns a AppendCustomerNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppendCustomerNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppendCustomerNoteCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        String noteToAppend = argMultimap.getValue(PREFIX_NOTE).get();

        return new AppendCustomerNoteCommand(index, noteToAppend);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
