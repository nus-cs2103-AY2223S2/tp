package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.task.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.task.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOTH;
import static seedu.task.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.task.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.task.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.task.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.task.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.task.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.task.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.task.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.task.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_EFFORT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.task.testutil.TypicalDeadlines.ASSIGNMENT;
import static seedu.task.testutil.TypicalDeadlines.RETURN_BOOK;
import static seedu.task.testutil.TypicalEvents.MEETING;
import static seedu.task.testutil.TypicalEvents.REST;
import static seedu.task.testutil.TypicalTasks.AMY;
import static seedu.task.testutil.TypicalTasks.AMY_BOTH;
import static seedu.task.testutil.TypicalTasks.BOB;
import static seedu.task.testutil.TypicalTasks.BOB_BOTH;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.AddCommand;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Deadline;
import seedu.task.model.task.Description;
import seedu.task.model.task.Event;
import seedu.task.model.task.Name;
import seedu.task.model.task.Task;
import seedu.task.testutil.DeadlineBuilder;
import seedu.task.testutil.EventBuilder;
import seedu.task.testutil.SimpleTaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parseSimpleTask_allFieldsPresent_success() {
        Task expectedTask = new SimpleTaskBuilder(BOB)
                .withTags(VALID_TAG_FRIEND)
                .build();
        Task expectedBobBoth = new SimpleTaskBuilder(BOB_BOTH)
                .withTags(VALID_TAG_FRIEND)
                .build();
        Task expectedAmyBoth = new SimpleTaskBuilder(AMY_BOTH)
                .withTags(VALID_TAG_FRIEND)
                .build();
        ArrayList<Task> multiNameTasks = new ArrayList<>();
        multiNameTasks.add(expectedAmyBoth);
        multiNameTasks.add(expectedBobBoth);


        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DESCRIPTION_DESC_BOTH
                + TAG_DESC_FRIEND, new AddCommand(multiNameTasks));

        // multiple description - last description accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new SimpleTaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parseDeadline_allFieldsPresent_success() {
        Deadline expectedDeadline = new DeadlineBuilder(RETURN_BOOK)
                .build();
        Task expectedAssignment = new DeadlineBuilder(ASSIGNMENT)
                .build();
        ArrayList<Task> multiNameTasks = new ArrayList<>();
        multiNameTasks.add(expectedAssignment);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + "Return Book "
                + PREFIX_DESCRIPTION + "Return book description " + PREFIX_TAG + "Reminder "
                + PREFIX_DEADLINE + "2023-01-01 1800 " + PREFIX_EFFORT + "1", new AddCommand(expectedDeadline));
    }

    @Test
    public void parseEvent_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(MEETING)
                .build();
        Task expectedRest = new EventBuilder(REST)
                .build();
        ArrayList<Task> multiNameTasks = new ArrayList<>();
        multiNameTasks.add(expectedRest);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + "Meeting "
                + PREFIX_DESCRIPTION + "Meeting description " + PREFIX_TAG + "Reminder "
                + PREFIX_FROM + "2023-01-01 1800 " + PREFIX_TO + "2023-01-02 1800 " + PREFIX_EFFORT + "5",
                new AddCommand(expectedEvent));
    }

    @Test
    public void parseSimpleTask_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new SimpleTaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DESCRIPTION_DESC_AMY,
                new AddCommand(expectedTask));
    }

    @Test
    public void parseSimpleTask_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DESCRIPTION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DESCRIPTION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DESCRIPTION_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
