package mycelium.mycelium.logic.parser;

import static mycelium.mycelium.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static mycelium.mycelium.logic.parser.CliSyntax.*;


import java.util.Set;
import java.util.stream.Stream;
import java.util.Date;
import java.util.Optional;

import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.commands.AddCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Address;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.person.Phone;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_CLIENT_NAME,
                        PREFIX_ACCEPTED_DATE, PREFIX_DEADLINE_DATE, PREFIX_PROJECT_DESCRIPTION,
                        PREFIX_PROJECT_STATUS, PREFIX_SOURCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_NAME, PREFIX_PROJECT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_PROJECT_NAME).get();
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());
        ProjectStatus projectStatus = ParserUtil.parseProjectStatus(argMultimap.getValue(PREFIX_PROJECT_STATUS).get());
        Optional<String> source = Optional.of(argMultimap.getValue(PREFIX_SOURCE).get());
        Optional<String> description = Optional.of(argMultimap.getValue(PREFIX_PROJECT_DESCRIPTION).get());
        Date acceptedDate = new Date(argMultimap.getValue(PREFIX_ACCEPTED_DATE).get());
        Optional<Date> deadlineDate = Optional.of(new Date(argMultimap.getValue(PREFIX_DEADLINE_DATE).get()));


        Project project = new Project(name, projectStatus, email, source, description, acceptedDate, deadlineDate);

        return new AddProjectCommand(project);
    }
}
