package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_LINKEDIN_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_MOD_ADD_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_MOD_ADD_SEM_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static codoc.logic.commands.CommandTestUtil.INVALID_SKILL_ADD_DESC;
import static codoc.logic.commands.CommandTestUtil.LINKEDIN_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.LINKEDIN_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.MOD_ADD_DESC_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.MOD_NEW_DESC_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.MOD_OLD_DESC_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.SKILL_ADD_DESC_CSHARP;
import static codoc.logic.commands.CommandTestUtil.SKILL_ADD_DESC_JAVA;
import static codoc.logic.commands.CommandTestUtil.SKILL_NEW_DESC_CSHARP;
import static codoc.logic.commands.CommandTestUtil.SKILL_OLD_DESC_CSHARP;
import static codoc.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_MODULE_AY2223S2_CS2103T;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static codoc.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD_DELETE;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL_DELETE;
import static codoc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static codoc.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import codoc.logic.commands.EditCommand;
import codoc.logic.commands.EditCommand.EditPersonDescriptor;
import codoc.model.module.Module;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.skill.Skill;
import codoc.testutil.EditPersonDescriptorBuilder;
import codoc.testutil.PersonBuilder;

public class EditCommandParserTest {

    private static final String SKILL_EMPTY = " " + PREFIX_SKILL_DELETE;
    private static final String MODULE_EMPTY = " " + PREFIX_MOD_DELETE;


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser(new PersonBuilder().build());

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_preambleExistence_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_GITHUB_DESC, Github.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_LINKEDIN_DESC, Linkedin.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, INVALID_SKILL_ADD_DESC, Skill.MESSAGE_CONSTRAINTS); // invalid skill
        assertParseFailure(parser, INVALID_MOD_ADD_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module
        assertParseFailure(parser, INVALID_MOD_ADD_SEM_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module


        // invalid phone followed by valid email
        assertParseFailure(parser, INVALID_GITHUB_DESC + EMAIL_DESC_AMY, Github.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, GITHUB_DESC_BOB + INVALID_GITHUB_DESC, Github.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_LINKEDIN_AMY
                        + VALID_GITHUB_AMY, Name.MESSAGE_CONSTRAINTS);

        // number of old and new modules or skills should be the same
        assertParseFailure(parser, MOD_OLD_DESC_AY2223S2_CS2103T, EditCommand.MESSAGE_INCORRECT_OLD_NEW_MOD_PREFIX);
        assertParseFailure(parser, MOD_NEW_DESC_AY2223S2_CS2103T, EditCommand.MESSAGE_INCORRECT_OLD_NEW_MOD_PREFIX);
        assertParseFailure(parser, SKILL_OLD_DESC_CSHARP, EditCommand.MESSAGE_INCORRECT_OLD_NEW_SKILL_PREFIX);
        assertParseFailure(parser, SKILL_NEW_DESC_CSHARP, EditCommand.MESSAGE_INCORRECT_OLD_NEW_SKILL_PREFIX);


    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = GITHUB_DESC_BOB + SKILL_ADD_DESC_JAVA
                + EMAIL_DESC_AMY + LINKEDIN_DESC_AMY + NAME_DESC_AMY + SKILL_ADD_DESC_CSHARP
                + MOD_ADD_DESC_AY2223S2_CS2103T;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withGithub(VALID_GITHUB_BOB).withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_JAVA, VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = GITHUB_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withCourse(VALID_COURSE_AMY)
                .withYear(VALID_YEAR_BOB)
                .withGithub(VALID_GITHUB_BOB)
                .withEmail(VALID_EMAIL_AMY)
                .withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_CSHARP)
                .withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB).withGithub(VALID_GITHUB_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, NAME_DESC_BOB, expectedCommand);

        // phone
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGithub(VALID_GITHUB_BOB)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, GITHUB_DESC_BOB, expectedCommand);

        // email
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGithub(VALID_GITHUB_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withLinkedin(VALID_LINKEDIN_AMY).withSkills(VALID_SKILL_CSHARP)
                .withModules(VALID_MODULE_AY2223S2_CS2103T).withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, EMAIL_DESC_BOB, expectedCommand);

        // address
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGithub(VALID_GITHUB_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_BOB)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, LINKEDIN_DESC_BOB, expectedCommand);

        // skills
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGithub(VALID_GITHUB_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, SKILL_ADD_DESC_JAVA, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = GITHUB_DESC_AMY + LINKEDIN_DESC_AMY + EMAIL_DESC_AMY
                + SKILL_ADD_DESC_CSHARP + GITHUB_DESC_AMY + LINKEDIN_DESC_AMY + EMAIL_DESC_AMY + SKILL_ADD_DESC_CSHARP
                + GITHUB_DESC_BOB + LINKEDIN_DESC_BOB + EMAIL_DESC_BOB + SKILL_ADD_DESC_JAVA;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withGithub(VALID_GITHUB_BOB).withEmail(VALID_EMAIL_BOB).withLinkedin(VALID_LINKEDIN_BOB)
                .withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA).withModules(VALID_MODULE_AY2223S2_CS2103T)
                .build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_GITHUB_DESC + GITHUB_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withGithub(VALID_GITHUB_BOB).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EMAIL_DESC_BOB + INVALID_GITHUB_DESC + LINKEDIN_DESC_BOB
                + GITHUB_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withLinkedin(VALID_LINKEDIN_AMY).withSkills(VALID_SKILL_CSHARP)
                .withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withGithub(VALID_GITHUB_BOB).withEmail(VALID_EMAIL_BOB)
                .withLinkedin(VALID_LINKEDIN_BOB).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetSkills_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withGithub(VALID_GITHUB_AMY).withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills().withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, SKILL_EMPTY, expectedCommand);
    }
    @Test
    public void parse_resetModules_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withCourse(VALID_COURSE_AMY).withYear(VALID_YEAR_BOB)
                .withGithub(VALID_GITHUB_AMY).withEmail(VALID_EMAIL_AMY).withLinkedin(VALID_LINKEDIN_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules().build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, MODULE_EMPTY, expectedCommand);
    }
}
