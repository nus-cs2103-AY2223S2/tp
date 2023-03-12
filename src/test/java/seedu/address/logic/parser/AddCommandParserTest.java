package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.skill.Skill;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withSkills(VALID_SKILL_CSHARP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple GitHub usernames - last GitHub username accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_AMY + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple skills - all accepted
        Person expectedPersonMultipleSkills = new PersonBuilder(BOB).withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, new AddCommand(expectedPersonMultipleSkills));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero skills
        Person expectedPerson = new PersonBuilder(AMY).withSkills().build();
        assertParseSuccess(parser, NAME_DESC_AMY + GITHUB_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GITHUB_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + GITHUB_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Name.MESSAGE_CONSTRAINTS);

        // invalid GitHub username
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_GITHUB_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Github.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Address.MESSAGE_CONSTRAINTS);

        // invalid skill
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_SKILL_DESC + VALID_SKILL_CSHARP, Skill.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + GITHUB_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_JAVA + SKILL_DESC_PYTHON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
