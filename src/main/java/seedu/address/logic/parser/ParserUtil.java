package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.ScoreValue;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final int FIRST_INDEX = 0;

    public static final int SECOND_INDEX = 1;

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses the first of {@code twoIndexes} into an {@code Index} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseFirstIndex(String twoIndexes) throws ParseException {
        String trimmedIndex = twoIndexes.trim();

        if (!StringUtil.isTwoIndexString(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        String firstIndex = trimmedIndex.split("\\s+")[FIRST_INDEX];

        if (!StringUtil.isNonZeroUnsignedInteger(firstIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(firstIndex));
    }

    /**
     * Parses the second of {@code twoIndexes} into an {@code Index} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseSecondIndex(String twoIndexes) throws ParseException {
        String trimmedIndex = twoIndexes.trim();

        if (!StringUtil.isTwoIndexString(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        String secondIndex = trimmedIndex.split("\\s+")[SECOND_INDEX];

        if (!StringUtil.isNonZeroUnsignedInteger(secondIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        return Index.fromOneBased(Integer.parseInt(secondIndex));
    }

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
        if (!Phone.isMoreThanMaxDigits(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_EXCEED_MAX_DIGITS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
        if (!Tag.isMoreThanMaxLetters(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_EXCEED_MAX_LETTERS);
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

    /**
     * Parses a {@code String taskName} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskName} is invalid.
     */
    public static Name parseTaskName(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedName = taskName.trim();
        if (!Task.isValidTaskName(trimmedName)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    //=========== Score ================================================================================
    /**
     * Parses a {@code String label} into an {@code Label}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Label} is invalid.
     */
    public static Label parseScoreLabel(String label) throws ParseException {
        requireNonNull(label);
        String trimmedLabel = label.trim();
        if (!Label.isValidLabel(trimmedLabel)) {
            throw new ParseException(Label.MESSAGE_CONSTRAINTS);
        }
        return new Label(trimmedLabel);
    }

    /**
     * Parses a {@code Double value} into an {@code ScoreValue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ScoreValue} is invalid.
     */
    public static ScoreValue parseScoreValue(String value) throws ParseException {
        requireNonNull(value);
        String trimmedValue = value.trim();
        if (!ScoreValue.isValidScoreValue(trimmedValue)) {
            throw new ParseException(ScoreValue.MESSAGE_CONSTRAINTS);
        }
        return new ScoreValue(trimmedValue);
    }

    /**
     * Parses a {@code LocalDate date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Date} is invalid.
     */
    public static Date parseScoreDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        if (!Date.isFutureDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_INVALID_DATE);
        }
        return new Date(trimmedDate);
    }



    /**
     * Parses a file path.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given file path is invalid.
     */
    public static String parseFilePath(Optional<String> filePathOpt) throws ParseException {
        String filePath = filePathOpt.isEmpty() ? "" : filePathOpt.get();
        filePath = filePath.trim();
        Path path;
        try {
            path = Paths.get(filePath);
        } catch (InvalidPathException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DIRECTORY);
        }
        if (Files.isDirectory(path)) {
            return path.toString();
        } else {
            throw new ParseException(Messages.MESSAGE_INVALID_DIRECTORY);
        }
    }

}
