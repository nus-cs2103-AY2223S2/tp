package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.logic.parser.CliSyntax.PREFIX_COURSE;
import static codoc.logic.parser.CliSyntax.PREFIX_MOD;
import static codoc.logic.parser.CliSyntax.PREFIX_NAME;
import static codoc.logic.parser.CliSyntax.PREFIX_SKILL;
import static codoc.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Arrays;
import java.util.function.Predicate;

import codoc.logic.commands.FindCommand;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.module.ModuleContainsKeywordsPredicate;
import codoc.model.person.CourseContainsKeywordsPredicate;
import codoc.model.person.NameContainsKeywordsPredicate;
import codoc.model.person.Person;
import codoc.model.person.YearContainsKeywordsPredicate;
import codoc.model.skill.SkillContainsKeywordsPredicate;

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
                        PREFIX_SKILL,
                        PREFIX_MOD
                );

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Predicate<Person> combinedPredicate = person -> true;
        combinedPredicate = addNamePredicate(argMultimap, combinedPredicate);
        combinedPredicate = addYearPredicate(argMultimap, combinedPredicate);
        combinedPredicate = addCoursePredicate(argMultimap, combinedPredicate);
        combinedPredicate = addSkillPredicate(argMultimap, combinedPredicate);
        combinedPredicate = addModulePredicate(argMultimap, combinedPredicate);


        return new FindCommand(
                combinedPredicate
        );
    }
    private Predicate<Person> addNamePredicate(ArgumentMultimap argMultimap, Predicate<Person> combinedPredicate) {
        String nameArgs = argMultimap.getValue(PREFIX_NAME).orElseGet(() -> "").trim();
        if (!nameArgs.isEmpty()) {
            String[] nameKeywords = nameArgs.split("\\s+");
            Predicate<Person> namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            combinedPredicate = combinedPredicate.and(namePredicate);
        }
        return combinedPredicate;
    }

    private Predicate<Person> addYearPredicate(ArgumentMultimap argMultimap, Predicate<Person> combinedPredicate) {
        String yearArgs = argMultimap.getValue(PREFIX_YEAR).orElseGet(() -> "").trim();
        if (!yearArgs.isEmpty()) {
            String[]yearKeywords = yearArgs.split("\\s+");
            Predicate<Person> yearPredicate = new YearContainsKeywordsPredicate(Arrays.asList(yearKeywords));
            combinedPredicate = combinedPredicate.and(yearPredicate);
        }
        return combinedPredicate;
    }

    private Predicate<Person> addCoursePredicate(ArgumentMultimap argMultimap, Predicate<Person> combinedPredicate) {
        String courseArgs = argMultimap.getValue(PREFIX_COURSE).orElseGet(() -> "").trim();
        if (!courseArgs.isEmpty()) {
            String[] courseKeywords = courseArgs.split("\\s+");
            Predicate<Person> coursePredicate = new CourseContainsKeywordsPredicate(Arrays.asList(courseKeywords));
            combinedPredicate = combinedPredicate.and(coursePredicate);
        }
        return combinedPredicate;
    }

    private Predicate<Person> addSkillPredicate(ArgumentMultimap argMultimap, Predicate<Person> combinedPredicate) {
        String skillArgs = argMultimap.getValue(PREFIX_SKILL).orElseGet(() -> "").trim();
        if (!skillArgs.isEmpty()) {
            String[] skillKeywords = skillArgs.split("\\s+");
            Predicate<Person> skillPredicate = new SkillContainsKeywordsPredicate(Arrays.asList(skillKeywords));
            combinedPredicate = combinedPredicate.and(skillPredicate);
        }
        return combinedPredicate;
    }

    private Predicate<Person> addModulePredicate(ArgumentMultimap argMultimap, Predicate<Person> combinedPredicate) {
        String moduleArgs = argMultimap.getValue(PREFIX_MOD).orElseGet(() -> "").trim();
        if (!moduleArgs.isEmpty()) {
            String[] moduleKeywords = moduleArgs.split("\\s+");
            Predicate<Person> modulePredicate = new ModuleContainsKeywordsPredicate(Arrays.asList(moduleKeywords));
            combinedPredicate = combinedPredicate.and(modulePredicate);
        }
        return combinedPredicate;
    }

}
