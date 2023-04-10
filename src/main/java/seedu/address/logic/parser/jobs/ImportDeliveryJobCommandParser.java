package seedu.address.logic.parser.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;
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
    public static final String MESSAGE_EMPTY_FILE = "File is empty";
    public static final String MESSAGE_WRONG_FILE = "File type is unsupported. Please upload a CSV file and check if "
            + "file extension is named .csv";

    public static final String MESSAGE_WRONG_DELIMITER = "Wrong delimiter used, please use comma as delimiter.";
    public static final String MESSAGE_MISSING_HEADER = "Missing header in import. "
            + "Header needs to consist in this order of Recipient ID, Sender ID, Delivery date, Delivery slot, Price, "
            + "Recipient ID, Recipient Name, Recipient Phone, Recipient Email, Recipient Address, "
            + "Recipient Tag, Sender ID, Sender Name, Sender Phone, Sender Email, Sender Address,"
            + " Sender Tag.";

    /**
     * Parses the given CSV File in the context of the ImportCommand
     *
     * @param file CSV file containing delivery jobs
     * @return List of delivery jobs to be added
     * @throws ParseException if the user input does not conform to
     *     the expected format
     */
    public static List<DeliveryJob> parse(File file, List<Person> listOfCustomers)
            throws ParseException, FileNotFoundException {
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()) {
            List<DeliveryJob> listOfAddDeliveryJob = new ArrayList<>();
            String header = sc.nextLine();
            String[] arrOfHeader = header.split(",");
            if (arrOfHeader.length == 0) {
                throw new ParseException(MESSAGE_EMPTY_FILE);
            } else if (!getFileExtensions(file).equals("csv")) {
                throw new ParseException(MESSAGE_WRONG_FILE);
            } else if (arrOfHeader.length == 1) {
                throw new ParseException(MESSAGE_WRONG_DELIMITER);
            } else if (!headerValidity(arrOfHeader)) {
                throw new ParseException(MESSAGE_MISSING_HEADER);
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arrOfStr = line.split(",");
                if (arrOfStr.length < 17) {
                    throw new ParseException(MESSAGE_MISSING_ELEMENT_IN_IMPORT);
                }
                addJobs(arrOfStr, listOfCustomers, listOfAddDeliveryJob);
            }
            sc.close();
            return listOfAddDeliveryJob;
        } else {
            throw new ParseException(MESSAGE_EMPTY_FILE);
        }
    }

    /**
     * Parses recipient or sender from jobs in CSV file.
     *
     * @param arrOfStr array of person details
     * @param index index of array
     * @return Person object using the details
     * @throws ParseException if the user input does not conform to
     *     the expected format
     */
    public static Person recipientOrSender(String[] arrOfStr, int index) throws ParseException {
        String personID = arrOfStr[index];
        Name name = new Name(arrOfStr[index + 1]);
        Phone phone = new Phone(arrOfStr[index + 2]);
        Email email = new Email(arrOfStr[index + 3]);
        Address address = new Address(arrOfStr[index + 4]);
        String[] arrOfTags = arrOfStr[index + 5].split(" ");
        Set<Tag> tags = ParserUtil.parseTags(Arrays.asList(arrOfTags));
        Person person;
        if (arrOfStr[0].equals("na")) {
            Set<Tag> tagSet = new HashSet<>();
            person = new Person(name, phone, email, address, tags);
        } else {
            person = new Person(personID, name, phone, email, address, tags);
        }
        return person;
    }

    /**
     * Ensures Header elements are all present and in right order.
     *
     * @param arrOfHeader array of header elements
     * @return Boolean whether header elements are correct
     */
    public static boolean headerValidity(String[] arrOfHeader) {
        if (!arrOfHeader[0].equals("Recipient ID") || !arrOfHeader[1].equals("Sender ID")
                || !arrOfHeader[2].equals("Delivery date") || !arrOfHeader[3].equals("Delivery slot")
                || !arrOfHeader[4].equals("Price")
                || !arrOfHeader[5].equals("Recipient ID") || !arrOfHeader[6].equals("Recipient Name")
                || !arrOfHeader[7].equals("Recipient Phone") || !arrOfHeader[8].equals("Recipient Email")
                || !arrOfHeader[9].equals("Recipient Address") || !arrOfHeader[10].equals("Recipient Tag")
                || !arrOfHeader[11].equals("Sender ID")
                || !arrOfHeader[12].equals("Sender Name") || !arrOfHeader[13].equals("Sender Phone")
                || !arrOfHeader[14].equals("Sender Email") || !arrOfHeader[15].equals("Sender Address")
                || !arrOfHeader[16].equals("Sender Tag")) {
            return false;
        }
        return true;
    }

    /**
     * Gets type of file extension.
     *
     * @param file File that extension is checked
     * @return String file extension name
     */
    public static String getFileExtensions(File file) {
        String fileName = file.getName();
        String extension = "";
        int i = fileName.lastIndexOf(".");
        extension = fileName.substring(i + 1, fileName.length());
        return extension;
    }


    /**
     * Adds new delivery job to list of jobs to be imported in.
     *
     * @param arrOfStr array of job and its customers details
     * @param listOfCustomers list of job's customers to be checked if
     *                        exist and added into address book.
     * @param listOfAddDeliveryJob list of delivery jobs to be
     *                             imported
     */
    public static void addJobs(String[] arrOfStr, List<Person> listOfCustomers,
                               List<DeliveryJob> listOfAddDeliveryJob) throws ParseException {
        String sid = arrOfStr[0];
        String rid = arrOfStr[1];
        String ded = arrOfStr[2];
        String des = arrOfStr[3];
        String ear = arrOfStr[4];
        Person recipient = recipientOrSender(arrOfStr, 5);
        Person sender = recipientOrSender(arrOfStr, 11);
        listOfCustomers.add(sender);
        listOfCustomers.add(recipient);

        if (ded.equals("na") && des.equals("na")) {
            Optional<DeliveryDate> date = Optional.empty();
            Optional<DeliverySlot> slot = Optional.empty();
            Optional<Earning> earn = Optional.of(new Earning(ear));
            DeliveryJob job = new DeliveryJob(rid, sid, date, slot, earn, "");
            listOfAddDeliveryJob.add(job);
        } else {
            DeliveryJob job = new DeliveryJob(rid, sid, ded, des, ear, "");
            listOfAddDeliveryJob.add(job);
        }
    }

}

