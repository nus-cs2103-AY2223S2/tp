package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyPair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.person.information.Region;
import seedu.address.model.person.information.RiskLevel;
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
                    new Nric("S4392754D"), new Age("29"), new Region("NORTH"), getTagSet("energetic")),
            new Volunteer(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Nric("S9375438F"), new Age("36"), new Region("WEST"), getTagSet("patience", "strong")),
            new Volunteer(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Nric("T9451847S"), new Age("22"), new Region("CENTRAL"), getTagSet("reliable")),
            new Volunteer(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Nric("S1837493D"), new Age("31"), new Region("NORTHEAST"), getTagSet("active")),
            new Volunteer(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Nric("T0349743E"), new Age("20"), new Region("EAST"), getTagSet("energetic")),
            new Volunteer(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Nric("S7692754J"), new Age("40"), new Region("NORTH"), getTagSet("eager"))
        };
    }

    /**
     * Returns a list of sample elderly data.
     *
     * @return Sample elderly list.
     */
    public static Elderly[] getSampleElderly() {
        return new Elderly[] {
            new Elderly(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Nric("S4392759D"), new Age("29"), new Region("CENTRAL"), new RiskLevel("LOW"),
                    getTagSet("energetic")),
            new Elderly(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Nric("S9375439F"), new Age("36"), new Region("NORTHEAST"), new RiskLevel("LOW"),
                    getTagSet("colleagues", "friends")),
            new Elderly(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Nric("T9451849S"), new Age("22"), new Region("WEST"), new RiskLevel("LOW"),
                    getTagSet("neighbours")),
            new Elderly(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Nric("S1837499D"), new Age("31"), new Region("EAST"), new RiskLevel("LOW"),
                    getTagSet("family")),
            new Elderly(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Nric("T0349749E"), new Age("20"), new Region("CENTRAL"), new RiskLevel("LOW"),
                    getTagSet("classmates")),
            new Elderly(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Nric("S7692759J"), new Age("40"), new Region("EAST"), new RiskLevel("LOW"),
                    getTagSet("colleagues"))
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

}
