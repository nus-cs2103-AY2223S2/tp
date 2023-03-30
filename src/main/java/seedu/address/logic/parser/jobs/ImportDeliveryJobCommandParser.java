package seedu.address.logic.parser.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import seedu.address.logic.commands.jobs.AddDeliveryJobCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Parses CSV file contents into separate jobs and job details
 */
public class ImportDeliveryJobCommandParser {

    public static final String MESSAGE_MISSING_ELEMENT_IN_IMPORT = "Missing element in import";

    /**
     * Parses the given CSV File in the context of the ImportCommand
     *
     * @param file CSV file containing delivery jobs
     * @return List of delivery jobs to be added
     * @throws ParseException if the user input does not conform the expected format
     */
    public static List<DeliveryJob> parse(File file, Person recipient, Person sender)
            throws ParseException, FileNotFoundException {

        try (Scanner sc = new Scanner(file)) {
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

                if (arrOfStr.length > 5) {
                    recipient = recipientOrSender(sid, arrOfStr, 5);
                    System.out.println(recipient);
                }
                if (arrOfStr.length > 11) {
                    sender = recipientOrSender(sid, arrOfStr, 11);
                    System.out.println(sender);
                }

                DeliveryJob job = new DeliveryJob(rid, sid, ded, des, ear, "");
                listOfAddDeliveryJob.add(job);
                System.out.println(listOfAddDeliveryJob);

                DeliveryJob job = new DeliveryJob(rid, sid, ded, des, ear, "");

                listOfAddDeliveryJob.add(job);
            }
            sc.close();
            return listOfAddDeliveryJob;
        }
    }

    public static Person recipientOrSender(String recipientString, String[] arrOfStr, int index) throws ParseException {
        String personID = arrOfStr[index];
        Name name = new Name(arrOfStr[index + 1]);
        Phone phone = new Phone(arrOfStr[index + 2]);
        Email email = new Email(arrOfStr[index + 3]);
        Address address = new Address(arrOfStr[index + 4]);
        String[] arrOfTags = arrOfStr[index + 5].split(" ");
        Set<Tag> tags = ParserUtil.parseTags(Arrays.asList(arrOfTags));
        Person person = new Person(personID, name, phone, email, address, tags);
        return person;
    }

}

