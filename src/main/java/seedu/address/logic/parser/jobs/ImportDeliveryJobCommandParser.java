package seedu.address.logic.parser.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;

public class ImportDeliveryJobCommandParser {

    public static final String MESSAGE_MISSING_ELEMENT_IN_IMPORT = "Missing element in import";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public static List<DeliveryJob> parse(File file) throws ParseException, FileNotFoundException {

        Scanner sc = new Scanner(file);
        List<DeliveryJob> listOfAddDeliveryJob = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] arrOfStr = line.split(",");

            if (arrOfStr.length != 5) {
                throw new ParseException(
                        String.format(MESSAGE_MISSING_ELEMENT_IN_IMPORT, AddDeliveryJobCommand.MESSAGE_USAGE));
            }

            String sid = arrOfStr[0];
            String rid = arrOfStr[1];
            String ded = arrOfStr[2];
            String des = arrOfStr[3];
            String ear = arrOfStr[4];

            DeliveryJob job = new DeliveryJob(rid, sid, ded, des, ear, "");

            listOfAddDeliveryJob.add(job);
        }

        return listOfAddDeliveryJob;
    }
}

