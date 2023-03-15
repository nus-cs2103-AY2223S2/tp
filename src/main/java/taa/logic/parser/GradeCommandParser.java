package taa.logic.parser;

import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static taa.logic.parser.CliSyntax.PREFIX_SUBMISSION_MARK;

import java.util.stream.Stream;

import taa.commons.core.Messages;
import taa.logic.commands.GradeCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Name;

/**
 * Parser for grading command.
 */
public class GradeCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns a GradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_SUBMISSION_MARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_SUBMISSION_MARK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        int studentId = Integer.parseInt(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        int mark = Integer.parseInt(argMultimap.getValue(PREFIX_SUBMISSION_MARK).get());
        return new GradeCommand(name.toString(), studentId, mark);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
