package taa.logic.parser;

import org.junit.jupiter.api.Test;

import taa.commons.core.Messages;
import taa.logic.commands.AddStudentCommand;
import taa.logic.commands.CommandTestUtil;
import taa.model.student.Name;
import taa.model.student.Student;
import taa.model.tag.Tag;
import taa.testutil.PersonBuilder;
import taa.testutil.TypicalPersons;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new PersonBuilder(TypicalPersons.BOB)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new PersonBuilder(TypicalPersons.BOB).withTags(
                CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, new AddStudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new PersonBuilder(TypicalPersons.AMY).withTags().build();
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY,
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB,
                expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid tag
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND
                        + CommandTestUtil.TAG_DESC_FRIEND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
