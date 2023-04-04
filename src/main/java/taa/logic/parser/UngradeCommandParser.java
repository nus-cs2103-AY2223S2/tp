package taa.logic.parser;

import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.stream.Stream;

import taa.commons.core.Messages;
import taa.logic.commands.UngradeCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Name;

/**
 * Parser for UngradeCommand.
 */
public class UngradeCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns a GradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UngradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    UngradeCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        int studentId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_ID).get()).getOneBased();
        return new UngradeCommand(name.toString(), studentId);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
