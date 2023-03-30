package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_DELIVERED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.jobs.FindDeliveryJobCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryJobContainsKeywordsPredicate;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindDeliveryJobCommandParser implements Parser<FindDeliveryJobCommand> {

    private static final String MESSAGE_DATE_FORMAT = "Invalid date format!";
    private static final String MESSAGE_SLOT_FORMAT = "Invalid slot format!";
    private static final String MESSAGE_EARN_FORMAT = "Invalid earning format!";

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDeliveryJobCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_ID, PREFIX_SENDER_ID,
                PREFIX_RECIPIENT_ID,
                PREFIX_DELIVERY_DATE, PREFIX_DELIVERY_SLOT, PREFIX_EARNING, PREFIX_IS_DELIVERED);

        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_ID, PREFIX_SENDER_ID, PREFIX_RECIPIENT_ID,
                PREFIX_DELIVERY_DATE, PREFIX_DELIVERY_SLOT, PREFIX_EARNING, PREFIX_IS_DELIVERED)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryJobCommand.MESSAGE_USAGE));
        }

        DeliveryJob.Builder toFind = new DeliveryJob.Builder();

        argMultimap.getValue(PREFIX_JOB_ID).ifPresent(val -> toFind.setJobId(val));
        argMultimap.getValue(PREFIX_SENDER_ID).ifPresent(val -> toFind.setSender(val));
        argMultimap.getValue(PREFIX_RECIPIENT_ID).ifPresent(val -> toFind.setRecipient(val));

        if (argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()) {
            try {
                new DeliveryDate(argMultimap.getValue(PREFIX_DELIVERY_DATE).get());
                toFind.setDeliveryDate(argMultimap.getValue(PREFIX_DELIVERY_DATE).get());
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_DATE_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_DELIVERY_SLOT).isPresent()) {
            try {
                new DeliverySlot(argMultimap.getValue(PREFIX_DELIVERY_SLOT).get());
                toFind.setDeliverySlot(argMultimap.getValue(PREFIX_DELIVERY_SLOT).get());
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_SLOT_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_EARNING).isPresent()) {
            try {
                new Earning(argMultimap.getValue(PREFIX_EARNING).get());
                toFind.setEarning(argMultimap.getValue(PREFIX_EARNING).get());
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_EARN_FORMAT, e.getMessage()));
            }
        }

        argMultimap.getValue(PREFIX_IS_DELIVERED).ifPresent(val ->
                toFind.setDeliveredStatus(Boolean.parseBoolean(val)));

        return new FindDeliveryJobCommand(new DeliveryJobContainsKeywordsPredicate(toFind.buildNullable()));
    }

}
