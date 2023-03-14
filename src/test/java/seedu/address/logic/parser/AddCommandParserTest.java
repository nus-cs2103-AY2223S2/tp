package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LAST_FED_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LAST_FED_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LAST_FED_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TANK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_FED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TANK_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFishes.AMY;
import static seedu.address.testutil.TypicalFishes.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.fish.Address;
import seedu.address.model.fish.Email;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FishBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Fish expectedFish = new FishBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedFish, Index.fromOneBased(1)));

        // multiple names - last name accepted
        assertParseSuccess(parser, TANK_DESC + NAME_DESC_AMY + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedFish, Index.fromOneBased(1)));

        // multiple LastFedDates - last LastFedDate accepted
        assertParseSuccess(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_AMY + LAST_FED_DATE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedFish, Index.fromOneBased(1)));

        // multiple emails - last email accepted
        assertParseSuccess(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedFish, Index.fromOneBased(1)));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedFish, Index.fromOneBased(1)));

        // multiple tags - all accepted
        Fish expectedFishMultipleTags = new FishBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedFishMultipleTags, Index.fromOneBased(1)));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Fish expectedFish = new FishBuilder(AMY).withTags().build();
        assertParseSuccess(parser, TANK_DESC + NAME_DESC_AMY + LAST_FED_DATE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY, new AddCommand(expectedFish, Index.fromOneBased(1)));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing tank prefix
        assertParseFailure(parser, VALID_TANK_INDEX + NAME_DESC_BOB + LAST_FED_DATE_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, TANK_DESC + VALID_NAME_BOB + LAST_FED_DATE_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB, expectedMessage);

        // missing LastFedDate prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_LAST_FED_DATE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_AMY + VALID_EMAIL_BOB
                        + ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_AMY + EMAIL_DESC_BOB
                        + VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TANK_INDEX + VALID_NAME_BOB + LAST_FED_DATE_DESC_AMY + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, TANK_DESC + INVALID_NAME_DESC + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid LastFedDate
        assertParseFailure(parser, TANK_DESC + NAME_DESC_BOB + INVALID_LAST_FED_DATE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, LastFedDate.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, TANK_DESC + INVALID_NAME_DESC + LAST_FED_DATE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TANK_DESC + NAME_DESC_BOB + LAST_FED_DATE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
