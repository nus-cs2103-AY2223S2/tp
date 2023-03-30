package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_DELIVERED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.jobs.FindDeliveryJobCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;
import seedu.address.model.jobs.predicate.DeliveryJobContainsDeliveryDatePredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsDeliverySlotPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsEarningPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsJobIdPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsRecipientIdPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsSenderIdPredicate;
import seedu.address.model.jobs.predicate.DeliveryJobContainsStatusPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindDeliveryJobCommandParser implements Parser<FindDeliveryJobCommand> {

    private static final String MESSAGE_DATE_FORMAT = "Invalid date format!\nDate/ <blank> | YYYY-MM-DD";
    private static final String MESSAGE_SLOT_FORMAT = "Invalid slot format!\nSlot/ <blank> | 1..5";
    private static final String MESSAGE_EARN_FORMAT = "Invalid earning format!\nearn/ d.f ";
    private static final String MESSAGE_EMPTY_SENDER_ID = "Sender id missing!\nsi/ id ";
    private static final String MESSAGE_EMPTY_JOB_ID = "Job id missing!\nji/ id ";
    private static final String MESSAGE_EMPTY_RECIPIENT_ID = "Recipient id missing!\nri/ id ";
    private static final String MESSAGE_EMPTY_STATUS = "Invalid status!\nDone/ t | f ";

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

        Predicate<DeliveryJob> predicate = null;
        String query = "";

        if (argMultimap.getValue(PREFIX_JOB_ID).isPresent()) {
            String val = argMultimap.getValue(PREFIX_JOB_ID).get();
            if (val.isBlank()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_JOB_ID));
            }
            query += "job id: " + val + "\n";
            predicate = new DeliveryJobContainsJobIdPredicate(val.toUpperCase());
        }

        if (argMultimap.getValue(PREFIX_SENDER_ID).isPresent()) {
            String val = argMultimap.getValue(PREFIX_SENDER_ID).get();
            if (val.isBlank()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_SENDER_ID));
            }

            if (predicate != null) {
                predicate = predicate.and(new DeliveryJobContainsSenderIdPredicate(val.toUpperCase()));
            } else {
                predicate = new DeliveryJobContainsSenderIdPredicate(val.toUpperCase());
            }
            query += "sender id: " + val + "\n";
        }

        if (argMultimap.getValue(PREFIX_RECIPIENT_ID).isPresent()) {
            String val = argMultimap.getValue(PREFIX_RECIPIENT_ID).get();
            if (val.isBlank()) {
                throw new ParseException(String.format(MESSAGE_EMPTY_RECIPIENT_ID));
            }

            if (predicate != null) {
                predicate = predicate.and(new DeliveryJobContainsRecipientIdPredicate(val.toUpperCase()));
            } else {
                predicate = new DeliveryJobContainsRecipientIdPredicate(val.toUpperCase());
            }
            query += "recipient id: " + val + "\n";
        }

        if (argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()) {
            try {
                String val = argMultimap.getValue(PREFIX_DELIVERY_DATE).get();
                DeliveryDate toFindDate;
                if (val.isBlank()) {
                    toFindDate = DeliveryDate.placeholder();
                } else {
                    toFindDate = new DeliveryDate(val);
                }
                if (predicate != null) {
                    predicate = predicate.and(new DeliveryJobContainsDeliveryDatePredicate(toFindDate));
                } else {
                    predicate = new DeliveryJobContainsDeliveryDatePredicate(toFindDate);
                }
                query += "delivery date: " + toFindDate.toString() + "\n";
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_DATE_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_DELIVERY_SLOT).isPresent()) {
            try {
                String val = argMultimap.getValue(PREFIX_DELIVERY_SLOT).get();
                DeliverySlot toFindSlot;
                if (val.isBlank()) {
                    toFindSlot = DeliverySlot.placeholder();
                } else {
                    toFindSlot = new DeliverySlot(val);
                }
                if (predicate != null) {
                    predicate = predicate.and(new DeliveryJobContainsDeliverySlotPredicate(toFindSlot));
                } else {
                    predicate = new DeliveryJobContainsDeliverySlotPredicate(toFindSlot);
                }
                query += "delivery slot: " + toFindSlot.toString() + "\n";
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_SLOT_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_EARNING).isPresent()) {
            try {
                String val = argMultimap.getValue(PREFIX_EARNING).get();
                if (predicate != null) {
                    predicate = predicate.and(new DeliveryJobContainsEarningPredicate(new Earning(val)));
                } else {
                    predicate = new DeliveryJobContainsEarningPredicate(new Earning(val));
                }
                query += "earning: " + val + "\n";
            } catch (IllegalArgumentException e) {
                throw new ParseException(
                    String.format(MESSAGE_EARN_FORMAT, e.getMessage()));
            }
        }

        if (argMultimap.getValue(PREFIX_IS_DELIVERED).isPresent()) {
            String val = argMultimap.getValue(PREFIX_IS_DELIVERED).get().toLowerCase();
            boolean done = false;
            if (val.isBlank() || (!val.equals("t") && !val.equals("f"))) {
                throw new ParseException(String.format(MESSAGE_EMPTY_STATUS));
            }
            if (val.startsWith("t")) {
                done = true;
            }
            if (predicate != null) {
                predicate = predicate.and(new DeliveryJobContainsStatusPredicate(done));
            } else {
                predicate = new DeliveryJobContainsStatusPredicate(done);
            }
            query += "status: " + Boolean.toString(done) + "\n";
        }

        if (predicate != null) {
            return new FindDeliveryJobCommand(predicate, query);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeliveryJobCommand.MESSAGE_USAGE));
        }
    }

}
