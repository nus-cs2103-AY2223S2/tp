package seedu.address.model.util;

import static seedu.address.model.util.SampleTankUtil.getSampleTanks;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Fish[] getSampleFishes() {
        Tank[] tanks = getSampleTanks();
        return new Fish[] {
            new Fish(new Name("Fish 1"), new LastFedDate("01/11/2023"), new Species("Guppy"),
                new FeedingInterval("1d0h"), tanks[0],
                    getTagSet("tempSpeciesA")),
            new Fish(new Name("Fish 2"), new LastFedDate("01/11/2023"), new Species("Tetra"),
                new FeedingInterval("0d5h"), tanks[0],
                    getTagSet("tempSpeciesA", "anotherTag")),
            new Fish(new Name("Fish 3"), new LastFedDate("01/11/2023"), new Species("Parrot Fish"),
                new FeedingInterval("1d5h"), tanks[0],
                    getTagSet("tempSpeciesB")),
            new Fish(new Name("Fish 4"), new LastFedDate("01/11/2023"), new Species("Arowana"),
                new FeedingInterval("2d0h"), tanks[1],
                    getTagSet("tempSpeciesA")),
            new Fish(new Name("Fish 5"), new LastFedDate("01/11/2023"), new Species("Flowerhorn"),
                new FeedingInterval("1d12h"), tanks[1],
                    getTagSet("tempSpeciesB")),
            new Fish(new Name("Fish 6"), new LastFedDate("01/11/2023"), new Species("Beta Fish"),
                new FeedingInterval("3d0h"), tanks[1],
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
