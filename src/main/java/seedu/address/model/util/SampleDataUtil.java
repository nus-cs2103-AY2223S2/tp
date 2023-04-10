package seedu.address.model.util;

import static seedu.address.model.util.SampleTankUtil.getSampleTanks;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDateTime;
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
            new Fish(new Name("Nemo"), new LastFedDateTime("28/03/2023 12:00"), new Species("Guppy"),
                new FeedingInterval("1d0h"), tanks[0],
                    getTagSet("tempSpeciesA")),
            new Fish(new Name("Dory"), new LastFedDateTime("28/03/2023 13:00"), new Species("Tetra"),
                new FeedingInterval("0d5h"), tanks[0],
                    getTagSet("tempSpeciesA", "anotherTag")),
            new Fish(new Name("Marlin"), new LastFedDateTime("28/03/2023 23:00"), new Species("Parrot Fish"),
                new FeedingInterval("1d5h"), tanks[0],
                    getTagSet("tempSpeciesB")),
            new Fish(new Name("Bruce"), new LastFedDateTime("25/03/2023 21:00"), new Species("Arowana"),
                new FeedingInterval("2d0h"), tanks[1],
                    getTagSet("tempSpeciesA")),
            new Fish(new Name("Chum"), new LastFedDateTime("25/03/2023 07:30"), new Species("Flowerhorn"),
                new FeedingInterval("1d12h"), tanks[1],
                    getTagSet("tempSpeciesB")),
            new Fish(new Name("Anchor"), new LastFedDateTime("25/03/2023 12:30"), new Species("Beta Fish"),
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
