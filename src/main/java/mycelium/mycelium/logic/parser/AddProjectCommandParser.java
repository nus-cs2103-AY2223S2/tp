package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_ACCEPTED_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_STATUS;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.time.LocalDate;
import java.util.Optional;

import mycelium.mycelium.commons.util.DateUtil;
import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * A command to add a new project.
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {
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

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME, PREFIX_CLIENT_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }

        // For projects, the only required field is the project name and the client's email.
        NonEmptyString name = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        Email clientEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());

        // The project's status and acceptedOn date take default values.
        Optional<String> maybeProjectStatus = argMultimap.getValue(PREFIX_PROJECT_STATUS);
        ProjectStatus projectStatus = maybeProjectStatus.isPresent()
            ? ParserUtil.parseProjectStatus(maybeProjectStatus.get())
            : ProjectStatus.NOT_STARTED;
        Optional<String> maybeAcceptedOn = argMultimap.getValue(PREFIX_ACCEPTED_DATE);
        LocalDate acceptedOn = maybeAcceptedOn.isPresent()
            ? ParserUtil.parseLocalDate(maybeAcceptedOn.get(), DateUtil.DATE_FMT)
            : LocalDate.now();

        Optional<NonEmptyString> source = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_SOURCE),
            ParserUtil::parseSource);
        // NOTE: the description is allowed to be empty. Thus we do not wrap it into a NonEmptyString.
        Optional<String> description = argMultimap.getValue(PREFIX_PROJECT_DESCRIPTION);
        Optional<LocalDate> deadline = ParserUtil.parseOptionalWith(
            argMultimap.getValue(PREFIX_DEADLINE_DATE),
            d -> ParserUtil.parseLocalDate(d, DateUtil.DATE_FMT));

        Project
            project =
            new Project(name,
                projectStatus,
                clientEmail,
                source,
                description,
                acceptedOn,
                deadline);

        return new AddProjectCommand(project);
    }
}
