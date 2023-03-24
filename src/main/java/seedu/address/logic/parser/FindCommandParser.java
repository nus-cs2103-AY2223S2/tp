package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AFTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_BEFORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INTERVAL;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindDateCommand;
import seedu.address.logic.commands.FindStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AfterDatePredicate;
import seedu.address.model.person.BeforeDatePredicate;
import seedu.address.model.person.BetweenDatePredicate;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StatusPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Checks if argMultimap contains valid FindCommand options.
     *
     * @param argMultimap Maps containing mapping of prefixes to their respective arguments
     * @return true if and only if the map does not contain any other prefix
     */
    private boolean isFindKeywordsCommand(ArgumentMultimap argMultimap) {
        return !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_BEFORE).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_AFTER).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_FROM).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_TO).isPresent();
    }

    /**
     * Checks if argMultimap contains valid FindCommand options
     *
     * @param argMultimap Maps containing mapping of prefixes to their respective arguments
     * @return true if and only if the map contains only prefix {@code PREFIX_STATUS} to its argument
     */
    private boolean isFindStatusCommand(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_BEFORE).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_AFTER).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_FROM).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_TO).isPresent();
    }

    /**
     * Checks if argMultimap contains valid FindCommand options
     *
     * @param argMultimap Maps containing mapping of prefixes to their respective arguments
     * @return true if and only if the map contains only prefix {@code PREFIX_DATE_BEFORE} to its argument
     */
    private boolean isFindBeforeDateCommand(ArgumentMultimap argMultimap) {
        return !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && argMultimap.getValue(PREFIX_DATE_BEFORE).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_AFTER).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_FROM).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_TO).isPresent();
    }

    /**
     * Checks if argMultimap contains valid FindCommand options
     *
     * @param argMultimap Maps containing mapping of prefixes to their respective arguments
     * @return true if and only if the map contains only prefix {@code PREFIX_DATE_AFTER} to its argument
     */
    private boolean isFindAfterDateCommand(ArgumentMultimap argMultimap) {
        return !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_BEFORE).isPresent()
                && argMultimap.getValue(PREFIX_DATE_AFTER).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_FROM).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_TO).isPresent();
    }

    /**
     * Checks if argMultimap contains valid FindCommand options
     *
     * @param argMultimap Maps containing mapping of prefixes to their respective arguments
     * @return true if and only if the map contains only prefixes {@code PREFIX_DATE_FROM}
     *     and {@code PREFIX_DATE_TO} to its argument
     */
    private boolean isFindBetweenDateCommand(ArgumentMultimap argMultimap) {
        return !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_BEFORE).isPresent()
                && !argMultimap.getValue(PREFIX_DATE_AFTER).isPresent()
                && argMultimap.getValue(PREFIX_DATE_FROM).isPresent()
                && argMultimap.getValue(PREFIX_DATE_TO).isPresent();
    }

    /**
     * Checks if argMultimap contains valid FindCommand options.
     *
     * @param argMultimap Maps containing mapping of prefixes to their respective arguments
     * @return true if and only if the map contains valid combination of options
     * @throws ParseException if the user input does not conform the expected format
     */
    private boolean verifyFindCommandOptions(ArgumentMultimap argMultimap) throws ParseException {
        if (!(isFindKeywordsCommand(argMultimap)
                || isFindStatusCommand(argMultimap)
                || isFindBeforeDateCommand(argMultimap)
                || isFindAfterDateCommand(argMultimap)
                || isFindBetweenDateCommand(argMultimap))) {
            Logger logger = LogsCenter.getLogger(FindCommandParser.class);
            logger.log(Level.INFO, "User used an invalid combination of prefixes");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return true;
    }

    /**
     * Checks if the given two dates forms valid interval.
     *
     * @param startDate The start date of the interval
     * @param endDate The end date of the interval
     * @return true if the two dates form a valid interval
     * @throws ParseException if two dates doesn't form a valid interval
     */
    private boolean checkValidInterval(InterviewDate startDate, InterviewDate endDate) throws ParseException {
        if (!(startDate.isBeforeInclusive(endDate))) {
            throw new ParseException(MESSAGE_INVALID_INTERVAL);
        }
        return true;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_STATUS, PREFIX_DATE_BEFORE, PREFIX_DATE_AFTER, PREFIX_DATE_FROM, PREFIX_DATE_TO);
        verifyFindCommandOptions(argMultimap);
        if (isFindStatusCommand(argMultimap)) {
            InternshipStatus status = ParserUtil.parseInternshipStatus(argMultimap.getValue(PREFIX_STATUS).get());
            return new FindStatusCommand(new StatusPredicate(status));
        }
        if (isFindBeforeDateCommand(argMultimap)) {
            InterviewDate date = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_DATE_BEFORE).get());
            return new FindDateCommand(new BeforeDatePredicate(date));
        }
        if (isFindAfterDateCommand(argMultimap)) {
            InterviewDate date = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_DATE_AFTER).get());
            return new FindDateCommand(new AfterDatePredicate(date));
        }
        if (isFindBetweenDateCommand(argMultimap)) {
            InterviewDate startDate = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_DATE_FROM).get());
            InterviewDate endDate = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_DATE_TO).get());
            checkValidInterval(startDate, endDate);
            return new FindDateCommand(new BetweenDatePredicate(startDate, endDate));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
