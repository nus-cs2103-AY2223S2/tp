package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.UpdateProjectCommand;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

public class UpdateProjectCommandParserTest {
    private UpdateProjectCommandParser parser = new UpdateProjectCommandParser();

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        UpdateProjectCommand.MESSAGE_USAGE);

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingRequiredArgs_throwsParseException() {
        // required arg not given
        assertParseFailure(parser, " -dd 31/12/2023", MESSAGE_INVALID_FORMAT);
        // require arg flag present, but no value
        assertParseFailure(parser, " -pn", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        Map<String, String> tests = Map.of(
            "-pn2   ", NonEmptyString.MESSAGE_CONSTRAINTS,
            "-s notastatus", ProjectStatus.MESSAGE_CONSTRAINTS,
            "-e notanemail", Email.MESSAGE_CONSTRAINTS,
            "-src  ", NonEmptyString.MESSAGE_CONSTRAINTS,
            "-ad 31-12-2023", Messages.MESSAGE_INVALID_DATE,
            "-dd 31-12-2023", Messages.MESSAGE_INVALID_DATE
        );
        tests.forEach((tt, err) -> {
            String input = " -pn foobar " + tt;
            assertParseFailure(parser, input, err);
        });
    }

    @Test
    public void parse_requiredArgsGiven_success() {
        // Only the current name is required
        var input = " -pn foobar";
        var want = new UpdateProjectCommand(new NonEmptyString("foobar"),
            new UpdateProjectCommand.UpdateProjectDescriptor());
        assertParseSuccess(parser, input, want);
    }

    @Test
    public void parse_allArgsGiven_success() {
        // set up some test data
        var curName = "foobar";
        var name = "barfoo";
        var status = "done";
        var email = "foo@bar.com";
        var src = "github";
        var desc = "the foobar project";
        var acceptedOn = "31/12/2023";
        var deadline = "31/12/2024";

        var descriptor = new UpdateProjectCommand.UpdateProjectDescriptor();
        descriptor.setName(new NonEmptyString(name));
        descriptor.setStatus(ProjectStatus.fromString(status));
        descriptor.setClientEmail(new Email(email));
        descriptor.setSource(new NonEmptyString(src));
        descriptor.setDescription(desc);
        descriptor.setAcceptedOn(LocalDate.parse(acceptedOn, Project.DATE_FMT));
        descriptor.setDeadline(LocalDate.parse(deadline, Project.DATE_FMT));

        var input = String.format(" -pn %s -pn2 %s -s %s -e %s -src %s -d %s -ad %s -dd %s",
            curName, name, status, email, src, desc, acceptedOn, deadline);
        var want = new UpdateProjectCommand(new NonEmptyString(curName), descriptor);

        assertParseSuccess(parser, input, want);
    }

    @Test
    public void parse_invalidArg_followedByValidArg_success() {
        var descriptor = new UpdateProjectCommand.UpdateProjectDescriptor();
        descriptor.setName(new NonEmptyString("barfoo"));
        descriptor.setStatus(ProjectStatus.DONE);

        var input = " -pn foobar -pn2 barfoo -s notastatus -s done";
        var want = new UpdateProjectCommand(new NonEmptyString("foobar"), descriptor);

        assertParseSuccess(parser, input, want);
    }
}
