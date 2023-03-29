package mycelium.mycelium.logic.parser;

import static java.util.Objects.requireNonNull;
import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_ACCEPTED_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_NEW_PROJECT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_STATUS;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;
import static mycelium.mycelium.logic.parser.ParserUtil.parseOptionalWith;

import mycelium.mycelium.logic.commands.UpdateProjectCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * A command to update an existing project.
 */
public class UpdateProjectCommandParser implements Parser<UpdateProjectCommand> {
    @Override
    public UpdateProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
            PREFIX_PROJECT_NAME,
            PREFIX_NEW_PROJECT_NAME,
            PREFIX_PROJECT_STATUS,
            PREFIX_CLIENT_EMAIL,
            PREFIX_SOURCE,
            PREFIX_PROJECT_DESCRIPTION,
            PREFIX_ACCEPTED_DATE,
            PREFIX_DEADLINE_DATE
        );

        // Only the project's current name is required
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateProjectCommand.MESSAGE_USAGE));
        }

        // NOTE: the use of type inference here helps reduce visual clutter!!!
        var newName = parseOptionalWith(argMultimap.getValue(PREFIX_NEW_PROJECT_NAME), ParserUtil::parseProjectName);
        var status = parseOptionalWith(argMultimap.getValue(PREFIX_PROJECT_STATUS), ParserUtil::parseProjectStatus);
        var clientEmail = parseOptionalWith(argMultimap.getValue(PREFIX_CLIENT_EMAIL), ParserUtil::parseEmail);
        var source = parseOptionalWith(argMultimap.getValue(PREFIX_SOURCE), ParserUtil::parseSource);
        var description = argMultimap.getValue(PREFIX_PROJECT_DESCRIPTION);
        var acceptedOn = parseOptionalWith(argMultimap.getValue(PREFIX_ACCEPTED_DATE),
            s -> ParserUtil.parseLocalDate(s, Project.DATE_FMT));
        var deadline = parseOptionalWith(argMultimap.getValue(PREFIX_DEADLINE_DATE),
            s -> ParserUtil.parseLocalDate(s, Project.DATE_FMT));

        UpdateProjectCommand.UpdateProjectDescriptor desc = new UpdateProjectCommand.UpdateProjectDescriptor();
        desc.setName(newName);
        desc.setStatus(status);
        desc.setClientEmail(clientEmail);
        desc.setSource(source);
        desc.setDescription(description);
        desc.setAcceptedOn(acceptedOn);
        desc.setDeadline(deadline);

        NonEmptyString currentName = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get());

        return new UpdateProjectCommand(currentName, desc);
    }
}
