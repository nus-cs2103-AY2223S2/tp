package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EARNING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SENDER_ID;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.commands.jobs.ImportDeliveryJobCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;

public class ImportDeliveryJobCommandParser {

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
    public static List<DeliveryJob> parse(File file) throws ParseException, FileNotFoundException {

        Scanner sc = new Scanner(file);
        List<DeliveryJob> listOfAddDeliveryJob = new ArrayList<>();

        for (int i = 0; i < file.length(); i++) {
            String line = sc.nextLine();

            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(line, PREFIX_SENDER_ID, PREFIX_RECIPIENT_ID,
                    PREFIX_DELIVERY_DATE, PREFIX_DELIVERY_SLOT, PREFIX_EARNING);

            if (!arePrefixesPresent(argMultimap, PREFIX_SENDER_ID, PREFIX_RECIPIENT_ID, PREFIX_DELIVERY_DATE,
                    PREFIX_DELIVERY_SLOT, PREFIX_EARNING) || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeliveryJobCommand.MESSAGE_USAGE));
            }

            String sid = argMultimap.getValue(PREFIX_SENDER_ID).get();
            String rid = argMultimap.getValue(PREFIX_RECIPIENT_ID).get();
            String ded = argMultimap.getValue(PREFIX_DELIVERY_DATE).get();
            String des = argMultimap.getValue(PREFIX_DELIVERY_SLOT).get();
            String ear = argMultimap.getValue(PREFIX_EARNING).get();

            DeliveryJob job = new DeliveryJob(rid, sid, ded, des, ear, "");

            listOfAddDeliveryJob.add(job);
        }

        return listOfAddDeliveryJob;
    }
}
