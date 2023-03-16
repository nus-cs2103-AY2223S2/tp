package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Arrays;
import java.util.function.Predicate;

import codoc.logic.commands.FindCommand;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.CourseContainsKeywordsPredicate;
import codoc.model.person.NameContainsKeywordsPredicate;
import codoc.model.person.Person;
import codoc.model.person.YearContainsKeywordsPredicate;
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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_NAME,
                        PREFIX_YEAR,
                        PREFIX_COURSE,
                        PREFIX_SKILL
                );

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String nameArgs = argMultimap.getValue(PREFIX_NAME).orElseGet(() -> "").trim();
        String yearArgs = argMultimap.getValue(PREFIX_YEAR).orElseGet(() -> "").trim();
        String courseArgs = argMultimap.getValue(PREFIX_COURSE).orElseGet(() -> "").trim();
        String skillArgs = argMultimap.getValue(PREFIX_SKILL).orElseGet(() -> "").trim();

        String[] nameKeywords = !nameArgs.isEmpty() ? nameArgs.split("\\s+") : new String[0];
        String[] yearKeywords = !yearArgs.isEmpty() ? yearArgs.split("\\s+") : new String[0];
        String[] courseKeywords = !courseArgs.isEmpty() ? courseArgs.split("\\s+") : new String[0];
        String[] skillKeywords = !skillArgs.isEmpty() ? skillArgs.split("\\s+") : new String[0];

        Predicate<Person> namePredicate = nameKeywords.length > 0
                ? new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords))
                : person -> false;
        Predicate<Person> yearPredicate = yearKeywords.length > 0
                ? new YearContainsKeywordsPredicate(Arrays.asList(yearKeywords))
                : person -> false;
        Predicate<Person> coursePredicate = courseKeywords.length > 0
                ? new CourseContainsKeywordsPredicate(Arrays.asList(courseKeywords))
                : person -> false;
        Predicate<Person> skillPredicate = skillKeywords.length > 0
                ? new SkillContainsKeywordPredicate(Arrays.asList(skillKeywords))
                : person -> false;

        Predicate<Person> combinedPredicate = namePredicate
                .or(yearPredicate)
                .or(coursePredicate)
                .or(skillPredicate);

        System.out.println(combinedPredicate);
        return new FindCommand(
                combinedPredicate
        );
    }

}
