package arb.testutil;

import static arb.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static arb.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static arb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static arb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arb.model.AddressBook;
import arb.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withPhone("94351253").withEmail("alice@example.com")
            .withTags("friends").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesmoney", "friends").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();
    public static final Client REGINA = new ClientBuilder().withName("Regina Fuller")
            .withPhone(null).withEmail(null).build();
    public static final Client YLVETAL = new ClientBuilder().withName("Ylvetal Z").withPhone(null)
            .withEmail("ylvetal@example.com").build();
    public static final Client ZINNIA = new ClientBuilder().withName("Zinnia O").withPhone("12345")
            .withEmail(null).build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE,
                REGINA, YLVETAL, ZINNIA));
    }
}
