package mycelium.mycelium.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
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
            Map.entry("no name nor email (whitespace)", "-pn  -e "),
            Map.entry("email, but no name", "-pn -e jamal@hogriders.org"),
            Map.entry("name, but no email", "-pn Bing -e"),
            Map.entry("name, but email is whitespace", "-pn Bing -e "),
            Map.entry("email, but name is whitespace", "-pn  -e jamal@hogriders.org"),
            Map.entry("name and email not separated", "-pnBing-ejamal@hogriders.org")
        );
        tests.forEach((desc, tt) -> {
            String input = " " + tt;
            Assertions.assertThrows(ParseException.class, ()
                    -> new AddProjectCommandParser().parse(input),
                "While testing case: " + desc);
        });
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertEquals(CliSyntax.PREFIX_PROJECT_NAME.getPrefix(), "-pn ");
        assertEquals(CliSyntax.PREFIX_CLIENT_EMAIL.getPrefix(), "-e ");

        String basic = "-pn Bing -e jamal@hogriders.org ";
        // For each of the following cases, we expect the parser to throw an exception.
        Map<String, String> tests = Map.ofEntries(
            Map.entry("invalid email", "-pn Bing -e foobar"),
            // NOTE: no restrictions on project name (except being non-empty), so no test case for that

            Map.entry("invalid acceptedOn (not even a date)", basic + "-ad notadate"),
            Map.entry("invalid acceptedOn (wrong date format)", basic + "-ad 2020-01-01"),
            Map.entry("invalid acceptedOn (date but with time)", basic + "-ad 2020-01-01 12:00"),

            Map.entry("invalid deadline (not even a date)", basic + "-dd notadate"),
            Map.entry("invalid deadline (wrong date format)", basic + "-dd 2020-01-01"),
            Map.entry("invalid deadline (date but with time)", basic + "-dd 2020-01-01 12:00"),

            Map.entry("invalid source (empty)", basic + "-src "),
            Map.entry("invalid description (empty)", basic + "-d ")
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
            Map.entry("name first, email second", Pair.of("-pn Bing -e jamal@hogriders.org", addBing)),
            Map.entry("email first, name second", Pair.of("-e jamal@hogriders.org -pn Bing", addBing)),
            Map.entry("trailing whitespace", Pair.of("-pn Bing -e jamal@hogriders.org  ", addBing)),

            Map.entry("multiple words in project name",
                Pair.of("-pn Microsoft Bing -e jamal@hogriders.org", addMicrosoftBing)),

            // TODO tests for status
            Map.entry("multiple names",
                Pair.of("-e jamal@hogriders.org -pn Bing -pn Microsoft Bing", addMicrosoftBing)),
            Map.entry("multiple emails", Pair.of("-pn Bing -e jamar@hogriders.org -e jamal@hogriders.org", addBing)),
            Map.entry("multiple names and emails",
                Pair.of("-pn Bing -pn Microsoft Bing -e jamar@hogriders.org -e jamal@hogriders.org", addMicrosoftBing)),

            // TODO add the status arg
            Map.entry("all arguments present",
                Pair.of(
                    "-pn Bing -e jamal@hogriders.org -ad 01/01/1970 -dd 02/01/1970 -src Fiverr -d This is a "
                        + "description",
                    addBing))
        );
        for (String desc : tests.keySet()) {
            String input = " " + tests.get(desc).first;
            AddProjectCommand expected = tests.get(desc).second;
            assertEquals(expected, new AddProjectCommandParser().parse(input),
                "While testing case: " + desc);
        }
    }
}
