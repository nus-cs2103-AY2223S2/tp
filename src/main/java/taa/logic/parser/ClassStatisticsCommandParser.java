package taa.logic.parser;

import taa.commons.core.Messages;
import taa.logic.commands.ClassStatisticsCommand;
import taa.logic.commands.enums.ClassStatisticField;
import taa.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static taa.logic.parser.CliSyntax.PREFIX_STAT_TYPE;
import static taa.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;

/**
 * Parses input arguments and creates a new ClassStatisticsCommand object
 */
public class ClassStatisticsCommandParser implements Parser<ClassStatisticsCommand> {

    @Override
    /**
     * Parses the given {@code String} of arguments in the context of the ClassStatisticsCommand
     * and returns an ClassStatisticsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClassStatisticsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAT_TYPE, PREFIX_ASSIGNMENT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_STAT_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassStatisticsCommand.MESSAGE_USAGE));
        }

        String rawField = argMultimap.getValue(PREFIX_STAT_TYPE).get().toUpperCase();
        if (!rawField.equals(ClassStatisticField.GRADES.toString())
                && !rawField.equals(ClassStatisticField.ATTENDANCE.toString())) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassStatisticsCommand.MESSAGE_UNKNOWN_FIELD));
        }

        ClassStatisticField field = ClassStatisticField.valueOf(rawField);

        if (field == ClassStatisticField.GRADES
                && !arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT_NAME)) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassStatisticsCommand.MESSAGE_MISSING_ASSIGNMENT_NAME));
        }

        if (field == ClassStatisticField.ATTENDANCE) {
            return new ClassStatisticsCommand(field);
        } else {
            String assignmentName = argMultimap.getValue(PREFIX_ASSIGNMENT_NAME).get();
            return new ClassStatisticsCommand(field, assignmentName);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
