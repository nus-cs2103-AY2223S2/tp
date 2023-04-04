package taa.logic.parser;

import static java.util.Objects.requireNonNull;
import static taa.logic.parser.CliSyntax.PREFIX_CLASS_TAG;
import static taa.logic.parser.CliSyntax.PREFIX_NAME;
import static taa.logic.parser.CliSyntax.PREFIX_PARTICIPATION_POINTS;
import static taa.logic.parser.CliSyntax.PREFIX_WEEK;

import taa.commons.core.index.Index;
import taa.logic.commands.InsertParticipationCommand;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Attendance;

/**
 * Parses input arguments and creates a new InsertParticipationCommand object
 */
public class InsertParticipationParser implements Parser<InsertParticipationCommand> {
    @Override
    public InsertParticipationCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_CLASS_TAG, PREFIX_WEEK,
                        PREFIX_PARTICIPATION_POINTS);

        Index index;
        int week = -1;
        int points = -1;

        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
        }

        if (argMultimap.getValue(PREFIX_PARTICIPATION_POINTS).isPresent()) {
            points = ParserUtil.parsePartPoints(argMultimap.getValue(PREFIX_PARTICIPATION_POINTS).get());
        }

        if (week == -1) {
            throw new ParseException(Attendance.WEEK_ERROR_MSG);
        }

        if (points == -1) {
            throw new ParseException(Attendance.POINTS_ERROR_MSG);
        }

        return new InsertParticipationCommand(index, Index.fromOneBased(week), points);
    }
}
