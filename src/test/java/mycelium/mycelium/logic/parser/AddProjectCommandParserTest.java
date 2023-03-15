package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseFailure;
import static mycelium.mycelium.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.testutil.Pair;
import mycelium.mycelium.testutil.ProjectBuilder;

public class AddProjectCommandParserTest {
    @Test
    public void parse_missingRequiredArgs_throwsParseException() {
        assertEquals(CliSyntax.PREFIX_PROJECT_NAME.getPrefix(), "-pn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        // For each of the following cases, we expect the parser to throw an exception.
        Map<String, String> tests = Map.ofEntries(
            Map.entry("empty string", ""),
            Map.entry("only whitespace", " "),

            Map.entry("has name prefix only, but no name", "-pn"),
            Map.entry("has name prefix only, but no name (whitespace)", "-pn "),
            Map.entry("has name prefix only, but no whitespace", "-pnBing"),

            Map.entry("has email prefix only, but no email", "-e"),
            Map.entry("has email prefix only, but no email (whitespace)", "-e "),
            Map.entry("has email prefix only, but no whitespace", "-ejamal@hogriders.org"),

            Map.entry("prefixes are not separated", "-pn-e"),
            Map.entry("no name nor email", "-pn -e"),
            Map.entry("name, but no email", "-pn Bing -e"),
            Map.entry("name and email not separated", "-pnBing-ejamal@hogriders.org")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            assertParseFailure(new AddProjectCommandParser(), input, String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddProjectCommand.MESSAGE_USAGE
            ), "While testing case: " + desc);
        });
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertEquals(CliSyntax.PREFIX_PROJECT_NAME.getPrefix(), "-pn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        String basic = "-pn Bing -e jamal@hogriders.org ";
        // For each of the following cases, we expect the parser to throw an exception.
        Map<String, Pair<String, String>> tests = Map.ofEntries(
            Map.entry("invalid name (whitespace)",
                Pair.of("-pn  -e jamal@hogriders.org", Name.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid email (whitespace)",
                Pair.of("-pn Bing -e ", Email.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid email",
                Pair.of("-pn Bing -e foobar", Email.MESSAGE_CONSTRAINTS)),
            // NOTE: no restrictions on project name (except being non-empty), so no test case for that

            Map.entry("invalid acceptedOn (not even a date)",
                Pair.of(basic + "-ad notadate", Messages.MESSAGE_INVALID_DATE)),
            Map.entry("invalid acceptedOn (wrong date format)",
                Pair.of(basic + "-ad 2020-01-01", Messages.MESSAGE_INVALID_DATE)),
            Map.entry("invalid acceptedOn (date but with time)",
                Pair.of(basic + "-ad 2020-01-01 12:00", Messages.MESSAGE_INVALID_DATE)),

            Map.entry("invalid deadline (not even a date)",
                Pair.of(basic + "-dd notadate", Messages.MESSAGE_INVALID_DATE)),
            Map.entry("invalid deadline (wrong date format)",
                Pair.of(basic + "-dd 2020-01-01", Messages.MESSAGE_INVALID_DATE)),
            Map.entry("invalid deadline (date but with time)",
                Pair.of(basic + "-dd 2020/01/01 12:00", Messages.MESSAGE_INVALID_DATE)),

            Map.entry("invalid source (empty)",
                Pair.of(basic + "-src ", Messages.MESSAGE_EMPTY_STR)),
            Map.entry("invalid description (empty)",
                Pair.of(basic + "-d ", Messages.MESSAGE_EMPTY_STR)),

            Map.entry("invalid status (empty)",
                Pair.of(basic + "-s ", ProjectStatus.MESSAGE_CONSTRAINTS)),
            Map.entry("invalid status (wrong words)",
                Pair.of(basic + "-s hog_riders", ProjectStatus.MESSAGE_CONSTRAINTS))
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            Assertions.assertThrows(ParseException.class, ()
                    -> new AddProjectCommandParser().parse(input),
                "While testing case: " + desc);
        });
    }

    @Test
    public void parse_validArgs_success() throws ParseException {
        assertEquals(CliSyntax.PREFIX_PROJECT_NAME.getPrefix(), "-pn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        AddProjectCommand addBing = new AddProjectCommand(new ProjectBuilder().withName("Bing").build());
        AddProjectCommand
            addMicrosoftBing =
            new AddProjectCommand(new ProjectBuilder().withName("Microsoft Bing").build());

        // For each of the following cases, we expect the parser to return the correct command.
        Map<String, Pair<String, AddProjectCommand>> tests = Map.ofEntries(
            Map.entry("name first, email second",
                Pair.of("-pn Bing -e jamal@hogriders.org", addBing)),
            Map.entry("email first, name second",
                Pair.of("-e jamal@hogriders.org -pn Bing", addBing)),
            Map.entry("trailing whitespace",
                Pair.of("-pn Bing -e jamal@hogriders.org  ", addBing)),

            Map.entry("multiple words in project name",
                Pair.of("-pn Microsoft Bing -e jamal@hogriders.org", addMicrosoftBing)),

            Map.entry("multiple names",
                Pair.of("-e jamal@hogriders.org -pn Bing -pn Microsoft Bing", addMicrosoftBing)),
            Map.entry("multiple emails",
                Pair.of("-pn Bing -e jamar@hogriders.org -e jamal@hogriders.org", addBing)),
            Map.entry("multiple names and emails",
                Pair.of("-pn Bing -pn Microsoft Bing -e jamar@hogriders.org -e jamal@hogriders.org", addMicrosoftBing)),

            Map.entry("all arguments present",
                Pair.of(
                    "-pn Bing -e jamal@hogriders.org -ad 01/01/1970 -dd 02/01/1970 -src Fiverr -d This is a "
                        + "description -s in_progress",
                    addBing))
        );
        for (String desc : tests.keySet()) {
            String input = " " + tests.get(desc).first;
            AddProjectCommand expected = tests.get(desc).second;
            assertParseSuccess(new AddProjectCommandParser(), input, expected,
                "While testing case: " + desc);
        }
    }
}
