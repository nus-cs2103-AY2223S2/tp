package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.CodePrefix;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.module.Tags;
import seedu.modtrek.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses a {@code String code} into a {@code Code}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static Code parseCode(String code) throws ParseException {
        requireNonNull(code);
        String trimmedName = code.trim().toUpperCase(Locale.ROOT);
        if (!Code.isValidCode(trimmedName)) {
            throw new ParseException(Code.MESSAGE_CONSTRAINTS);
        }
        return new Code(trimmedName);
    }

    /**
     * Parses a {@code String codePrefix} into a {@code CodePrefix}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code codePrefix} is invalid.
     */
    public static CodePrefix parseCodePrefix(String codePrefix) throws ParseException {
        requireNonNull(codePrefix);
        String trimmedName = codePrefix.trim().toUpperCase(Locale.ROOT);
        if (!CodePrefix.isValidCodePrefix(trimmedName)) {
            throw new ParseException(CodePrefix.MESSAGE_CONSTRAINTS);
        }
        return new CodePrefix(trimmedName);
    }

    /**
     * Parses {@code Collection<String> codePrefixes} into a {@code Set<String>}.
     */
    public static Set<String> parseCodePrefixes(Collection<String> codePrefixes) throws ParseException {
        requireNonNull(codePrefixes);
        final Set<String> codePrefixSet = new HashSet<>();
        for (String codePrefixName : codePrefixes) {
            codePrefixSet.add(parseCodePrefix(codePrefixName).toString());
        }
        return codePrefixSet;
    }

    /**
     * Parses a {@code String credit} into a {@code Credit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code credit} is invalid.
     */
    public static Credit parseCredit(String credit) throws ParseException {
        requireNonNull(credit);
        String trimmedCredit = credit.trim();
        if (!Credit.isValidCredit(trimmedCredit)) {
            throw new ParseException(Credit.MESSAGE_CONSTRAINTS);
        }
        return new Credit(trimmedCredit);
    }

    /**
     * Parses {@code Collection<String> credits} into a {@code Set<Credit>}.
     */
    public static Set<Credit> parseCredits(Collection<String> credits) throws ParseException {
        requireNonNull(credits);
        final Set<Credit> creditSet = new HashSet<>();
        for (String creditName : credits) {
            creditSet.add(parseCredit(creditName));
        }
        return creditSet;
    }

    /**
     * Parses a {@code String semYear} into an {@code SemYear}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semYear} is invalid.
     */
    public static SemYear parseSemYear(String semYear) throws ParseException {
        requireNonNull(semYear);
        String trimmedAddress = semYear.trim().toUpperCase(Locale.ROOT);
        if (!SemYear.isValidSemYear(trimmedAddress)) {
            throw new ParseException(SemYear.MESSAGE_CONSTRAINTS);
        }
        return new SemYear(trimmedAddress);
    }

    /**
     * Parses {@code Collection<String> semYears} into a {@code Set<SemYear>}.
     */
    public static Set<SemYear> parseSemYears(Collection<String> semYears) throws ParseException {
        requireNonNull(semYears);
        final Set<SemYear> semYearSet = new HashSet<>();
        for (String semYearName : semYears) {
            semYearSet.add(parseSemYear(semYearName));
        }
        return semYearSet;
    }

    /**
     * Parses a {@code String grade} into an {@code Grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code grade} is invalid.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim().toUpperCase(Locale.ROOT);
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return new Grade(trimmedGrade);
    }

    /**
     * Parses {@code Collection<String> grades} into a {@code Set<Grade>}.
     */
    public static Set<Grade> parseGrades(Collection<String> grades) throws ParseException {
        requireNonNull(grades);
        final Set<Grade> gradeSet = new HashSet<>();
        for (String gradeName : grades) {
            gradeSet.add(parseGrade(gradeName));
        }
        return gradeSet;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    public static Tags parseTagsForSort(Set<Tag> tags) {
        return new Tags(tags);
    }

    /**
     * Parses {@code Collection<String> codes} into a {@code Set<Code>}.
     */
    public static Set<Code> parseCodes(Collection<String> codes) throws ParseException {
        requireNonNull(codes);
        final Set<Code> codeSet = new HashSet<>();
        for (String code : codes) {
            codeSet.add(parseCode(code));
        }
        return codeSet;
    }

    /**
     * Checks if the argument following the specified prefix contains the "/" character.
     *
     * @throws ParseException if the argument following the specified prefix contains the "/" character.
     */
    public static void checkIfSlashIsPresent(ArgumentMultimap argumentMultimap, Prefix prefix,
                                             String messageUsage) throws ParseException {
        List<String> prefixArgs = argumentMultimap.getAllValues(prefix);
        for (String prefixArg : prefixArgs) {
            if (prefixArg.contains("/")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
            }
        }
    }
}
