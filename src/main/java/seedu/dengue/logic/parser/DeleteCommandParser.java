package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_STARTDATE;

import seedu.dengue.commons.core.index.Index;
import seedu.dengue.logic.commands.DeleteCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartDate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try { // all indexes
            // TODO: parseMultiIndex(args)
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {

            try {
                ArgumentMultimap argMultimap =
                        ArgumentTokenizer.tokenize(args, PREFIX_DATE,
                                PREFIX_STARTDATE, PREFIX_ENDDATE);
                if (hasMixedDates(argMultimap)) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteCommand.MESSAGE_USAGE));
                } else {
                    if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
                        String date = argMultimap.getValue(PREFIX_DATE).get();
                        return new DeleteCommand(new Date(date));
                    } else {
                        String startDate = argMultimap.getValue(PREFIX_STARTDATE).get();
                        String endDate = argMultimap.getValue(PREFIX_ENDDATE).get();
                        Range<Date> range = new Range(new StartDate(startDate), new EndDate(endDate));
                        return new DeleteCommand(range);
                    }
                }
            } catch (ParseException pe2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe2);
            }
        }
    }

    private static boolean hasMixedDates(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_DATE).isPresent() &
                (argumentMultimap.getValue(PREFIX_STARTDATE).isPresent() |
                        argumentMultimap.getValue(PREFIX_ENDDATE).isPresent());
    }

}
