package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PetPal;
import seedu.address.model.ReadOnlyPetPal;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Email;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Pet[] getSamplePets() {
        return new Pet[] {
            new Pet(new OwnerName("Alex Yeoh"), new Name("Woofers"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("Pomeranian")),
            new Pet(new OwnerName("June Tan"), new Name("Max"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("GoldenRetriever")),
            new Pet(new OwnerName("Ben Leo"), new Name("Charlie"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("GermanShepherd")),
            new Pet(new OwnerName("Irfan"), new Name("Buddy"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("Chihuahua")),
            new Pet(new OwnerName("Lucy Lee"), new Name("Milo"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("BorderCollie")),
            new Pet(new OwnerName("Alexandra Toh"), new Name("Lola"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("SiberianHusky"))
        };
    }

    public static ReadOnlyPetPal getSamplePetPal() {
        PetPal sampleAb = new PetPal();
        for (Pet samplePerson : getSamplePets()) {
            sampleAb.addPet(samplePerson);
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
