package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.fish.Address;
import seedu.address.model.fish.Species;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Fish[] getSampleFishes() {
        return new Fish[] {
            new Fish(new Name("Fish 1"), new LastFedDate("01/11/2023"), new Species("Guppy"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("tempSpeciesA")),
            new Fish(new Name("Fish 2"), new LastFedDate("01/11/2023"), new Species("Tetra"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("tempSpeciesA", "anotherTag")),
            new Fish(new Name("Fish 3"), new LastFedDate("01/11/2023"), new Species("Parrot Fish"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("tempSpeciesB")),
            new Fish(new Name("Fish 4"), new LastFedDate("01/11/2023"), new Species("Arowana"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("tempSpeciesA")),
            new Fish(new Name("Fish 5"), new LastFedDate("01/11/2023"), new Species("Flowerhorn"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("tempSpeciesB")),
            new Fish(new Name("Fish 6"), new LastFedDate("01/11/2023"), new Species("Beta Fish"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("tempSpeciesC"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Fish sampleFish : getSampleFishes()) {
            sampleAb.addFish(sampleFish);
        }
        return sampleAb;
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
