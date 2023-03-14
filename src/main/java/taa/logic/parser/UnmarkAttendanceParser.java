package taa.logic.parser;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_WEEK;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.UnmarkAttendanceCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Attendance;

/**
 * Parses input arguments and creates a new UnmarkAttendanceCommand object
 */
public class UnmarkAttendanceParser implements Parser<UnmarkAttendanceCommand> {
    @Override
    public UnmarkAttendanceCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_CLASS_TAG, PREFIX_WEEK);

        Index index;
        int week = -1;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkAttendanceCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
        }

        if (week == -1) {
            throw new ParseException(Attendance.ERROR_MSG);
        }

        return new UnmarkAttendanceCommand(index, Index.fromOneBased(week));
    }
}
