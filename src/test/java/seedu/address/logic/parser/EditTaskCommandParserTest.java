package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.util.TypicalPersons.NAME_DESC_AMY;
import static seedu.address.model.util.TypicalPersons.VALID_NAME_AMY;
import static seedu.address.model.util.TypicalTasks.INVALID_CONTENT;
import static seedu.address.model.util.TypicalTasks.INVALID_DESC_CONTENT_EMAIL;
import static seedu.address.model.util.TypicalTasks.INVALID_DESC_TITLE_EMAIL;
import static seedu.address.model.util.TypicalTasks.INVALID_STATUS;
import static seedu.address.model.util.TypicalTasks.INVALID_TITLE;
import static seedu.address.model.util.TypicalTasks.VALID_CONTENT_SEND_EMAIL;
import static seedu.address.model.util.TypicalTasks.VALID_CONTENT_SUBMIT_REPORT;
import static seedu.address.model.util.TypicalTasks.VALID_DESC_CONTENT_EMAIL;
import static seedu.address.model.util.TypicalTasks.VALID_DESC_CONTENT_REPORT;
import static seedu.address.model.util.TypicalTasks.VALID_DESC_STATUS_DONE;
import static seedu.address.model.util.TypicalTasks.VALID_DESC_STATUS_UNDONE;
import static seedu.address.model.util.TypicalTasks.VALID_DESC_TITLE_EMAIL;
import static seedu.address.model.util.TypicalTasks.VALID_DESC_TITLE_REPORT;
import static seedu.address.model.util.TypicalTasks.VALID_STATUS_DONE;
import static seedu.address.model.util.TypicalTasks.VALID_STATUS_UNDONE;
import static seedu.address.model.util.TypicalTasks.VALID_TITLE_SEND_EMAIL;
import static seedu.address.model.util.TypicalTasks.VALID_TITLE_SUBMIT_REPORT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Title;
import seedu.address.testutil.EditTaskDescriptorBuilder;


public class EditTaskCommandParserTest {
    private static final String TAG_EMPTY = " ";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private final EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
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
        assertParseFailure(parser, "1" + INVALID_TITLE, Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + INVALID_CONTENT, Content.MESSAGE_CONSTRAINTS); // invalid content
        assertParseFailure(parser, "1" + INVALID_STATUS, Status.MESSAGE_CONSTRAINTS); // invalid email

        // invalid title followed by valid content
        assertParseFailure(parser, "1" + INVALID_TITLE + VALID_DESC_CONTENT_EMAIL, Title.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid content.
        assertParseFailure(parser, "1" + VALID_DESC_TITLE_EMAIL + INVALID_CONTENT, Content.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE + INVALID_CONTENT + INVALID_STATUS,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + VALID_DESC_TITLE_EMAIL + VALID_DESC_CONTENT_EMAIL
                + VALID_DESC_STATUS_DONE;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SEND_EMAIL)
                .withContent(VALID_CONTENT_SEND_EMAIL).withStatus(VALID_STATUS_DONE).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + VALID_DESC_TITLE_EMAIL + VALID_DESC_CONTENT_EMAIL;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SEND_EMAIL).withContent(VALID_CONTENT_SEND_EMAIL).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + VALID_DESC_TITLE_EMAIL;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SEND_EMAIL).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // content
        userInput = targetIndex.getOneBased() + VALID_DESC_CONTENT_EMAIL;
        descriptor = new EditTaskDescriptorBuilder()
                .withContent(VALID_CONTENT_SEND_EMAIL).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + VALID_DESC_STATUS_DONE;
        descriptor = new EditTaskDescriptorBuilder()
                .withStatus(VALID_STATUS_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + VALID_DESC_TITLE_EMAIL + VALID_DESC_CONTENT_EMAIL
                + VALID_DESC_STATUS_DONE + VALID_DESC_TITLE_EMAIL + VALID_DESC_CONTENT_EMAIL
                + VALID_DESC_STATUS_DONE + VALID_DESC_TITLE_REPORT + VALID_DESC_CONTENT_REPORT
                + VALID_DESC_STATUS_UNDONE;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SUBMIT_REPORT)
                .withContent(VALID_CONTENT_SUBMIT_REPORT)
                .withStatus(VALID_STATUS_UNDONE)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_DESC_TITLE_EMAIL + VALID_DESC_TITLE_EMAIL;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SEND_EMAIL).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + VALID_DESC_TITLE_EMAIL + INVALID_DESC_CONTENT_EMAIL
                + VALID_DESC_STATUS_UNDONE + VALID_DESC_CONTENT_EMAIL;
        descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SEND_EMAIL)
                .withContent(VALID_CONTENT_SEND_EMAIL)
                .withStatus(VALID_STATUS_UNDONE)
                .build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
