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
import static seedu.socket.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.socket.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.socket.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_HOST_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_HOST_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_NAME_DESC_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.REPO_NAME_DESC_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_BRAVO;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalProjects.BRAVO;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.AddProjectCommand;
import seedu.socket.model.project.Project;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;
import seedu.socket.testutil.ProjectBuilder;

public class AddProjectCommandParserTest {
    private AddProjectCommandParser parser = new AddProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(BRAVO).withMembers(new HashSet<>()).build();

        // whitespace only preamble
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE
                        + PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_BRAVO,
                new AddProjectCommand(expectedProject));

        // multiple project names - last name accepted
        assertParseSuccess(
                parser,
                PROJECT_NAME_DESC_ALPHA
                        + PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_BRAVO,
                new AddProjectCommand(expectedProject));

        // multiple repo host - last repo host accepted
        assertParseSuccess(
                parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_ALPHA
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_BRAVO,
                new AddProjectCommand(expectedProject));

        // multiple repo name - last repo name accepted
        assertParseSuccess(
                parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_ALPHA
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_BRAVO,
                new AddProjectCommand(expectedProject));

        // multiple deadline - last deadline accepted
        assertParseSuccess(
                parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_ALPHA
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_BRAVO,
                new AddProjectCommand(expectedProject));

        // multiple meeting - last meeting accepted
        assertParseSuccess(
                parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_ALPHA
                        + MEETING_DESC_BRAVO,
                new AddProjectCommand(expectedProject));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero meeting
        Project expectedProject = new ProjectBuilder(BRAVO).withProjectMeeting("").withMembers(new HashSet<>()).build();
        assertParseSuccess(
                parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO,
                new AddProjectCommand(expectedProject));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);

        // missing project name prefix
        assertParseFailure(parser,
                VALID_PROJECT_NAME_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO,
                expectedMessage);

        // missing repo host prefix
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + VALID_PROJECT_REPO_HOST_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO,
                expectedMessage);

        // missing repo name prefix
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + VALID_PROJECT_REPO_NAME_BRAVO
                        + DEADLINE_DESC_BRAVO,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + VALID_PROJECT_DEADLINE_BRAVO,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_PROJECT_NAME_BRAVO
                        + VALID_PROJECT_REPO_HOST_BRAVO
                        + VALID_PROJECT_REPO_NAME_BRAVO
                        + VALID_PROJECT_DEADLINE_BRAVO,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project name
        assertParseFailure(parser,
                INVALID_PROJECT_NAME_DESC
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO,
                ProjectName.MESSAGE_CONSTRAINTS);

        // invalid repo host
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + INVALID_REPO_HOST_DESC
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO,
                ProjectRepoHost.MESSAGE_CONSTRAINTS);

        // invalid repo name
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + INVALID_REPO_NAME_DESC
                        + DEADLINE_DESC_BRAVO,
                ProjectRepoName.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + INVALID_DEADLINE_DESC,
                ProjectDeadline.MESSAGE_CONSTRAINTS);

        // invalid meeting
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + INVALID_MEETING_DESC,
                ProjectMeeting.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + PROJECT_NAME_DESC_BRAVO
                        + REPO_HOST_DESC_BRAVO
                        + REPO_NAME_DESC_BRAVO
                        + DEADLINE_DESC_BRAVO
                        + MEETING_DESC_BRAVO,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
    }
}
