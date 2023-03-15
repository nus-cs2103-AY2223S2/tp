package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_LINKEDIN_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static codoc.logic.commands.CommandTestUtil.LINKEDIN_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.LINKEDIN_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.MOD_DESC_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static codoc.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static codoc.logic.commands.CommandTestUtil.SKILL_DESC_CSHARP;
import static codoc.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_MODULE_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static codoc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static codoc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static codoc.testutil.TypicalPersons.AMY;
import static codoc.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import codoc.logic.commands.AddCommand;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Person;
import codoc.model.skill.Skill;
import codoc.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withSkills(VALID_SKILL_CSHARP)
                .withModules(VALID_MODULE_AY2223S2_CS2103T).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + LINKEDIN_DESC_BOB + SKILL_DESC_CSHARP + MOD_DESC_AY2223S2_CS2103T,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + LINKEDIN_DESC_BOB + SKILL_DESC_CSHARP + MOD_DESC_AY2223S2_CS2103T,
                new AddCommand(expectedPerson));

        // multiple GitHub usernames - last GitHub username accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_AMY + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + LINKEDIN_DESC_BOB + SKILL_DESC_CSHARP + MOD_DESC_AY2223S2_CS2103T,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + LINKEDIN_DESC_BOB + SKILL_DESC_CSHARP + MOD_DESC_AY2223S2_CS2103T,
                new AddCommand(expectedPerson));

        // multiple linkedins - last linkedin accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + LINKEDIN_DESC_AMY
                + LINKEDIN_DESC_BOB + SKILL_DESC_CSHARP + MOD_DESC_AY2223S2_CS2103T,
                new AddCommand(expectedPerson));

        // multiple skills - all accepted
        Person expectedPersonMultipleSkills = new PersonBuilder(BOB).withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + LINKEDIN_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_CSHARP + MOD_DESC_AY2223S2_CS2103T,
                new AddCommand(expectedPersonMultipleSkills));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero skills
        Person expectedPerson = new PersonBuilder(AMY).withSkills().withModules().build();
        assertParseSuccess(parser, NAME_DESC_AMY + GITHUB_DESC_AMY + EMAIL_DESC_AMY + LINKEDIN_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + LINKEDIN_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + VALID_EMAIL_BOB + LINKEDIN_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GITHUB_BOB + VALID_EMAIL_BOB + VALID_LINKEDIN_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + GITHUB_DESC_BOB + EMAIL_DESC_BOB + LINKEDIN_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_CSHARP, Name.MESSAGE_CONSTRAINTS);

        // invalid GitHub username
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_GITHUB_DESC + EMAIL_DESC_BOB + LINKEDIN_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_CSHARP, Github.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + INVALID_EMAIL_DESC + LINKEDIN_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_CSHARP, Email.MESSAGE_CONSTRAINTS);

        // invalid linkedin
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + INVALID_LINKEDIN_DESC
                + SKILL_DESC_JAVA + SKILL_DESC_CSHARP, Linkedin.MESSAGE_CONSTRAINTS);

        // invalid skill
        assertParseFailure(parser, NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + LINKEDIN_DESC_BOB
                + INVALID_SKILL_DESC + VALID_SKILL_CSHARP, Skill.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + GITHUB_DESC_BOB + EMAIL_DESC_BOB + INVALID_LINKEDIN_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + LINKEDIN_DESC_BOB + SKILL_DESC_JAVA + SKILL_DESC_CSHARP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
