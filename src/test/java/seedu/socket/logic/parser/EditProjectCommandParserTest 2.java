package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.commands.CommandTestUtil.DEADLINE_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.DEADLINE_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_MEETING_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_PROJECT_NAME_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_REPO_HOST_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.INVALID_REPO_NAME_DESC;
import static seedu.socket.logic.commands.CommandTestUtil.MEETING_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.MEETING_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_HOST_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_HOST_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_NAME_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_NAME_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_BRAVO;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_THIRD_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.EditProjectCommand;
import seedu.socket.logic.commands.EditProjectCommand.EditProjectDescriptor;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;
import seedu.socket.testutil.EditProjectDescriptorBuilder;

public class EditProjectCommandParserTest {
    private static final String NAME_EMPTY = " " + PREFIX_NAME;
    private static final String REPO_HOST_EMPTY = " " + PREFIX_REPO_HOST;
    private static final String REPO_NAME_EMPTY = " " + PREFIX_REPO_NAME;
    private static final String MEETING_EMPTY = " " + PREFIX_MEETING;
    private static final String DEADLINE_EMPTY = " " + PREFIX_DEADLINE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE);
    private EditProjectCommandParser parser = new EditProjectCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditProjectCommand.MESSAGE_NOT_EDITED);

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
        // invalid name
        assertParseFailure(parser, "1" + INVALID_PROJECT_NAME_DESC, ProjectName.MESSAGE_CONSTRAINTS);
        // invalid repo host
        assertParseFailure(parser, "1" + INVALID_REPO_HOST_DESC, ProjectRepoHost.MESSAGE_CONSTRAINTS);
        // invalid repo name
        assertParseFailure(parser, "1" + INVALID_REPO_NAME_DESC, ProjectRepoName.MESSAGE_CONSTRAINTS);
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, ProjectDeadline.MESSAGE_CONSTRAINTS);
        // invalid meeting
        assertParseFailure(parser, "1" + INVALID_MEETING_DESC, ProjectMeeting.MESSAGE_CONSTRAINTS);


        // invalid repo_name followed by valid repo_host
        assertParseFailure(parser, "1" + INVALID_REPO_NAME_DESC + REPO_HOST_DESC_ALPHA,
                ProjectRepoName.MESSAGE_CONSTRAINTS);

        // valid repo_host followed by valid repo_name
        assertParseFailure(parser, "1" + REPO_HOST_DESC_ALPHA + INVALID_REPO_NAME_DESC,
                ProjectRepoName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyNonLanguageTagField_failure() {
        assertParseFailure(parser, "1" + NAME_EMPTY, ProjectName.MESSAGE_CONSTRAINTS); // empty name
        assertParseFailure(parser, "1" + REPO_HOST_EMPTY, ProjectRepoHost.MESSAGE_CONSTRAINTS); // empty profile
        assertParseFailure(parser, "1" + REPO_NAME_EMPTY, ProjectRepoName.MESSAGE_CONSTRAINTS); // empty phone
        assertParseFailure(parser, "1" + DEADLINE_EMPTY, ProjectDeadline.MESSAGE_CONSTRAINTS); // empty email
        assertParseFailure(parser, "1" + MEETING_EMPTY, ProjectMeeting.MESSAGE_CONSTRAINTS); // empty address
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROJECT;
        String userInput = targetIndex.getOneBased() + PROJECT_NAME_DESC_ALPHA + REPO_HOST_DESC_ALPHA
                + REPO_NAME_DESC_ALPHA
               + DEADLINE_DESC_ALPHA + MEETING_DESC_ALPHA;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withName(VALID_PROJECT_NAME_ALPHA)
                .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA).withRepoName(VALID_PROJECT_REPO_NAME_ALPHA)
                .withDeadline(VALID_PROJECT_DEADLINE_ALPHA)
                .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + PROJECT_NAME_DESC_ALPHA + REPO_NAME_DESC_ALPHA;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_ALPHA)
                .withRepoName(VALID_PROJECT_REPO_NAME_ALPHA).build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + PROJECT_NAME_DESC_ALPHA;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_ALPHA).build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // repo host
        userInput = targetIndex.getOneBased() + REPO_HOST_DESC_ALPHA;
        descriptor = new EditProjectDescriptorBuilder().withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // repo name
        userInput = targetIndex.getOneBased() + REPO_NAME_DESC_ALPHA;
        descriptor = new EditProjectDescriptorBuilder().withRepoName(VALID_PROJECT_REPO_NAME_ALPHA).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_ALPHA;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_PROJECT_DEADLINE_ALPHA).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // meeting
        userInput = targetIndex.getOneBased() + MEETING_DESC_ALPHA;
        descriptor = new EditProjectDescriptorBuilder().withMeeting(VALID_PROJECT_MEETING_ALPHA).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + PROJECT_NAME_DESC_ALPHA
                + REPO_HOST_DESC_ALPHA + REPO_NAME_DESC_ALPHA
                + DEADLINE_DESC_ALPHA + MEETING_DESC_ALPHA
                + PROJECT_NAME_DESC_BRAVO + REPO_HOST_DESC_BRAVO + REPO_NAME_DESC_BRAVO
                + DEADLINE_DESC_BRAVO + MEETING_DESC_BRAVO;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withName(VALID_PROJECT_NAME_BRAVO)
                .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
                .withRepoName(VALID_PROJECT_REPO_NAME_BRAVO).withDeadline(VALID_PROJECT_DEADLINE_BRAVO)
                .withMeeting(VALID_PROJECT_MEETING_BRAVO).build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
