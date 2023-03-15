package seedu.address.model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactIndex;
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
    private static final Logger logger = LogsCenter.getLogger(SampleDataUtil.class);

    /**
     * Gets sample data from the text file "sampleData.txt"
     * @param size Size of the sample data to be returned.
     */
    public static ReadOnlyEduMate getSampleEduMate(int size) {
        EduMate sampleEm = new EduMate();
        User sampleUser = getSampleUser();
        try {
            List<Person> samplePersons = getSamplePersons();
            Collections.shuffle(samplePersons);
            samplePersons.stream()
                    .filter(Objects::nonNull)
                    .limit(size)
                    .forEach(p -> {
                        p.setCommonModules(sampleUser.getImmutableModuleTags());
                        sampleEm.addPerson(p);
                    });
        } catch (FileNotFoundException fnfe) {
            logger.info("Sample Data not found: " + fnfe.getMessage());
        }
        sampleEm.setUser(sampleUser);
        return sampleEm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<GroupTag> getGroupTagSet(String... strings) {
        return Arrays.stream(strings)
                .filter(Predicate.not(String::isEmpty))
                .map(String::trim)
                .map(GroupTag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a group tag set containing the list of strings given.
     * Splits a string by space, and processes the tags as varargs.
     */
    public static Set<GroupTag> getGroupTagSetFromUnsplitted(String unsplittedString) {
        return getGroupTagSet(unsplittedString.split(" "));
    }

    /**
     * Returns a module tag set containing the list of strings given.
     */
    public static Set<ModuleTag> getModuleTagSet(String... strings) {
        return Arrays.stream(strings)
                .filter(Predicate.not(String::isEmpty))
                .map(String::trim)
                .map(ModuleTag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a module tag set containing the list of strings given.
     * Splits a string by space, and processes the tags as varargs.
     */
    public static Set<ModuleTag> getModuleTagSetFromUnsplitted(String unsplittedString) {
        return getModuleTagSet(unsplittedString.split(" "));
    }

    /**
     * Returns a sample User singleton object.
     */
    public static User getSampleUser() {
        return new User(new Name("Linus Richards"),
                new Phone("90102030"),
                new Email("linusrichards@gmail.com"),
                new Address("National University of Singapore"),
                new TelegramHandle("@linusrichards"),
                new ContactIndex(0),
                getGroupTagSet(),
                getModuleTagSetFromUnsplitted("CS2100 CS2101 CS2102 CS2103 CS2104 CS2105")
        );
    }

    /**
     * Formats the data from the text file.
     * @param personData String representing the person data in the text file.
     * @param index The index to assign to the person.
     */
    private static Person getSamplePerson(String personData, int index) {
        List<String> personDataList = Stream.of(personData.split("\\|"))
                .map(String::trim)
                .limit(7) // Maximum of 7 fields
                .collect(Collectors.toList());

        if (personDataList.size() != 7) {
            return null;
        }

        ContactIndex contactIndex = new ContactIndex(index);
        Name name = new Name(personDataList.get(0));
        Phone phone = new Phone(personDataList.get(1));
        Email email = new Email(personDataList.get(2));
        Address address = new Address(personDataList.get(3));
        TelegramHandle telegramHandle = new TelegramHandle(personDataList.get(4));
        Set<GroupTag> groupTagSet = getGroupTagSetFromUnsplitted(personDataList.get(5));
        Set<ModuleTag> moduleTagSet = getModuleTagSetFromUnsplitted(personDataList.get(6));

        return new Person(name, phone, email, address,
                telegramHandle, contactIndex, groupTagSet, moduleTagSet);
    }

    /**
     * Returns a sample array of Persons.
     */
    public static List<Person> getSamplePersons() throws FileNotFoundException {
        File sampleDataFile = new File("src/main/java/seedu/address/model/util/sampleData.txt");
        Scanner scanner = new Scanner(sampleDataFile);
        List<Person> personSet = new ArrayList<>();
        int index = 1;

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            personSet.add(getSamplePerson(nextLine, index));
            index++;
        }

        return personSet;
    }
}

