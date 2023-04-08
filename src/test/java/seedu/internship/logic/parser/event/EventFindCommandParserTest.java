package seedu.internship.logic.parser.event;

import org.junit.jupiter.api.Test;
import seedu.internship.logic.commands.event.EventFindCommand;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;
import seedu.internship.testutil.FilterInternshipDescriptorBuilder;

import java.time.LocalDateTime;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.*;
import static seedu.internship.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internship.testutil.TypicalIndexes.*;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

public class EventFindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventFindCommand.MESSAGE_USAGE);

    private EventFindCommandParser parser = new EventFindCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // missing all fields
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_START_DESC, Start.MESSAGE_CONSTRAINTS); // invalid start
        assertParseFailure(parser, INVALID_END_DESC, End.MESSAGE_CONSTRAINTS); // invalid end


        // invalid name followed by valid start
        assertParseFailure(parser, INVALID_NAME_DESC + START_DESC_EM11, Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name.
        assertParseFailure(parser, NAME_DESC_EM11 + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_END_DESC + INVALID_START_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = NAME_DESC_EM11 + START_DESC_EM11 + END_DESC_EM11;

        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name(VALID_NAME_EM11));
        descriptor.setStart(new Start(LocalDateTime.parse(VALID_START_EM11,
                Start.NUMERIC_DATE_TIME_FORMATTER)));
        descriptor.setEnd(new End(LocalDateTime.parse(VALID_END_EM11,
                End.NUMERIC_DATE_TIME_FORMATTER)));
        EventFindCommand expectedCommand = new EventFindCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = " na/Interview";

        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("Interview"));
        EventFindCommand expectedCommand = new EventFindCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

//    @Test
//    public void parse_oneFieldSpecified_success() {
//        // position
//        String userInput = POSITION_DESC_ML1;
//        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
//                .withPosition(VALID_POSITION_ML1).build();
//        FindCommand expectedCommand = new FindCommand(descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // company
//        userInput = COMPANY_DESC_ML1;
//        descriptor = new FilterInternshipDescriptorBuilder().withCompany(VALID_COMPANY_ML1).build();
//        expectedCommand = new FindCommand(descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // status
//        userInput = STATUS_DESC_ML1;
//        descriptor = new FilterInternshipDescriptorBuilder().withStatus(VALID_STATUS_ML1).build();
//        expectedCommand = new FindCommand(descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
////        // description
////        userInput = DESCRIPTION_DESC_ML1;
////        descriptor = new FilterInternshipDescriptorBuilder().withDescription(VALID_DESCRIPTION_ML1).build();
////        expectedCommand = new FindCommand(descriptor);
////        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // tags
//        userInput = TAG_DESC_FUN;
//        descriptor = new FilterInternshipDescriptorBuilder().withTags(VALID_TAG_FUN).build();
//        expectedCommand = new FindCommand(descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }

//    @Test
//    public void parse_multipleRepeatedFields_acceptsLast() {
//        String userInput = COMPANY_DESC_ML1 + STATUS_DESC_ML1 + DESCRIPTION_DESC_ML1
//                + TAG_DESC_FUN + COMPANY_DESC_ML1 + STATUS_DESC_ML1 + DESCRIPTION_DESC_ML1 + TAG_DESC_FUN
//                + COMPANY_DESC_SE1 + STATUS_DESC_SE1 + DESCRIPTION_DESC_SE1 + TAG_DESC_IMPORTANT;
//
//        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
//                .withCompany(VALID_COMPANY_SE1)
//                .withStatus(VALID_STATUS_SE1)
//                .withDescription(VALID_DESCRIPTION_SE1)
//                .withTags(VALID_TAG_FUN, VALID_TAG_IMPORTANT)
//                .build();
//        FindCommand expectedCommand = new FindCommand(descriptor);
//
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }

//    @Test
//    public void parse_invalidValueFollowedByValidValue_success() {
//        // no other valid values specified
//        String userInput = INVALID_STATUS_DESC + STATUS_DESC_SE1;
//        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder()
//                .withStatus(VALID_STATUS_SE1).build();
//        FindCommand expectedCommand = new FindCommand(descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // other valid values specified
//        userInput = COMPANY_DESC_SE1 + INVALID_STATUS_DESC + DESCRIPTION_DESC_SE1
//                + STATUS_DESC_SE1;
//        descriptor = new FilterInternshipDescriptorBuilder().withCompany(VALID_COMPANY_SE1).withStatus(VALID_STATUS_SE1)
//                .withDescription(VALID_DESCRIPTION_SE1).build();
//        expectedCommand = new FindCommand(descriptor);
//        assertParseSuccess(parser, userInput, expectedCommand);
//    }
}

