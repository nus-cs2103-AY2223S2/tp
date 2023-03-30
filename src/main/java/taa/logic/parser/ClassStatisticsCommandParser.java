package taa.logic.parser;

import static taa.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_STAT_TYPE;

import java.util.stream.Stream;

import taa.commons.core.Messages;
import taa.logic.commands.ClassStatisticsCommand;
import taa.logic.commands.enums.ChartType;
import taa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClassStatisticsCommand object
 */
public class ClassStatisticsCommandParser implements Parser<ClassStatisticsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClassStatisticsCommand
     * and returns an ClassStatisticsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ClassStatisticsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAT_TYPE, PREFIX_ASSIGNMENT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_STAT_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassStatisticsCommand.MESSAGE_USAGE));
        }

        String rawField = "CLASS_" + argMultimap.getValue(PREFIX_STAT_TYPE).get().toUpperCase();
        if (!rawField.equals(ChartType.CLASS_GRADES.toString())
                && !rawField.equals(ChartType.CLASS_ATTENDANCE.toString())) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassStatisticsCommand.MESSAGE_UNKNOWN_FIELD));
        }

        ChartType field = ChartType.valueOf(rawField);

        if (field == ChartType.CLASS_GRADES
                && !arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT_NAME)) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ClassStatisticsCommand.MESSAGE_MISSING_ASSIGNMENT_NAME));
        }

        if (field == ChartType.CLASS_ATTENDANCE) {
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
