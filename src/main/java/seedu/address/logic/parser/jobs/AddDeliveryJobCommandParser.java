package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECEPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;

/**
 * Parses input arguments and creates a new AddCommand object
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SENDER_ID, PREFIX_RECEPIENT_ID,
                PREFIX_DELIVERY_DATE, PREFIX_DELIVERY_SLOT, PREFIX_EARNING);

        if (!arePrefixesPresent(argMultimap, PREFIX_SENDER_ID, PREFIX_RECEPIENT_ID, PREFIX_DELIVERY_DATE,
                PREFIX_DELIVERY_SLOT, PREFIX_EARNING)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeliveryJobCommand.MESSAGE_USAGE));
        }

        String sid = argMultimap.getValue(PREFIX_SENDER_ID).get();
        String rid = argMultimap.getValue(PREFIX_RECEPIENT_ID).get();
        String ded = argMultimap.getValue(PREFIX_DELIVERY_DATE).get();
        String des = argMultimap.getValue(PREFIX_DELIVERY_SLOT).get();
        String ear = argMultimap.getValue(PREFIX_EARNING).get();

        // TODO: check for valid person id before inserting
        DeliveryJob job = new DeliveryJob(rid, sid, ded, ear);

        return new AddDeliveryJobCommand(job);
    }

}
