package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.PolicyTag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        Meeting introMeeting = new Meeting("Policy introduction with Jun Jie", 
                    LocalDateTime.of(2023, 4, 14, 14, 0),
                    LocalDateTime.of(2023, 4, 14, 16, 0));
        Meeting followupMeeting = new Meeting("Policy follow up with Jun Jie", 
                    LocalDateTime.of(2023, 5, 20, 14, 0),
                    LocalDateTime.of(2023, 5, 20, 16, 0));

        ArrayList<Meeting> junjieMeetingList = new ArrayList<>();
        junjieMeetingList.add(followupMeeting);
        junjieMeetingList.add(introMeeting);

        return new Person[] {
            new Person(new Name("Lim Jun Jie"), new Phone("87438807"), new Email("limjunjie@gmail.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("Insurance"), 
                junjieMeetingList),
            new Person(new Name("Tan Jia Qi"), new Phone("99272758"), new Email("tanjq@gmail.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("Investment", "Insurance"), new ArrayList<>()),
            new Person(new Name("Fariq Ahmad"), new Phone("93210283"), new Email("ahmadfariq@hotmail.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("Retirement"), new ArrayList<>()),
            new Person(new Name("Rohit Sharma"), new Phone("91031282"), new Email("sharma@outlook.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("Investment"), new ArrayList<>()),
            new Person(new Name("Irfan Iskandar"), new Phone("92492021"), new Email("irfan@gmail.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("DebtManagement"), new ArrayList<>()),
            new Person(new Name("Joe Sanders"), new Phone("92624417"), new Email("joesanders@yahoo.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("AssetManagement", "Investment"), new ArrayList<>())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<PolicyTag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(PolicyTag::new)
                .collect(Collectors.toSet());
    }

}
