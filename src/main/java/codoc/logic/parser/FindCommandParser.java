package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_FIND_SKILL_NO_ARGUMENT;
import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Arrays;

import codoc.logic.commands.FindCommand;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.NameContainsKeywordsPredicate;
import codoc.model.skill.SkillContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SKILL);

        if (argMultimap.getValue(PREFIX_SKILL).isPresent()) {
            if (argMultimap.getAllValues(PREFIX_SKILL).get(0).isEmpty()) {
                throw new ParseException(MESSAGE_FIND_SKILL_NO_ARGUMENT);
            }
            return new FindCommand(new SkillContainsKeywordPredicate(argMultimap.getAllValues(PREFIX_SKILL)));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
