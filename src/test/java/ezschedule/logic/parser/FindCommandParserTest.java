package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static ezschedule.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ezschedule.logic.commands.CommandTestUtil.NAME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.NAME_DESC_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static ezschedule.logic.parser.CliSyntax.PREFIX_DATE;
import static ezschedule.logic.parser.CliSyntax.PREFIX_NAME;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.FindCommand;
import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.model.event.Date;
import ezschedule.model.event.Name;
import ezschedule.testutil.FindEventDescriptorBuilder;

public class FindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryPrefixesMissing_failure() {
        // missing name prefix
        assertParseFailure(parser, VALID_NAME_A, MESSAGE_INVALID_FORMAT);

        // missing date prefix
        assertParseFailure(parser, VALID_DATE_A, MESSAGE_INVALID_FORMAT);

        // missing name and date prefixes
        assertParseFailure(parser, VALID_NAME_A + VALID_DATE_A, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryFieldsEmpty_failure() {
        // missing name field
        assertParseFailure(parser, " " + PREFIX_NAME, Name.MESSAGE_CONSTRAINTS);

        // missing date field
        assertParseFailure(parser, " " + PREFIX_DATE, Date.MESSAGE_CONSTRAINTS);

        // multiple args parsed - missing name field
        assertParseFailure(parser, " " + PREFIX_NAME + DATE_DESC_A, Name.MESSAGE_CONSTRAINTS);

        // multiple args parsed - missing date field
        assertParseFailure(parser, NAME_DESC_A + " " + PREFIX_DATE, Date.MESSAGE_CONSTRAINTS);

        // multiple args parsed - all fields missing, throws first occurrence
        assertParseFailure(parser, " " + PREFIX_NAME + " " + PREFIX_DATE, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid name followed by valid date
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_A, Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid date
        assertParseFailure(parser, NAME_DESC_A + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_DATE_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = NAME_DESC_A + DATE_DESC_A;
        FindEventDescriptor descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_A)
                .withDate(VALID_DATE_A).build();
        FindCommand expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_A;
        FindEventDescriptor descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_A).build();
        FindCommand expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = DATE_DESC_A;
        descriptor = new FindEventDescriptorBuilder().withDate(VALID_DATE_A).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = NAME_DESC_A + NAME_DESC_B + DATE_DESC_A + DATE_DESC_B;
        FindEventDescriptor descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_B)
                .withDate(VALID_DATE_B).build();
        FindCommand expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // invalid name followed by valid name
        String userInput = INVALID_NAME_DESC + NAME_DESC_A;
        FindEventDescriptor descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_A).build();
        FindCommand expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid date followed by valid date
        userInput = INVALID_DATE_DESC + DATE_DESC_A;
        descriptor = new FindEventDescriptorBuilder().withDate(VALID_DATE_A).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // valid name, and invalid date followed by valid date
        userInput = NAME_DESC_A + INVALID_DATE_DESC + DATE_DESC_A;
        descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_A).withDate(VALID_DATE_A).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid name followed by valid name, and valid date
        userInput = INVALID_NAME_DESC + NAME_DESC_A + DATE_DESC_A;
        descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_A).withDate(VALID_DATE_A).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid name followed by valid name, and invalid date followed by valid date
        userInput = INVALID_NAME_DESC + NAME_DESC_A + INVALID_DATE_DESC + DATE_DESC_A;
        descriptor = new FindEventDescriptorBuilder().withName(VALID_NAME_A).withDate(VALID_DATE_A).build();
        expectedCommand = new FindCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
