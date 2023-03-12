//package seedu.address.logic.parser;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
//import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
//import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.EditCommand;
//import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Github;
//import seedu.address.model.skill.Skill;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
//
//public class EditCommandParserTest {
//
//    private static final String SKILL_EMPTY = " " + PREFIX_SKILL;
//
//    private static final String MESSAGE_INVALID_FORMAT =
//            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
//
//    private EditCommandParser parser = new EditCommandParser();
//
//    @Test
//    public void parse_missingParts_failure() {
//        // no index specified
//        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);
//
//        // no field specified
//        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);
//
//        // no index and no field specified
//        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidPreamble_failure() {
//        // negative index
//        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
//
//        // zero index
//        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
//
//        // invalid arguments being parsed as preamble
//        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
//
//        // invalid prefix being parsed as preamble
//        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
//        assertParseFailure(parser, "1" + INVALID_GITHUB_DESC, Github.MESSAGE_CONSTRAINTS); // invalid github
//        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
//        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
//        assertParseFailure(parser, "1" + INVALID_SKILL_DESC, Skill.MESSAGE_CONSTRAINTS); // invalid skill
//
//        // invalid github followed by valid email
//        assertParseFailure(parser, "1" + INVALID_GITHUB_DESC + EMAIL_DESC_AMY, Github.MESSAGE_CONSTRAINTS);
//
//        // valid github followed by invalid github. The test case for invalid github followed by valid github
//        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
//        assertParseFailure(parser, "1" + GITHUB_DESC_BOB + INVALID_GITHUB_DESC, Github.MESSAGE_CONSTRAINTS);
//
//        // while parsing {@code PREFIX_SKILL} alone will reset the skills of the {@code Person} being edited,
//        // parsing it together with a valid skill results in error
//        assertParseFailure(parser, "1" + SKILL_DESC_PYTHON + SKILL_DESC_JAVA + SKILL_EMPTY,
//                Skill.MESSAGE_CONSTRAINTS);
//        assertParseFailure(parser, "1" + SKILL_DESC_PYTHON + SKILL_EMPTY + SKILL_DESC_JAVA,
//                Skill.MESSAGE_CONSTRAINTS);
//        assertParseFailure(parser, "1" + SKILL_EMPTY + SKILL_DESC_PYTHON + SKILL_DESC_JAVA,
//                Skill.MESSAGE_CONSTRAINTS);
//
//        // multiple invalid values, but only the first invalid value is captured
//        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY +
//        VALID_GITHUB_AMY,
//                Name.MESSAGE_CONSTRAINTS);
//    }
//
//    @Test
//    public void parse_allFieldsSpecified_success() {
//        Index targetIndex = INDEX_SECOND_PERSON;
//        String userInput = targetIndex.getOneBased() + GITHUB_DESC_BOB + SKILL_DESC_JAVA
//                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + SKILL_DESC_PYTHON;
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
//                .withGithub(VALID_GITHUB_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
//                .withSkills(VALID_SKILL_JAVA, VALID_SKILL_PYTHON).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_someFieldsSpecified_success() {
//        Index targetIndex = INDEX_FIRST_PERSON;
//        String userInput = targetIndex.getOneBased() + GITHUB_DESC_BOB + EMAIL_DESC_AMY;
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGithub(VALID_GITHUB_BOB)
//                .withEmail(VALID_EMAIL_AMY).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_oneFieldSpecified_success() {
//        // name
//        Index targetIndex = INDEX_THIRD_PERSON;
//        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // github
//        userInput = targetIndex.getOneBased() + GITHUB_DESC_AMY;
//        descriptor = new EditPersonDescriptorBuilder().withGithub(VALID_GITHUB_AMY).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // email
//        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
//        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // address
//        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
//        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // skills
//        userInput = targetIndex.getOneBased() + SKILL_DESC_PYTHON;
//        descriptor = new EditPersonDescriptorBuilder().withSkills(VALID_SKILL_PYTHON).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_multipleRepeatedFields_acceptsLast() {
//        Index targetIndex = INDEX_FIRST_PERSON;
//        String userInput = targetIndex.getOneBased() + GITHUB_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
//                + SKILL_DESC_PYTHON + GITHUB_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + SKILL_DESC_PYTHON
//                + GITHUB_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + SKILL_DESC_JAVA;
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGithub(VALID_GITHUB_BOB)
//                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_PYTHON,
//                        VALID_SKILL_JAVA)
//                .build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_invalidValueFollowedByValidValue_success() {
//        // no other valid values specified
//        Index targetIndex = INDEX_FIRST_PERSON;
//        String userInput = targetIndex.getOneBased() + INVALID_GITHUB_DESC + GITHUB_DESC_BOB;
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGithub(VALID_GITHUB_BOB).build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // other valid values specified
//        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_GITHUB_DESC + ADDRESS_DESC_BOB
//                + GITHUB_DESC_BOB;
//        descriptor = new EditPersonDescriptorBuilder().withGithub(VALID_GITHUB_BOB).withEmail(VALID_EMAIL_BOB)
//                .withAddress(VALID_ADDRESS_BOB).build();
//        expectedCommand = new EditCommand(targetIndex, descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//
//    @Test
//    public void parse_resetSkills_success() {
//        Index targetIndex = INDEX_THIRD_PERSON;
//        String userInput = targetIndex.getOneBased() + SKILL_EMPTY;
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withSkills().build();
//        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
//}
