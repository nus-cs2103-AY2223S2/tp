package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.PetPal;
import seedu.address.model.pet.NameContainsKeywordsPredicate;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.EditPetDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_DOG = "Doggo";
    public static final String VALID_NAME_CAT = "Catto";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_DOG = "pomeranian";
    public static final String VALID_TAG_CAT = "siamese";
    public static final String INVALID_OWNER_NAME_DESC = " " + PREFIX_OWNER_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PET_NAME_DESC = " " + PREFIX_NAME + "Dog&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final EditCommand.EditPetDescriptor DESC_AMY;
    public static final EditCommand.EditPetDescriptor DESC_BOB;
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String VALID_DEADLINE_AMY1 = "Vaccination";
    public static final LocalDateTime VALID_DEADLINE_AMY2 = LocalDateTime.now().plusDays(1)
            .truncatedTo(ChronoUnit.MINUTES);
    public static final String VALID_DEADLINE_BOB1 = "Feed Bob";
    public static final LocalDateTime VALID_DEADLINE_BOB2 = LocalDateTime.now().plusDays(2)
            .truncatedTo(ChronoUnit.MINUTES);
    public static final LocalDateTime VALID_TIMESTAMP_AMY = LocalDateTime.now().minusDays(1)
            .truncatedTo(ChronoUnit.MINUTES);
    public static final LocalDateTime VALID_TIMESTAMP_BOB = LocalDateTime.now().minusDays(2)
            .truncatedTo(ChronoUnit.MINUTES);
    public static final String NAME_DESC_AMY = " " + PREFIX_OWNER_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_OWNER_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_DOG = " " + PREFIX_NAME + VALID_NAME_DOG;
    public static final String NAME_DESC_CAT = " " + PREFIX_NAME + VALID_NAME_CAT;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_DOG = " " + PREFIX_TAG + VALID_TAG_DOG;
    public static final String TAG_DESC_CAT = " " + PREFIX_TAG + VALID_TAG_CAT;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY1 + " - "
            + VALID_DEADLINE_AMY2.format(formatter);
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB1 + " - "
            + VALID_DEADLINE_BOB2.format(formatter);
    public static final String TIMESTAMP_DESC_AMY = " " + PREFIX_TIMESTAMP + VALID_TIMESTAMP_AMY.format(formatter);
    public static final String TIMESTAMP_DESC_BOB = " " + PREFIX_TIMESTAMP + VALID_TIMESTAMP_BOB.format(formatter);


    static {
        DESC_AMY = new EditPetDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_CAT).build();
        DESC_BOB = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_DOG, VALID_TAG_CAT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the PetPal list, filtered pet list and selected pet in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PetPal expectedPetPal = new PetPal(actualModel.getPetPal());
        List<Pet> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPetList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPetPal, actualModel.getPetPal());
        assertEquals(expectedFilteredList, actualModel.getFilteredPetList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s PetPal list.
     */
    public static void showPetAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPetList().size());

        Pet pet = model.getFilteredPetList().get(targetIndex.getZeroBased());
        final String[] splitName = pet.getName().fullName.split("\\s+");
        model.updateFilteredPetList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPetList().size());
    }

}
