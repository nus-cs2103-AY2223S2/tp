package taa.logic.parser;

import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;
import static taa.logic.parser.CliSyntax.PREFIX_MARK;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import taa.commons.core.Messages;
import taa.logic.commands.AddAssignmentCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Name;


/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddAssignmentCommandParser implements Parser<AddAssignmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CLASS_TAG, PREFIX_MARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssignmentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        int totalMarks = 100;
        if (arePrefixesPresent(argMultimap, PREFIX_MARK)) {
            totalMarks = ParserUtil.parseInt(argMultimap.getValue(PREFIX_MARK).get());
            if (totalMarks < 0) {
                throw new ParseException("Marks cannot be negative.");
            }
        }

        return new AddAssignmentCommand(name.toString(), totalMarks);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
