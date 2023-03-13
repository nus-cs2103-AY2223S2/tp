package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_ACCEPTED_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_STATUS;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

/**
 * A command to add a new project.
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProjectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_CLIENT_EMAIL,
                PREFIX_PROJECT_STATUS, PREFIX_SOURCE, PREFIX_PROJECT_DESCRIPTION,
                PREFIX_ACCEPTED_DATE, PREFIX_DEADLINE_DATE
            );

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME, PREFIX_CLIENT_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }

        // For projects, the only required field is the project name and the client's email.
        String name = ParserUtil.parseNonEmptyString(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        Email clientEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());

        // The project's status and acceptedOn date take default values.
        ProjectStatus projectStatus = ProjectStatus.NOT_STARTED; // TODO actually parse this
        Date acceptedOn = new Date(); // TODO parse this shit

        Optional<String> source = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_SOURCE),
            ParserUtil::parseNonEmptyString);
        Optional<String> description = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_PROJECT_DESCRIPTION),
            ParserUtil::parseNonEmptyString);
        Optional<Date> deadline = Optional.empty(); // TODO parse this shit

        Project project = new Project(name, projectStatus, clientEmail, source, description, acceptedOn, deadline);

        return new AddProjectCommand(project);
    }
}
