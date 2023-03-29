package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

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
        String date;
        String slot;
        String earn;
        try {
            date = argMultimap.getValue(PREFIX_DELIVERY_DATE).get();
        } catch (NoSuchElementException e) {
            date = "";
        }

        try {
            slot = argMultimap.getValue(PREFIX_DELIVERY_SLOT).get();
        } catch (NoSuchElementException e) {
            slot = "";
        }

        try {
            earn = argMultimap.getValue(PREFIX_EARNING).get();
        } catch (NoSuchElementException e) {
            earn = "";
        }

        if (sid.equals("")) {
            throw new ParseException(AddDeliveryJobCommand.MESSAGE_SENDER_CONSTRAINT);
        }

        if (rid.equals("")) {
            throw new ParseException(AddDeliveryJobCommand.MESSAGE_RECIPIENT_CONSTRAINT);
        }

        /*if (ded.equals("")) {
            throw new ParseException(DeliveryDate.MESSAGE_CONSTRAINTS);
        }

        if (des.equals("")) {
            throw new ParseException(DeliverySlot.MESSAGE_CONSTRAINTS);
        } */
        if ((!slot.equals("")) && (Integer.parseInt(slot) < 1)) {
            throw new ParseException(DeliverySlot.MESSAGE_CONSTRAINTS);
        }

        /*if ((ear != null) && (ear.equals(""))) {
            throw new ParseException(Earning.MESSAGE_CONSTRAINTS);
        }*/

        DeliveryJob job = createDeliveryJob(rid, sid, date, slot, earn);
        return new AddDeliveryJobCommand(job);
    }

    private DeliveryJob createDeliveryJob(String rid, String sid, String date, String slot, String earn) {
        Optional<DeliveryDate> jobDate;
        Optional<DeliverySlot> jobSlot;
        Optional<Earning> jobEarning;

        if (date.isEmpty()) {
            jobDate = Optional.empty();
        } else {
            jobDate = Optional.of(new DeliveryDate(date));
        }

        if (slot.isEmpty()) {
            jobSlot = Optional.empty();
        } else {
            jobSlot = Optional.of(new DeliverySlot(slot));
        }

        if (earn.isEmpty()) {
            jobEarning = Optional.empty();
        } else {
            jobEarning = Optional.of(new Earning(earn));
        }

        return new DeliveryJob(rid, sid, jobDate, jobSlot, jobEarning, "");
    }

}
