package seedu.address.model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.User;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Contains utility methods for populating {@code EduMate} with sample data.
 */
public class SampleDataUtil {
    private static final Path SAMPLE_DATA_FOLDER = Paths.get("src", "main", "java", "seedu.address", "model", "util");
    private static final Logger logger = LogsCenter.getLogger(SampleDataUtil.class);

    /**
     * Gets sample data from the text file "sampleData.txt"
     * @param size Size of the sample data to be returned.
     */
    public static ReadOnlyEduMate getSampleEduMate(int size) {
        EduMate sampleEm = new EduMate();
        try {
            List<Person> samplePersons = getSamplePersons();
            Collections.shuffle(samplePersons);
            samplePersons.stream().limit(size).forEach(sampleEm::addPerson);
        } catch (FileNotFoundException fnfe) {
            logger.info("Sample Data not found: " + fnfe.getMessage());
        }
        sampleEm.setUser(getSampleUser());
        return sampleEm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<GroupTag> getGroupTagSet(String... strings) {
        return Arrays.stream(strings)
                .filter(Predicate.not(String::isEmpty))
                .map(GroupTag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a module tag set containing the list of strings given.
     */
    public static Set<ModuleTag> getModuleTagSet(String... strings) {
        return Arrays.stream(strings)
                .filter(Predicate.not(String::isEmpty))
                .map(ModuleTag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a sample User singleton object.
     */
    public static User getSampleUser() {
        return User.getSingletonUser(new Name("Linus Richards"),
                new Phone("90102030"),
                new Email("linusrichards@gmail.com"),
                new Address("National University of Singapore"),
                new TelegramHandle("@linusrichards"),
                getGroupTagSet(),
                getModuleTagSet("CS2100", "CS2101", "CS2102", "CS2103", "CS2104", "CS2105")
        );
    }

    /**
     * Formats the data from the text file.
     * @param personData String representing the person data in the text file.
     */
    private static Person getSamplePerson(String personData) {
        String[] personDataAsArray = personData.split("\\|");
        Name name = new Name(personDataAsArray[0]);
        Phone phone = new Phone(personDataAsArray[1]);
        Email email = new Email(personDataAsArray[2]);
        Address address = new Address(personDataAsArray[3]);
        TelegramHandle telegramHandle = new TelegramHandle(personDataAsArray[4]);
        Set<GroupTag> groupTagSet = getGroupTagSet(personDataAsArray[5].split(" "));
        Set<ModuleTag> moduleTagSet = getModuleTagSet(personDataAsArray[6].split(" "));
        return new Person(name, phone, email, address,
                telegramHandle, groupTagSet, moduleTagSet);
    }

    /**
     * Returns a sample array of Persons.
     */
    public static List<Person> getSamplePersons() throws FileNotFoundException {
        File sampleDataFile = new File("src/main/java/seedu/address/model/util/sampleData.txt");
        Scanner scanner = new Scanner(sampleDataFile);
        List<Person> personSet = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            personSet.add(getSamplePerson(nextLine));
        }
        return personSet;
    }
}

