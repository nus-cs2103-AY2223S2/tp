package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PetPal;
import seedu.address.model.pet.Pet;

import static seedu.address.logic.commands.CommandTestUtil.*;


/**
 * A utility class containing a list of {@code Pet} objects to be used in tests.
 */
public class TypicalPets {

    public static final Pet WHISKERS = new PetBuilder().withOwnerName("Alice Pauline")
            .withName("Whiskers")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("MaineCoon")
            .withTimestamp(LocalDateTime.now().minusDays(1))
            .withDeadline("Feed cat", LocalDateTime.now().plusDays(3)).build();
    public static final Pet WOOFERS = new PetBuilder().withOwnerName("Benson Meier")
            .withName("Woofers")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withDeadline("Feed dog", LocalDateTime.now().plusDays(4)).withTimestamp(LocalDateTime.now().minusDays(1))
            .withTags("GoldenRetriever", "Male").build();
    public static final Pet BOBBY = new PetBuilder().withOwnerName("Yuan Xun")
            .withName("Bobby")
            .withAddress("311, Kent Ridge Ave 2, #04-25")
            .withEmail("yx@example.com").withPhone("91111111")
            .withDeadline("Feed turtle", LocalDateTime.now().plusDays(3)).withTimestamp(LocalDateTime.now().minusDays(1))
            .withTags("Turtle").build();
    public static final Pet GREGORY = new PetBuilder().withOwnerName("Weng Kit")
            .withName("GREGORY")
            .withAddress("411, Bukit Panjang Ave 2, #02-25")
            .withEmail("wk@example.com").withPhone("98761234")
            .withDeadline("Feed mouse", LocalDateTime.now().plusDays(3)).withTimestamp(LocalDateTime.now().minusDays(1))
            .withTags("Mouse").build();
    public static final Pet PENGY = new PetBuilder().withOwnerName("Daniel Irwan")
            .withName("Pengy")
            .withAddress("122, Bukit Batok Ave 10, #06-25")
            .withEmail("dirwan@example.com").withPhone("93323334")
            .withDeadline("Feed penguin", LocalDateTime.now().plusDays(3)).withTimestamp(LocalDateTime.now().minusDays(1))
            .withTags("Penguin").build();
    public static final Pet SHARKY = new PetBuilder().withOwnerName("Shui Mei")
            .withName("SHARKY")
            .withAddress("444, Chinatown St 2, #01-25")
            .withEmail("shuimei@example.com").withPhone("12345678")
            .withDeadline("Feed parrot", LocalDateTime.now().plusDays(3)).withTimestamp(LocalDateTime.now().minusDays(1))
            .withTags("Parrot", "Female").build();
    public static final Pet GRIZZLY = new PetBuilder().withOwnerName("Yang Shan")
            .withName("Grizzly")
            .withAddress("888, Shenton Ave 2, #02-25")
            .withEmail("ys123@example.com").withPhone("92313222")
            .withDeadline("Vaccination", LocalDateTime.now().plusDays(10)).withTimestamp(LocalDateTime.now().minusDays(1))
            .withTags("Snake").build();

    // Manually added
    public static final Pet HOON = new PetBuilder().withOwnerName("Hoon Meier").withName("Doggo").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Pet IDA = new PetBuilder().withOwnerName("Ida Mueller").withName("catto").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Pet's details found in {@code CommandTestUtil}
    public static final Pet EXAMPLE_CAT = new PetBuilder().withOwnerName(VALID_NAME_AMY).withName(VALID_NAME_CAT)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTimestamp(VALID_TIMESTAMP_AMY).withDeadline(VALID_DEADLINE_AMY1, VALID_DEADLINE_AMY2)
            .build();

    public static final Pet EXAMPLE_DOG = new PetBuilder().withOwnerName(VALID_NAME_BOB).withName(VALID_NAME_DOG)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTimestamp(VALID_TIMESTAMP_BOB).withDeadline(VALID_DEADLINE_BOB1, VALID_DEADLINE_BOB2)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPets() {} // prevents instantiation

    /**
     * Returns an {@code PetPal} with all the typical pets.
     */
    public static PetPal getTypicalPetPal() {
        PetPal pp = new PetPal();
        for (Pet pet : getTypicalPet()) {
            pp.addPet(pet);
        }
        return pp;
    }

    public static PetPal getTypicalArchive() {
        return new PetPal();
    }

    public static PetPal getFilledArchive() {
        PetPal pp = new PetPal();
        for (Pet pet : getTypicalPet()) {
            pp.addPet(pet);
        }
        return pp;
    }

    public static List<Pet> getTypicalPet() {
        return new ArrayList<>(Arrays.asList(WHISKERS, WOOFERS, GREGORY, PENGY, BOBBY, SHARKY, GRIZZLY));
    }

}
