package codoc.logic.parser;

import static codoc.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static codoc.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static codoc.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static codoc.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
import static codoc.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static codoc.testutil.TypicalPersons.AMY;
import static codoc.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import codoc.commons.core.Messages;
import codoc.logic.commands.AddCommand;
import codoc.model.person.Email;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.skill.Skill;
import codoc.model.person.Address;
import codoc.model.person.Phone;
import codoc.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withSkills(VALID_SKILL_CSHARP).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        CommandParserTestUtil.assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        CommandParserTestUtil.assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        CommandParserTestUtil.assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple skills - all accepted
        Person expectedPersonMultipleSkills = new PersonBuilder(BOB).withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA)
                .build();
        CommandParserTestUtil.assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, new AddCommand(expectedPersonMultipleSkills));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero skills
        Person expectedPerson = new PersonBuilder(AMY).withSkills().build();
        CommandParserTestUtil.assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        CommandParserTestUtil.assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        CommandParserTestUtil.assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        CommandParserTestUtil.assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        CommandParserTestUtil.assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        CommandParserTestUtil.assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Address.MESSAGE_CONSTRAINTS);

        // invalid skill
        CommandParserTestUtil.assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_SKILL_DESC + VALID_SKILL_CSHARP, Skill.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKILL_DESC_JAVA + SKILL_DESC_PYTHON,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
