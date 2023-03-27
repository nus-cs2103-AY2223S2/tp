package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyPair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.BirthDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FriendlyLink} with sample data.
 */
public class SampleDataUtil {
    /**
     * Returns a list of sample volunteer data.
     *
     * @return Sample volunteer list.
     */
    public static Volunteer[] getSampleVolunteers() {
        return new Volunteer[] {
            new Volunteer(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Nric("S4392754D"), new BirthDate("2000-01-13"), new Region("NORTH"), getTagSet("energetic"),
                    getMedicalTagSet("CPR BASIC", "AED ADVANCED"), new HashSet<>()),
            new Volunteer(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Nric("S9375438F"), new BirthDate("1989-02-13"), new Region("WEST"),
                    getTagSet("patience", "strong"), new HashSet<>(), new HashSet<>()),
            new Volunteer(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Nric("T9451847S"), new BirthDate("1993-03-13"), new Region("CENTRAL"),
                    getTagSet("reliable"),
                    getMedicalTagSet("AED INTERMEDIATE"), new HashSet<>()),
            new Volunteer(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Nric("S1837493D"), new BirthDate("1996-04-13"), new Region("NORTHEAST"),
                    getTagSet("active"), new HashSet<>(), new HashSet<>()),
            new Volunteer(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Nric("T0349743E"), new BirthDate("1979-05-13"), new Region("EAST"),
                    getTagSet("energetic"), new HashSet<>(), new HashSet<>()),
            new Volunteer(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Nric("S7692754J"), new BirthDate("1984-06-13"), new Region("NORTH"),
                    getTagSet("eager"), new HashSet<>(), new HashSet<>())
        };
    }

    /**
     * Returns a list of sample elderly data.
     *
     * @return Sample elderly list.
     */
    public static Elderly[] getSampleElderly() {
        HashSet<AvailableDate> dates = new HashSet<>();
        dates.add(getAvailableDate("2023-03-10", "2023-03-11"));
        dates.add(getAvailableDate("2023-03-11", "2023-03-12"));
        dates.add(getAvailableDate("2023-03-12", "2023-03-13"));
        dates.add(getAvailableDate("2023-03-13", "2023-03-14"));

        return new Elderly[] {
            new Elderly(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Nric("S4392759D"), new BirthDate("1940-01-13"), new Region("CENTRAL"), new RiskLevel("LOW"),
                    getTagSet("energetic"), dates),
            new Elderly(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Nric("S9375439F"), new BirthDate("1941-02-13"), new Region("NORTHEAST"), new RiskLevel("LOW"),
                    getTagSet("colleagues", "friends"), new HashSet<>()),
            new Elderly(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Nric("T9451849S"), new BirthDate("1942-03-13"), new Region("WEST"), new RiskLevel("LOW"),
                    getTagSet("neighbours"), new HashSet<>()),
            new Elderly(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Nric("S1837499D"), new BirthDate("1943-04-13"), new Region("EAST"), new RiskLevel("LOW"),
                    getTagSet("family"), new HashSet<>()),
            new Elderly(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Nric("T0349749E"), new BirthDate("1944-05-13"), new Region("CENTRAL"), new RiskLevel("LOW"),
                    getTagSet("classmates"), new HashSet<>()),
            new Elderly(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Nric("S7692759J"), new BirthDate("1945-06-13"), new Region("EAST"), new RiskLevel("LOW"),
                    getTagSet("colleagues"), new HashSet<>())
        };

        // TODO: Elderly data is currently a copy of volunteer data with additional risk level attribute
        //  and different nric(slightly), need to make it more legit
    }

    // TODO: getSamplePairs()

    public static ReadOnlyPair getSampleFriendlyLink() {
        FriendlyLink sampleFl = new FriendlyLink();

        for (Volunteer sampleVolunteer : getSampleVolunteers()) {
            sampleFl.addVolunteer(sampleVolunteer);
        }
        for (Elderly sampleElderly : getSampleElderly()) {
            sampleFl.addElderly(sampleElderly);
        }

        return sampleFl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<MedicalQualificationTag> getMedicalTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(s -> {
                    String[] parts = s.split(" ");
                    return new MedicalQualificationTag(parts[0], parts[1]);
                })
                .collect(Collectors.toSet());
    }

    public static Set<AvailableDate> getAvailableDateSet(String... strings) {
        return Arrays.stream(strings)
                .map(s -> {
                    String[] parts = s.split(",");
                    return new AvailableDate(parts[0], parts[1]);
                })
                .collect(Collectors.toSet());
    }

    public static AvailableDate getAvailableDate(String startDate, String endDate) {
        return new AvailableDate(startDate, endDate);
    }

}
