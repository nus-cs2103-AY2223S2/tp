package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.commands.CommandTestUtil.DEADLINE_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_MEETING_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_REPO_HOST_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_REPO_NAME_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.MEETING_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_HOST_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_NAME_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_ALPHA;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_THIRD_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.RemoveProjectCommand;
import seedu.socket.logic.commands.RemoveProjectCommand.RemoveProjectDescriptor;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;
import seedu.socket.testutil.RemoveProjectDescriptorBuilder;

public class RemoveProjectCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveProjectCommand.MESSAGE_USAGE);

    private RemoveProjectCommandParser parser = new RemoveProjectCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PROJECT_NAME_ALPHA, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", RemoveProjectCommand.MESSAGE_NOT_REMOVE);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PROJECT_NAME_DESC_ALPHA, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PROJECT_NAME_DESC_ALPHA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid repo host
        assertParseFailure(parser, "1" + INVALID_REPO_HOST_DESC, ProjectRepoHost.MESSAGE_CONSTRAINTS);
        // invalid repo name
        assertParseFailure(parser, "1" + INVALID_REPO_NAME_DESC, ProjectRepoName.MESSAGE_CONSTRAINTS);
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, ProjectDeadline.MESSAGE_CONSTRAINTS);
        // invalid meeting
        assertParseFailure(parser, "1" + INVALID_MEETING_DESC, ProjectMeeting.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROJECT;
        String userInput = targetIndex.getOneBased() + REPO_HOST_DESC_ALPHA + REPO_NAME_DESC_ALPHA + DEADLINE_DESC_ALPHA
                + MEETING_DESC_ALPHA;

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA).withRepoName(VALID_PROJECT_REPO_NAME_ALPHA)
                .withDeadline(VALID_PROJECT_DEADLINE_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();
        RemoveProjectCommand expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + MEETING_DESC_ALPHA + DEADLINE_DESC_ALPHA;

        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withDeadline(VALID_PROJECT_DEADLINE_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();
        RemoveProjectCommand expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // repo host
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + REPO_HOST_DESC_ALPHA;
        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA).build();
        RemoveProjectCommand expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // repo name
        userInput = targetIndex.getOneBased() + REPO_NAME_DESC_ALPHA;
        descriptor = new RemoveProjectDescriptorBuilder()
                .withRepoName(VALID_PROJECT_REPO_NAME_ALPHA).build();
        expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_ALPHA;
        descriptor = new RemoveProjectDescriptorBuilder()
                .withDeadline(VALID_PROJECT_DEADLINE_ALPHA).build();
        expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // meeting
        userInput = targetIndex.getOneBased() + MEETING_DESC_ALPHA;
        descriptor = new RemoveProjectDescriptorBuilder()
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();
        expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + INVALID_REPO_HOST_DESC + REPO_HOST_DESC_ALPHA;
        RemoveProjectDescriptor descriptor = new RemoveProjectDescriptorBuilder()
                .withRepoName(VALID_PROJECT_REPO_HOST_ALPHA).build();
        RemoveProjectCommand expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_REPO_HOST_DESC + MEETING_DESC_ALPHA + REPO_HOST_DESC_ALPHA;
        descriptor = new RemoveProjectDescriptorBuilder().withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();
        expectedCommand = new RemoveProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

