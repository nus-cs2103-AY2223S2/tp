package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.socket.logic.commands.AddProjectCommand;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;

/**
 * Parses input arguments and creates a new AddProjectCommand object
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @SuppressWarnings("checkstyle:Regexp")
    public AddProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_REPO_HOST, PREFIX_REPO_NAME, PREFIX_DEADLINE, PREFIX_MEETING);

        boolean isRequiredPrefixesPresent = arePrefixesPresent(argMultimap, PREFIX_NAME)
                && arePrefixesPresent(argMultimap, PREFIX_REPO_HOST)
                && arePrefixesPresent(argMultimap, PREFIX_REPO_NAME)
                && arePrefixesPresent(argMultimap, PREFIX_DEADLINE);

        if (!isRequiredPrefixesPresent
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }
        assert isRequiredPrefixesPresent == true;

        ProjectName projectName = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_NAME).get());
        ProjectRepoHost projectRepoHost = ParserUtil.parseRepoHost(argMultimap.getValue(PREFIX_REPO_HOST).get());
        ProjectRepoName projectRepoName = ParserUtil.parseRepoName(argMultimap.getValue(PREFIX_REPO_NAME).get());
        ProjectDeadline projectDeadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        ProjectMeeting projectMeeting = ParserUtil.parseMeeting(argMultimap.getValue(PREFIX_MEETING).orElse(""));
        Set<Person> personList = new HashSet<>();

        Project project = new Project(
                projectName, projectRepoHost, projectRepoName, projectDeadline, projectMeeting, personList);

        return new AddProjectCommand(project);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
