package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.model.project.Project.PROJ_DEADLINE;
import static seedu.socket.model.project.Project.PROJ_NAME;
import static seedu.socket.model.project.Project.PROJ_REPO_HOST;
import static seedu.socket.model.project.Project.PROJ_REPO_NAME;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.SortProjectCommand;

class SortProjectCommandParserTest {

    private SortProjectCommandParser parser = new SortProjectCommandParser();

    @Test
    public void parse_emptyArg_returnsSortProjectCommand() {
        SortProjectCommand expectedSortProjectCommand = new SortProjectCommand(PROJ_DEADLINE);
        assertParseSuccess(parser, "", expectedSortProjectCommand);
    }

    @Test
    public void parse_name_returnsSortNameCommand() {
        //no leading and trailing whitespaces
        SortProjectCommand expectedSortProjectCommand = new SortProjectCommand(PROJ_NAME);
        assertParseSuccess(parser, PROJ_NAME, expectedSortProjectCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  " + PROJ_NAME + "   ", expectedSortProjectCommand);
    }

    @Test
    public void parse_phone_returnsSortDeadlineCommand() {
        //no leading and trailing whitespaces
        SortProjectCommand expectedSortProjectCommand = new SortProjectCommand(PROJ_DEADLINE);
        assertParseSuccess(parser, PROJ_DEADLINE, expectedSortProjectCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  " + PROJ_DEADLINE + "  ", expectedSortProjectCommand);
    }

    @Test
    public void parse_email_returnsSortRepoHostCommand() {
        //no leading and trailing whitespaces
        SortProjectCommand expectedSortProjectCommand = new SortProjectCommand(PROJ_REPO_HOST);
        assertParseSuccess(parser, PROJ_REPO_HOST, expectedSortProjectCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  " + PROJ_REPO_HOST + "  ", expectedSortProjectCommand);
    }

    @Test
    public void parse_email_returnsSortRepoNameCommand() {
        //no leading and trailing whitespaces
        SortProjectCommand expectedSortProjectCommand = new SortProjectCommand(PROJ_REPO_NAME);
        assertParseSuccess(parser, PROJ_REPO_NAME, expectedSortProjectCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  " + PROJ_REPO_NAME + "  ", expectedSortProjectCommand);
    }

    @Test
    public void parse_invalidCategory_throwsParseException() {
        assertParseFailure(parser, "sort invalid",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortProjectCommand.MESSAGE_USAGE));
    }
}
