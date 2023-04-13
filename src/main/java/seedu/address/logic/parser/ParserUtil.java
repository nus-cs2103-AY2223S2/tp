package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.role.Role;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Date;
import seedu.address.model.task.Score;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

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
     * Parses a {@code String des} into a {@code TaskDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code des} is invalid.
     */
    public static TaskDescription parseTaskDescription(String des) throws ParseException {
        requireNonNull(des);
        String trimmedDes = des.trim();
        if (!TaskDescription.isValidTaskDescription(trimmedDes)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new TaskDescription(trimmedDes);
    }

    /**
     * Parses a {@code String des} into a {@code Comment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code des} is invalid.
     */
    public static Comment parseComment(String com) throws ParseException {
        requireNonNull(com);
        String trimmedCom = com.trim();
        if (!Comment.isValidComment(trimmedCom)) {
            throw new ParseException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(trimmedCom);
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
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
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
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRoleName(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String taskType} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskType} is invalid.
     */
    public static String parseTaskType(String taskType) throws ParseException {
        requireNonNull(taskType);
        String trimmedTaskType = taskType.trim();
        System.out.println(trimmedTaskType.equals("T"));
        if (!Task.isValidTaskType(trimmedTaskType)) {
            throw new ParseException(EditTaskCommand.MESSAGE_CONSTRAINTS);
        }
        return trimmedTaskType;
    }

    /**
     * Parses {@code Collection<String> roles} into a {@code Set<Tag>}.
     */
    public static Set<Role> parseRoles(Collection<String> roles) throws ParseException {
        requireNonNull(roles);
        final Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(parseRole(roleName));
        }
        return roleSet;
    }

    /**
     * Parses {@code String taskIndex} into a {@code Index}.
     *
     * @param taskIndex
     * @return index of task
     * @throws ParseException
     */
    public static Index parseTaskIndex(String taskIndex) throws ParseException {
        String trimmedIndex = taskIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code String personIndex} into a {@code Index}.
     *
     * @param personIndex
     * @return index of person
     * @throws ParseException
     */
    public static Index parsePersonIndex(String personIndex) throws ParseException {
        String trimmedIndex = personIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }


    /**
     * Parses {@code String score} into a {@code Score}.
     * @param score
     * @return score of person
     * @throws ParseException
     */
    public static Score parseScoreValue(String score) throws ParseException {
        requireNonNull(score);
        String trimmedScore = score.trim();
        int scoreValue = Integer.parseInt(trimmedScore);
        if (!Score.isValidScore(scoreValue)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Score(scoreValue);
    }
}
