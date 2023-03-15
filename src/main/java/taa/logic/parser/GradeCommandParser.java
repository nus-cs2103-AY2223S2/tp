package taa.logic.parser;

import taa.commons.core.Messages;
import taa.logic.commands.GradeCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Name;

import java.util.stream.Stream;

import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static taa.logic.parser.CliSyntax.PREFIX_SUBMISSION_MARK;

/**
 * Parser for grading command.
 */
public class GradeCommandParser {
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
        int student_id = Integer.parseInt(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        int mark = Integer.parseInt(argMultimap.getValue(PREFIX_SUBMISSION_MARK).get());
        return new GradeCommand(name.toString(), student_id, mark);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
