package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_CHARLIE;

import seedu.address.model.person.Elderly;

/**
 * A utility class containing a list of {@code Elderly} objects to be used in tests.
 * (to be finished)
 */
public class TypicalElderlys {

    public static final Elderly AMY = new ElderlyBuilder().withName("Amy Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("amy@example.com")
            .withPhone("94351253")
            .withTags("friends").withAge("20").withNric(VALID_NRIC_AMY).build();
    public static final Elderly BOB = new ElderlyBuilder().withName("Bob Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("bobby@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withAge("23").withNric(VALID_NRIC_BOB).build();
    public static final Elderly CHARLIE = new ElderlyBuilder().withName("Charlie Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withAge("31").withNric(VALID_NRIC_CHARLIE).build();
}
