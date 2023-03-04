package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.AGE_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_AGE_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_POSTAL_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.dengue.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.POSTAL_DESC_AMY;
import static seedu.dengue.logic.commands.CommandTestUtil.POSTAL_DESC_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.dengue.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.dengue.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.dengue.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dengue.testutil.TypicalPersons.AMY;
import static seedu.dengue.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.AddCommand;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.tag.Tag;
import seedu.dengue.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple postals - last postal accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSTAL_DESC_AMY + POSTAL_DESC_BOB + DATE_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_AMY + DATE_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB + AGE_DESC_AMY
                + AGE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB + AGE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + POSTAL_DESC_AMY + DATE_DESC_AMY + AGE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB + AGE_DESC_BOB,
                expectedMessage);

        // missing postal prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_POSTAL_BOB + DATE_DESC_BOB + AGE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + VALID_DATE_BOB + AGE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB + VALID_AGE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_POSTAL_BOB + VALID_DATE_BOB + VALID_AGE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + POSTAL_DESC_BOB + DATE_DESC_BOB + AGE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid postal
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_POSTAL_DESC + DATE_DESC_BOB + AGE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Postal.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + INVALID_DATE_DESC + AGE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB + INVALID_AGE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Age.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB + AGE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + POSTAL_DESC_BOB + DATE_DESC_BOB + INVALID_AGE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + POSTAL_DESC_BOB + DATE_DESC_BOB
                + AGE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
