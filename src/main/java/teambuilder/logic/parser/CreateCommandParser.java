package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TAG;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TEAMDESC;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import java.util.Set;
import java.util.stream.Stream;

import teambuilder.logic.commands.CreateCommand;
import teambuilder.logic.parser.exceptions.ParseException;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateCommand
     * and returns an CreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEAMNAME, PREFIX_TEAMDESC, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAMNAME, PREFIX_TEAMDESC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        TeamName teamName = ParserUtil.parseTeamName(argMultimap.getValue(PREFIX_TEAMNAME).get());
        Desc teamDesc = ParserUtil.parseTeamDesc(argMultimap.getValue(PREFIX_TEAMDESC).get());
        Set<Tag> skillTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Team team = new Team(teamName, teamDesc, skillTags);

        return new CreateCommand(team);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
