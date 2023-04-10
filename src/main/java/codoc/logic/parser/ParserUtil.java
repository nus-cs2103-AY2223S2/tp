package codoc.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import codoc.commons.core.index.Index;
import codoc.commons.util.StringUtil;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.course.Course;
import codoc.model.module.Module;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Linkedin;
import codoc.model.person.Name;
import codoc.model.person.Year;
import codoc.model.skill.Skill;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_NAME_TOO_LONG = "Name cannot be longer than 50 characters.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     * @throws ParseException if the given {@code name} is longer than 50 characters.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (trimmedName.length() > 50) {
            throw new ParseException(MESSAGE_NAME_TOO_LONG);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String github} into a {@code Github}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code github} is invalid.
     */
    public static Github parseGithub(String github) throws ParseException {
        if (github != null) {
            if (github.equals("")) {
                return new Github(null);
            }
            String trimmedGithub = github.trim();
            if (!Github.isValidGithub(trimmedGithub)) {
                throw new ParseException(Github.MESSAGE_CONSTRAINTS);
            }
            return new Github(trimmedGithub);
        }
        return new Github(null);
    }

    /**
     * Parses a {@code String linkedin} into an {@code Linkedin}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code linkedin} is invalid.
     */
    public static Linkedin parseLinkedin(String linkedin) throws ParseException {
        if (linkedin != null) {
            if (linkedin.equals("")) {
                return new Linkedin(null);
            }
            String trimmedLinkedin = linkedin.trim();
            if (!Linkedin.isValidLinkedin(trimmedLinkedin)) {
                throw new ParseException(Linkedin.MESSAGE_CONSTRAINTS);
            }
            return new Linkedin(trimmedLinkedin);
        }
        return new Linkedin(null);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }
    /**
     * Parses a {@code String Course} into an {@code Course}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code course} is invalid.
     */
    public static Course parseCourse(String course) throws ParseException {
        requireNonNull(course);
        String trimmedIndex = course.trim();
        if (!Course.isValidCourse(trimmedIndex)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(trimmedIndex);
    }
    /**
     * Parses a {@code String Year} into an {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Year.isValidYear(trimmedYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(trimmedYear);
    }
    /**
     * Parses a {@code String skill} into a {@code Skill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String trimmedSkill = skill.trim();
        if (!Skill.isValidSkillName(trimmedSkill)) {
            throw new ParseException(Skill.MESSAGE_CONSTRAINTS);
        }
        return new Skill(trimmedSkill);
    }

    /**
     * Parses a {@code List<Skill> skillsList} into a {@code Set<Skill> skillSet}.
     * Parses all items into Skill and puts them into skillSet
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Set<Skill> parseSkillSet(List<String> skillList) throws ParseException {
        requireNonNull(skillList);
        final Set<Skill> skillSet = new HashSet<>();
        for (String skillName : skillList) { // Had to use loop for mat instead of forEach to handle exception
            if (skillName.equals("")) { // Ignore empty entries
                continue;
            }
            Skill skill = parseSkill(skillName);
            skillSet.add(skill);
        }
        return skillSet;
    }

    /**
     * Parses a {@code String Module} into a {@code Module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module} is invalid.
     */
    public static Module parseModule(String module) throws ParseException {
        requireNonNull(module);
        module = module.toUpperCase();
        String trimmedModule = module.trim();
        if (!Module.isValidModuleName(trimmedModule)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        if (!Module.isValidModuleYear(trimmedModule)) {
            throw new ParseException(Module.YEAR_CONSTRAINTS);
        }
        return new Module(trimmedModule);
    }
    /**
     * Parses {@code Collection<String> modules} into a {@code List<Module>}.
     */
    public static Set<Module> parseModuleSet(List<String> moduleList) throws ParseException {
        requireNonNull(moduleList);
        final Set<Module> moduleSet = new HashSet<>();
        for (String moduleName : moduleList) {
            if (moduleName.equals("")) { // Ignore empty entries
                continue;
            }
            moduleSet.add(parseModule(moduleName));
        }
        return moduleSet;
    }
}
