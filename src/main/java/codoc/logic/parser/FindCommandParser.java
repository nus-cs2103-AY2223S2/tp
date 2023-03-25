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

    private static class PredicateStringPair {
        private Predicate<Person> predicate;
        private String string;

        private PredicateStringPair() {
            this.predicate = person -> true;;
            this.string = "";
        }

        private void appendString(String s) {
            this.string = this.string.concat(s);
        }
        private void combinePredicate(Predicate<Person> namePredicate) {
            this.predicate = this.predicate.and(namePredicate);
        }
        private Predicate<Person> getPredicate() {
            return this.predicate;
        }
        private String getString() {
            return this.string;
        }
    }

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
                        PREFIX_MOD,
                        PREFIX_SKILL
                );

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        PredicateStringPair psp = new PredicateStringPair();
        addNamePredicate(argMultimap, psp);
        addYearPredicate(argMultimap, psp);
        addCoursePredicate(argMultimap, psp);
        addModulePredicate(argMultimap, psp);
        addSkillPredicate(argMultimap, psp);

        return new FindCommand(psp.getPredicate(), psp.getString());

    }

    private void addNamePredicate(ArgumentMultimap argMultimap, PredicateStringPair psp) {
        String nameArgs = argMultimap.getValue(PREFIX_NAME).orElse("").trim();
        if (!nameArgs.isEmpty()) {
            String[] nameKeywords = nameArgs.split("\\s+");
            Predicate<Person> namePredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            psp.appendString(namePredicate.toString());
            psp.combinePredicate(namePredicate);
        }
    }

    private void addYearPredicate(ArgumentMultimap argMultimap, PredicateStringPair psp) {
        String yearArgs = argMultimap.getValue(PREFIX_YEAR).orElse("").trim();
        if (!yearArgs.isEmpty()) {
            String[]yearKeywords = yearArgs.split("\\s+");
            Predicate<Person> yearPredicate = new YearContainsKeywordsPredicate(Arrays.asList(yearKeywords));
            psp.appendString(yearPredicate.toString());
            psp.combinePredicate(yearPredicate);
        }
    }

    private void addCoursePredicate(ArgumentMultimap argMultimap, PredicateStringPair psp) {
        String courseArgs = argMultimap.getValue(PREFIX_COURSE).orElse("").trim();
        if (!courseArgs.isEmpty()) {
            String[] courseKeywords = courseArgs.split("\\s+");
            Predicate<Person> coursePredicate = new CourseContainsKeywordsPredicate(Arrays.asList(courseKeywords));
            psp.appendString(coursePredicate.toString());
            psp.combinePredicate(coursePredicate);
        }
    }

    private void addModulePredicate(ArgumentMultimap argMultimap, PredicateStringPair psp) {
        String moduleArgs = argMultimap.getValue(PREFIX_MOD).orElse("").trim();
        if (!moduleArgs.isEmpty()) {
            String[] moduleKeywords = moduleArgs.split("\\s+");
            Predicate<Person> modulePredicate = new ModuleContainsKeywordsPredicate(Arrays.asList(moduleKeywords));
            psp.appendString(modulePredicate.toString());
            psp.combinePredicate(modulePredicate);
        }
    }

    private void addSkillPredicate(ArgumentMultimap argMultimap, PredicateStringPair psp) {
        String skillArgs = argMultimap.getValue(PREFIX_SKILL).orElse("").trim();
        if (!skillArgs.isEmpty()) {
            String[] skillKeywords = skillArgs.split("\\s+");
            Predicate<Person> skillPredicate = new SkillContainsKeywordsPredicate(Arrays.asList(skillKeywords));
            psp.appendString(skillPredicate.toString());
            psp.combinePredicate(skillPredicate);
        }
    }

}
