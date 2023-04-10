package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;

/**
 * Parses input arguments and creates a new AddDeliveryJobCommand object
 */
public class AddDeliveryJobCommandParser implements Parser<AddDeliveryJobCommand> {
    private static final String MESSAGE_SLOT_MISSING = "Delivery slot missing, "
            + "both date and slot are required for scheduling";
    private static final String MESSAGE_DATE_MISSING = "Delivery date missing, "
            + "both date and slot are required for scheduling";
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeliveryJobCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SENDER_ID, PREFIX_RECIPIENT_ID,
                PREFIX_DELIVERY_DATE, PREFIX_DELIVERY_SLOT, PREFIX_EARNING);

        if (!arePrefixesPresent(argMultimap, PREFIX_SENDER_ID, PREFIX_RECIPIENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeliveryJobCommand.MESSAGE_USAGE));
        }

        String sid = argMultimap.getValue(PREFIX_SENDER_ID).get();
        String rid = argMultimap.getValue(PREFIX_RECIPIENT_ID).get();
        Optional<DeliveryDate> date = Optional.empty();
        Optional<DeliverySlot> slot = Optional.empty();
        Optional<Earning> earn;

        if (argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()
                && !argMultimap.getValue(PREFIX_DELIVERY_SLOT).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_SLOT_MISSING));
        } else if (!argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()
                && argMultimap.getValue(PREFIX_DELIVERY_SLOT).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_DATE_MISSING));
        } else if (argMultimap.getValue(PREFIX_DELIVERY_DATE).isPresent()
                && argMultimap.getValue(PREFIX_DELIVERY_SLOT).isPresent()) {
            try {
                date = argMultimap.getValue(PREFIX_DELIVERY_DATE).map(x -> new DeliveryDate(x));
            } catch (Exception e) {
                throw new ParseException(DeliveryDate.MESSAGE_CONSTRAINTS);
            }

            try {
                slot = argMultimap.getValue(PREFIX_DELIVERY_SLOT).map(x -> new DeliverySlot(x));
            } catch (Exception e) {
                throw new ParseException(DeliverySlot.MESSAGE_CONSTRAINTS);
            }
        }

        if (argMultimap.getValue(PREFIX_EARNING).isPresent()) {
            try {
                earn = argMultimap.getValue(PREFIX_EARNING).map(x -> new Earning(x));
            } catch (Exception e) {
                throw new ParseException(Earning.MESSAGE_CONSTRAINTS);
            }
        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeliveryJobCommand.MESSAGE_USAGE));
        }

        if (sid.equals("")) {
            throw new ParseException(AddDeliveryJobCommand.MESSAGE_SENDER_CONSTRAINT);
        }

        if (rid.equals("")) {
            throw new ParseException(AddDeliveryJobCommand.MESSAGE_RECIPIENT_CONSTRAINT);
        }

        return new AddDeliveryJobCommand(new DeliveryJob(rid, sid, date, slot, earn, ""));
    }
}
