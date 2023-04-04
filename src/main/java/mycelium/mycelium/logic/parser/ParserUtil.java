package mycelium.mycelium.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Stream;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /* Some project names are illegal as they interfere with parsing */
    public static final String[] ILLEGAL_PROJECT_NAMES = {"-pn2"};

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
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
     * Parses a {@code String year of birth} into an {@code YearOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static YearOfBirth parseYearOfBirth(String yearOfBirth) throws ParseException {
        requireNonNull(yearOfBirth);
        String trimmedYearOfBirth = yearOfBirth.trim();
        if (!YearOfBirth.isValidYearOfBirth(trimmedYearOfBirth)) {
            throw new ParseException(YearOfBirth.MESSAGE_CONSTRAINTS);
        }
        return new YearOfBirth(trimmedYearOfBirth);
    }

    /**
     * Parses a project's status from its string representation.
     */
    public static ProjectStatus parseProjectStatus(String projectStatus) throws ParseException {
        requireNonNull(projectStatus);
        String trimmedProjectStatus = projectStatus.trim();
        if (!ProjectStatus.isValidProjectStatus(trimmedProjectStatus)) {
            throw new ParseException(ProjectStatus.MESSAGE_CONSTRAINTS);
        }
        return ProjectStatus.fromString(trimmedProjectStatus);
    }

    /**
     * Parses a string by asserting that it is non-empty after trimming. Note that this
     */
    public static NonEmptyString parseNonEmptyString(String source) throws ParseException {
        requireNonNull(source);
        String trimmedSource = source.trim();
        if (!NonEmptyString.isValid(trimmedSource)) {
            throw new ParseException(NonEmptyString.MESSAGE_CONSTRAINTS);
        }
        return NonEmptyString.of(trimmedSource);
    }

    /**
     * Parses a string into a {@code NonEmptyString} representing a project name.
     */
    public static NonEmptyString parseProjectName(String source) throws ParseException {
        NonEmptyString name;
        try {
            name = parseNonEmptyString(source);
        } catch (ParseException e) {
            throw new ParseException(Messages.MESSAGE_EMPTY_PROJECT_NAME);
        }

        // some project names may cause problems down the line, so we check them here
        for (String bannedName : ILLEGAL_PROJECT_NAMES) {
            if (name.equals(bannedName)) {
                throw new ParseException(String.format(Messages.MESSAGE_ILLEGAL_PROJECT_NAME_FMT, name));
            }
        }
        return name;
    }

    /**
     * Parses a string into a {@code NonEmptyString} representing a project or client's source.
     */
    public static NonEmptyString parseSource(String source) throws ParseException {
        try {
            return parseNonEmptyString(source);
        } catch (ParseException e) {
            throw new ParseException(Messages.MESSAGE_EMPTY_SOURCE);
        }
    }

    /**
     * Parses a string into a {@code LocalDate} using the formatter provided.
     */
    public static LocalDate parseLocalDate(String s, DateTimeFormatter dateFmt) throws ParseException {
        requireNonNull(s);
        String trimmedSource = s.trim();
        try {
            return LocalDate.parse(trimmedSource, dateFmt);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Performs a map operation. We need this because {@code Optional.map} does not throw checked exceptions.
     *
     * @param src The raw input to pass, which may be an {@code Optional.empty()}
     * @param f   The parsing function to use against the raw input
     * @param <T> Type of the raw input
     * @param <U> Type of the parsed result
     * @return The parsed result, wrapped in an {@code Optional}
     * @throws ParseException If the parsing function throws it.
     */
    public static <T, U> Optional<U> parseOptionalWith(Optional<T> src, ParserFn<T, U> f) throws ParseException {
        return src.isPresent() ? Optional.of(f.parse(src.get())) : Optional.empty();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
