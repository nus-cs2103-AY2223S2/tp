package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.skill.Skill;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

import java.util.HashSet;

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
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, INVALID_SKILL_DESC, Skill.MESSAGE_CONSTRAINTS); // invalid skill
        assertParseFailure(parser, INVALID_MOD_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module
        assertParseFailure(parser, INVALID_MOD_SEM_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module


        // invalid phone followed by valid email
        assertParseFailure(parser, INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);

        // number of old and new modules or skills should be the same
        assertParseFailure(parser, MOD_OLD_DESC_AY2223S2_CS2103T, EditCommand.MESSAGE_INCORRECT_OLD_NEW_MOD_PREFIX);
        assertParseFailure(parser, MOD_NEW_DESC_AY2223S2_CS2103T, EditCommand.MESSAGE_INCORRECT_OLD_NEW_MOD_PREFIX);
        assertParseFailure(parser, SKILL_OLD_DESC_CSHARP, EditCommand.MESSAGE_INCORRECT_OLD_NEW_SKILL_PREFIX);
        assertParseFailure(parser, SKILL_NEW_DESC_CSHARP, EditCommand.MESSAGE_INCORRECT_OLD_NEW_SKILL_PREFIX);


    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + SKILL_DESC_JAVA
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + SKILL_ADD_DESC_CSHARP + MOD_ADD_DESC_AY2223S2_CS2103T;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_JAVA, VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, NAME_DESC_BOB, expectedCommand);

        // phone
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, PHONE_DESC_BOB, expectedCommand);

        // email
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withSkills(VALID_SKILL_CSHARP)
                .withModules(VALID_MODULE_AY2223S2_CS2103T).withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, EMAIL_DESC_BOB, expectedCommand);

        // address
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_BOB)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, ADDRESS_DESC_BOB, expectedCommand);

        // skills
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA).withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, SKILL_ADD_DESC_JAVA, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + SKILL_ADD_DESC_CSHARP + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + SKILL_ADD_DESC_CSHARP
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SKILL_DESC_JAVA;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_CSHARP,
                        VALID_SKILL_JAVA).withModules(VALID_MODULE_AY2223S2_CS2103T)
                .build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY).withSkills(VALID_SKILL_CSHARP)
                .withModules(VALID_MODULE_AY2223S2_CS2103T)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetSkills_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills().withModules(VALID_MODULE_AY2223S2_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, SKILL_EMPTY, expectedCommand);
    }
    @Test
    public void parse_resetModules_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkills(VALID_SKILL_CSHARP).withModules().build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, MODULE_EMPTY, expectedCommand);
    }
}
